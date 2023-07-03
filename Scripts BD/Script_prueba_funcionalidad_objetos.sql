--SUBPROGRAMA DE PRUEBA DE FUNCIONALIDAD 

SET SERVEROUTPUT ON

--FUNCIONES
DECLARE
  id_cita NUMBER := 1234; -- Aquí debes proporcionar el valor real de la idCita que deseas consultar
  numero_laboratorio NUMBER;
BEGIN
  numero_laboratorio := obtener_numero_laboratorio(id_cita);
  DBMS_OUTPUT.PUT_LINE('El número de laboratorio para la cita ' || id_cita || ' es: ' || numero_laboratorio);
END;

DECLARE
  id_enfermera NUMBER := 123; -- Aquí debes proporcionar el valor real de la idEnfermera que deseas consultar
  nombre_enfermera VARCHAR2(50);
BEGIN
  nombre_enfermera := obtener_nombre_enfermera(id_enfermera);
  DBMS_OUTPUT.PUT_LINE('El nombre de la enfermera con id ' || id_enfermera || ' es: ' || nombre_enfermera);
END;

DECLARE
  id_tipo_sangre VARCHAR2(3) := 'A+'; -- Aquí debes proporcionar el valor real del idTipoSangre que deseas consultar
  cantidad_sangre NUMBER;
BEGIN
  cantidad_sangre := obtener_cantidad_sangre(id_tipo_sangre);
  DBMS_OUTPUT.PUT_LINE('La cantidad de sangre para el tipo ' || id_tipo_sangre || ' es: ' || cantidad_sangre);
END;

--PROCEDIMIENTOS
BEGIN
  asignar_enfermera_laboratorio(123, 1); -- Aquí debes proporcionar los valores reales de p_idEnfermera y p_nroLaboratorio
  DBMS_OUTPUT.PUT_LINE('Asignación de enfermera a laboratorio exitosa.');
END;

BEGIN
  actualizar_estado_muestra('12345678', 'Rechazado'); -- Aquí debes proporcionar los valores reales de DNI y estado
END;

BEGIN
  registrar_muestra_sangre('12345678', 'A+', 'Pendiente'); -- Aquí debes proporcionar los valores reales de p_DNI, p_idTipoSangre y p_estado
  DBMS_OUTPUT.PUT_LINE('Registro de muestra de sangre exitoso.');
END;

