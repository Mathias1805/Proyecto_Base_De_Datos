--FUNCIONES
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

--PROCEDIMIENTOS

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

--TRIGGERS

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
