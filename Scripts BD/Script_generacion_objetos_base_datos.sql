--CREACION DE OBJETOS DE BASE DE DATOS
--CREACION DE TABLAS

--TipoSangre
create table TipoSangre
(idTipoSangre varchar2(3) primary key
constraint check_Tipo check (idTipoSangre in ('A+','B+','AB+','O+','A-','B-','AB-','O-','N.A'))
)tablespace BDBancodeSangre;

--Laboratorio
create table Laboratorio
(nroLaboratorio smallint primary key,
Especialidad varchar2(24) not null
)tablespace BDBancodeSangre;

--Enfermera
create table Enfermera
(idEnfermera number primary key,
Nombre varchar2(25) not null ,
Apellido varchar2(25) not null,
Foto BLOB, 
Correo varchar2(30), 
Contraseña varchar2(30)
)tablespace BDBancodeSangre;

--Laboratorio_Enfermera
create table Laboratorio_enfermera
(idEnfermera number,
nroLaboratorio number,
constraint labEnf_pk primary key (idEnfermera,nroLaboratorio),
constraint Enfermera_fk1 foreign key (idEnfermera) references Enfermera (idEnfermera),
constraint Laboratorio_fk2 foreign key (nroLaboratorio) references Laboratorio (nroLaboratorio)
)tablespace BDBancodeSangre;


--Paciente
create table Paciente
(DNI varchar2(8) primary key,
Nombre varchar2(25) not null,
Apellido varchar2(25) not null,
constraint check_DNI check (DNI between 9999999 and 100000000)
)tablespace BDBancodeSangre;


--Inventario 
create table Inventario
(idInventario number primary key,
idTipoSangre varchar2(3),
TotalTSangre number,
constraint Inventario_fk foreign key (idTipoSangre) references TipoSangre (idTipoSangre)
)tablespace BDBancodeSangre;

--MuestraSangre
create table MuestraSangre
(DNI varchar2(8) primary key,
idTipoSangre varchar2(3),
Estado varchar2(25) not null,
Fecha_Muestra date default sysdate,
constraint MuestraSangre_fk foreign key (DNI) references Paciente(DNI),
constraint MuestraSangre_fk2 foreign key (idTipoSangre) references TipoSangre(idTipoSangre),
constraint check_estado check (Estado in ('Aceptado', 'Rechazado' , 'Pendiente','N.A'))
)tablespace BDBancodeSangre;

--Cita
create table Cita
(idCita number(8) primary key,
DNI varchar2(8),
idEnfermera number,
nroLaboratorio smallint,
CantidadLT number,
Fecha_Cita date default sysdate,
TipoCita varchar2(10),
constraint Cita_fk1 foreign key (DNI) references Paciente(DNI),
constraint Cita_fk2 foreign key (idEnfermera) references Enfermera (idEnfermera),
constraint Cita_fk3 foreign key (nroLaboratorio) references Laboratorio(nroLaboratorio),
constraint check_tipocita check (TipoCita in ('Muestra','Donacion'))
)tablespace BDBancodeSangre;

--HistoriaClinica
create table HistoriaClinica
(DNI varchar2(8) primary key,
Atencion varchar2(2) NOT NULL,
constraint HistoriaClinica_fk foreign key (DNI) references Paciente(DNI),
constraint check_atencion check (Atencion in ('Si', 'No'))
)tablespace BDBancodeSangre;

--CREANDO VISTA
create or replace view vistal_tipo_total as
    select t.idtiposangre, sum(i.totaltsangre) as total_de_sangre 
    from tiposangre t left join inventario i on t.idtiposangre = i.idtiposangre
    group by t.idtiposangre
    order by 
    CASE t.idtiposangre
    WHEN 'A+' THEN 1
    WHEN 'A-' THEN 2
    WHEN 'B+' THEN 3
    WHEN 'B-' THEN 4
    WHEN 'AB+' THEN 5
    WHEN 'AB-' THEN 6
    WHEN 'N.A' THEN 7
    ELSE 8
    end; 

create or replace view paciente_muestra_vista as
    select p.dni ,p.nombre,p.apellido ,m.idtiposangre,m.estado from 
    paciente p join muestrasangre m on p.dni = m.dni;

create or replace view ShowUnionsCita as 
    select c.idcita ,c.tipocita ,lab.especialidad,e.idenfermera,
    e.nombre as nombre_enfermera ,e.apellido as apellido_enfermera,p.dni,p.nombre as nombre_paciente,p.apellido ,m.idtiposangre from 
    cita c join paciente p on p.dni = c.dni 
    join laboratorio lab on c.nrolaboratorio= lab.nrolaboratorio
    join enfermera e on e.idenfermera = c.idenfermera
    join muestrasangre m on m.dni = c.dni;

--CREANDO SECUENCIA
--secuencia cita
CREATE SEQUENCE cita_id_sequence
  START WITH 11000001
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

--secuencia inventario 
CREATE SEQUENCE iventario_id_sequence
  START WITH 33000001
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

--CREANDO TRIGGERS
--TriggerAsignarCita
CREATE OR REPLACE TRIGGER AsignarIDCita
BEFORE INSERT ON Cita
FOR EACH ROW
BEGIN
  :NEW.idcita := cita_id_sequence.NEXTVAL;
END;

CREATE OR REPLACE TRIGGER FechaCita
BEFORE INSERT ON Cita
FOR EACH ROW
DECLARE
    v_fecha_cita DATE;
    v_dias_aleatorios NUMBER;
BEGIN
    v_dias_aleatorios := TRUNC(DBMS_RANDOM.VALUE(1, 16)); -- Generar número aleatorio entre 1 y 15
    v_fecha_cita := TRUNC(SYSDATE) + v_dias_aleatorios; -- Sumar los días aleatorios a la fecha actual
    :NEW.fecha_cita := v_fecha_cita;
END;

create or replace trigger CreateMuestraFromPacient 
after insert on paciente
for each row
begin
    insert into muestrasangre(dni,idtiposangre,estado,fecha_muestra)
    values (:NEW.DNI,'N.A','N.A',null);
end;
       
--asignacion id automatica inventario
CREATE OR REPLACE TRIGGER AsignarIDInventario
BEFORE INSERT ON inventario
FOR EACH ROW
BEGIN
  :NEW.idinventario := iventario_id_sequence.NEXTVAL;
END;

create or replace trigger alterInventarioMuesstra
after insert on cita 
for each row 
declare
    idtiposangre_v muestrasangre.idtiposangre%type;
    estado_v muestrasangre.estado%type;
begin 
    SELECT idtiposangre, estado into idtiposangre_v,estado_v
    FROM muestrasangre where dni = :new.dni ;
    if :NEW.tipocita = 'Donacion' and idtiposangre_v <> 'N.A' 
    and estado_v = 'Aceptado' and (:new.cantidadlt is not null and :new.cantidadlt <> 0 )then
    insert into inventario(idtiposangre,totaltsangre) values (idtiposangre_v,:NEW.cantidadlt);
    end if;
end;

create or replace trigger CambioEstadoAsignCita
after insert on cita 
for each row
begin
    if :new.tipocita = 'Muestra' then
        update muestrasangre
        set estado = 'Pendiente',
        idtiposangre = 'N.A'
        where dni = :new.dni;
    end if;
end ;

CREATE OR REPLACE TRIGGER check_tipocita_donacion
BEFORE INSERT OR UPDATE ON Cita
FOR EACH ROW
DECLARE
    v_estado MuestraSangre.Estado%TYPE;
BEGIN
    SELECT Estado INTO v_estado
    FROM MuestraSangre
    WHERE DNI = :NEW.DNI;

    IF (:NEW.TipoCita = 'Donacion' AND v_estado <> 'Aceptado') THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se permite la cita de donación cuando el estado de la muestra no es "Aceptado".');
    END IF;
END;

create or replace trigger CleanPaciente
before delete on paciente
for each row
declare
  v_count NUMBER;
begin
  delete from muestrasangre where dni = :old.dni;

  select count(*) into v_count from cita where dni = :old.dni;

  if v_count > 0 then
    delete from cita where dni = :old.dni;
  end if;
end;

--CREACION DE FUNCIONES
CREATE OR REPLACE FUNCTION obtener_nombre_enfermera(p_idEnfermera IN NUMBER) 
  RETURN VARCHAR2
IS
  v_nombre VARCHAR2(50);
BEGIN
  SELECT Nombre || ' ' || Apellido INTO v_nombre
  FROM Enfermera
  WHERE idEnfermera = p_idEnfermera;
  
  RETURN v_nombre;
END;

CREATE OR REPLACE FUNCTION obtener_numero_laboratorio(p_idCita IN NUMBER)
  RETURN NUMBER
IS
  v_numero NUMBER;
BEGIN
  SELECT nroLaboratorio INTO v_numero
  FROM Cita
  WHERE idCita = p_idCita;

  RETURN v_numero;
END;

CREATE OR REPLACE FUNCTION obtener_cantidad_sangre(p_idTipoSangre IN VARCHAR2)
  RETURN NUMBER
IS
  v_cantidad NUMBER;
BEGIN
  SELECT TotalTSangre INTO v_cantidad
  FROM Inventario
  WHERE idTipoSangre = p_idTipoSangre;

  RETURN v_cantidad;
END;

--CREACION DE PROCEDIMIENTOS

CREATE OR REPLACE PROCEDURE registrar_muestra_sangre(p_DNI IN VARCHAR2, p_idTipoSangre IN VARCHAR2, p_estado IN VARCHAR2)
IS
BEGIN
  INSERT INTO MuestraSangre (DNI, idTipoSangre, Estado)
  VALUES (p_DNI, p_idTipoSangre, p_estado);
  
  COMMIT;
END;

CREATE OR REPLACE PROCEDURE actualizar_estado_muestra(p_DNI IN VARCHAR2, p_estado IN VARCHAR2)
IS
BEGIN
  UPDATE MuestraSangre
  SET Estado = p_estado
  WHERE DNI = p_DNI;
  
  COMMIT;
END;

CREATE OR REPLACE PROCEDURE asignar_enfermera_laboratorio(p_idEnfermera IN NUMBER, p_nroLaboratorio IN SMALLINT)
IS
BEGIN
  INSERT INTO Laboratorio_enfermera (idEnfermera, nroLaboratorio)
  VALUES (p_idEnfermera, p_nroLaboratorio);
  
  COMMIT;
END;
