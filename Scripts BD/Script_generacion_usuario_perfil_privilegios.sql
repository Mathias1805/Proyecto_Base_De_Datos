--GENERACION DE USUARIO
alter session set "_ORACLE_SCRIPT" = true;

CREATE USER BD_banco_sangre
IDENTIFIED BY grupo6
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP
QUOTA 20M ON USERS


ALTER USER "BD_BANCO_SANGRE"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP"
ACCOUNT UNLOCK ;

--GENERACION DE PRIVILEGIOS

GRANT CREATE JOB TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT UPDATE ANY CUBE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY ANALYTIC VIEW TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY TRIGGER TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY SQL TRANSLATION PROFILE TO "BD_BANCO_SANGRE" ;
GRANT MANAGE ANY FILE GROUP TO "BD_BANCO_SANGRE" ;
GRANT ALTER PUBLIC DATABASE LINK TO "BD_BANCO_SANGRE" ;
GRANT MANAGE FILE GROUP TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY INDEX TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY SEQUENCE TO "BD_BANCO_SANGRE" ;
GRANT ALTER PROFILE TO "BD_BANCO_SANGRE" ;
GRANT INHERIT ANY PRIVILEGES TO "BD_BANCO_SANGRE" ;
GRANT UNDER ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY LIBRARY TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY EDITION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ROLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE LIBRARY TO "BD_BANCO_SANGRE" ;
GRANT DROP ROLLBACK SEGMENT TO "BD_BANCO_SANGRE" ;
GRANT CREATE TRIGGER TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER DATABASE TRIGGER TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY OUTLINE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY ANALYTIC VIEW TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY INDEXTYPE TO "BD_BANCO_SANGRE" ;
GRANT USE ANY JOB RESOURCE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY DIRECTORY TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY RULE SET TO "BD_BANCO_SANGRE" ;
GRANT USE ANY SQL TRANSLATION PROFILE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT DEBUG CONNECT SESSION TO "BD_BANCO_SANGRE" ;
GRANT LOGMINING TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY ATTRIBUTE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT CREATE LOCKDOWN PROFILE TO "BD_BANCO_SANGRE" ;
GRANT ALTER SESSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE MATERIALIZED VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE PLUGGABLE DATABASE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY ANALYTIC VIEW TO "BD_BANCO_SANGRE" ;
GRANT WRITE ANY ANALYTIC VIEW CACHE TO "BD_BANCO_SANGRE" ;
GRANT MERGE ANY VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY INDEX TO "BD_BANCO_SANGRE" ;
GRANT READ ANY ANALYTIC VIEW CACHE TO "BD_BANCO_SANGRE" ;
GRANT CREATE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY RULE SET TO "BD_BANCO_SANGRE" ;
GRANT CREATE SQL TRANSLATION PROFILE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY MATERIALIZED VIEW TO "BD_BANCO_SANGRE" ;
GRANT AUDIT SYSTEM TO "BD_BANCO_SANGRE" ;
GRANT CREATE OPERATOR TO "BD_BANCO_SANGRE" ;
GRANT MANAGE ANY QUEUE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY SQL PROFILE TO "BD_BANCO_SANGRE" ;
GRANT GRANT ANY OBJECT PRIVILEGE TO "BD_BANCO_SANGRE" ;
GRANT CREATE INDEXTYPE TO "BD_BANCO_SANGRE" ;
GRANT AUDIT ANY TO "BD_BANCO_SANGRE" ;
GRANT INHERIT ANY REMOTE PRIVILEGES TO "BD_BANCO_SANGRE" ;
GRANT DEBUG ANY PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY SEQUENCE TO "BD_BANCO_SANGRE" ;
GRANT CREATE MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT UPDATE ANY CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT CREATE VIEW TO "BD_BANCO_SANGRE" ;
GRANT ALTER DATABASE LINK TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY SQL TRANSLATION PROFILE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY EVALUATION CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT DELETE ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY ATTRIBUTE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE SESSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE RULE TO "BD_BANCO_SANGRE" ;
GRANT BECOME USER TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT INSERT ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY SQL PROFILE TO "BD_BANCO_SANGRE" ;
GRANT FORCE ANY TRANSACTION TO "BD_BANCO_SANGRE" ;
GRANT DELETE ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY SEQUENCE TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY EDITION TO "BD_BANCO_SANGRE" ;
GRANT CREATE EXTERNAL JOB TO "BD_BANCO_SANGRE" ;
GRANT EM EXPRESS CONNECT TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY MATERIALIZED VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT FLASHBACK ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY RULE SET TO "BD_BANCO_SANGRE" ;
GRANT BACKUP ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY CUBE TO "BD_BANCO_SANGRE" ;
GRANT CREATE CREDENTIAL TO "BD_BANCO_SANGRE" ;
GRANT CREATE TABLE TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY LIBRARY TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY OUTLINE TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY HIERARCHY TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANALYTIC VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER KEY MANAGEMENT TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY CLUSTER TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY CLASS TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CREDENTIAL TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY RULE SET TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY SEQUENCE TO "BD_BANCO_SANGRE" ;
GRANT UNDER ANY TYPE TO "BD_BANCO_SANGRE" ;
GRANT MANAGE TABLESPACE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY OPERATOR TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY OPERATOR TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY HIERARCHY TO "BD_BANCO_SANGRE" ;
GRANT EXEMPT IDENTITY POLICY TO "BD_BANCO_SANGRE" ;
GRANT CREATE TYPE TO "BD_BANCO_SANGRE" ;
GRANT CREATE TABLESPACE TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY TRANSACTION TO "BD_BANCO_SANGRE" ;
GRANT DELETE ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CUBE TO "BD_BANCO_SANGRE" ;
GRANT LOCK ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE EVALUATION CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY TYPE TO "BD_BANCO_SANGRE" ;
GRANT ADVISOR TO "BD_BANCO_SANGRE" ;
GRANT CREATE PUBLIC DATABASE LINK TO "BD_BANCO_SANGRE" ;
GRANT ANALYZE ANY TO "BD_BANCO_SANGRE" ;
GRANT CREATE ATTRIBUTE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY RULE TO "BD_BANCO_SANGRE" ;
GRANT INSERT ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ROLLBACK SEGMENT TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY JOB TO "BD_BANCO_SANGRE" ;
GRANT ALTER USER TO "BD_BANCO_SANGRE" ;
GRANT QUERY REWRITE TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY DICTIONARY TO "BD_BANCO_SANGRE" ;
GRANT CREATE PUBLIC SYNONYM TO "BD_BANCO_SANGRE" ;
GRANT GLOBAL QUERY REWRITE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY CLUSTER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY RULE TO "BD_BANCO_SANGRE" ;
GRANT UPDATE ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER RESOURCE MANAGER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY SYNONYM TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY SYNONYM TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT CREATE SYNONYM TO "BD_BANCO_SANGRE" ;
GRANT SET CONTAINER TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY PROGRAM TO "BD_BANCO_SANGRE" ;
GRANT EXEMPT REDACTION POLICY TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY TYPE TO "BD_BANCO_SANGRE" ;
GRANT ON COMMIT REFRESH TO "BD_BANCO_SANGRE" ;
GRANT DEBUG CONNECT ANY TO "BD_BANCO_SANGRE" ;
GRANT CREATE SEQUENCE TO "BD_BANCO_SANGRE" ;
GRANT CREATE HIERARCHY TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT COMMENT ANY MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER SQL TUNING SET TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY INDEXTYPE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY INDEX TO "BD_BANCO_SANGRE" ;
GRANT RESTRICTED SESSION TO "BD_BANCO_SANGRE" ;
GRANT DEQUEUE ANY QUEUE TO "BD_BANCO_SANGRE" ;
GRANT ANALYZE ANY DICTIONARY TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY INDEXTYPE TO "BD_BANCO_SANGRE" ;
GRANT TRANSLATE ANY SQL TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER ANY SQL TUNING SET TO "BD_BANCO_SANGRE" ;
GRANT CREATE USER TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY OPERATOR TO "BD_BANCO_SANGRE" ;
GRANT CREATE CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT CREATE PROFILE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY ROLE TO "BD_BANCO_SANGRE" ;
GRANT UPDATE ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY LIBRARY TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CLUSTER TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY RULE TO "BD_BANCO_SANGRE" ;
GRANT ALTER TABLESPACE TO "BD_BANCO_SANGRE" ;
GRANT UNDER ANY VIEW TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT GRANT ANY PRIVILEGE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY TRIGGER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY VIEW TO "BD_BANCO_SANGRE" ;
GRANT ALTER LOCKDOWN PROFILE TO "BD_BANCO_SANGRE" ;
GRANT EXPORT FULL DATABASE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY MEASURE FOLDER TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY EVALUATION CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT TEXT DATASTORE ACCESS TO "BD_BANCO_SANGRE" ;
GRANT FLASHBACK ARCHIVE ADMINISTER TO "BD_BANCO_SANGRE" ;
GRANT IMPORT FULL DATABASE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY OUTLINE TO "BD_BANCO_SANGRE" ;
GRANT COMMENT ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT READ ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE DATABASE LINK TO "BD_BANCO_SANGRE" ;
GRANT DROP PUBLIC SYNONYM TO "BD_BANCO_SANGRE" ;
GRANT DROP USER TO "BD_BANCO_SANGRE" ;
GRANT CHANGE NOTIFICATION TO "BD_BANCO_SANGRE" ;
GRANT CREATE MINING MODEL TO "BD_BANCO_SANGRE" ;
GRANT INSERT ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT DROP LOCKDOWN PROFILE TO "BD_BANCO_SANGRE" ;
GRANT DROP PROFILE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY MATERIALIZED VIEW TO "BD_BANCO_SANGRE" ;
GRANT CREATE RULE SET TO "BD_BANCO_SANGRE" ;
GRANT EXEMPT ACCESS POLICY TO "BD_BANCO_SANGRE" ;
GRANT MANAGE SCHEDULER TO "BD_BANCO_SANGRE" ;
GRANT READ ANY FILE GROUP TO "BD_BANCO_SANGRE" ;
GRANT FORCE TRANSACTION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY CUBE BUILD PROCESS TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY TYPE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY SQL TRANSLATION PROFILE TO "BD_BANCO_SANGRE" ;
GRANT DROP PUBLIC DATABASE LINK TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY INDEXTYPE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY SQL PROFILE TO "BD_BANCO_SANGRE" ;
GRANT ALTER SYSTEM TO "BD_BANCO_SANGRE" ;
GRANT UNLIMITED TABLESPACE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY ROLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY CUBE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY TRIGGER TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT ADMINISTER SQL MANAGEMENT OBJECT TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY DIRECTORY TO "BD_BANCO_SANGRE" ;
GRANT ENQUEUE ANY QUEUE TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY EVALUATION CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY ASSEMBLY TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY TYPE TO "BD_BANCO_SANGRE" ;
GRANT REDEFINE ANY TABLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE CLUSTER TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT EXECUTE ANY EVALUATION CONTEXT TO "BD_BANCO_SANGRE" ;
GRANT RESUMABLE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY LIBRARY TO "BD_BANCO_SANGRE" ;
GRANT DROP ANY EDITION TO "BD_BANCO_SANGRE" ;
GRANT CREATE PROCEDURE TO "BD_BANCO_SANGRE" ;
GRANT ALTER DATABASE TO "BD_BANCO_SANGRE" ;
GRANT SELECT ANY CUBE TO "BD_BANCO_SANGRE" ;
GRANT GRANT ANY ROLE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY RULE TO "BD_BANCO_SANGRE" ;
GRANT CREATE ANY ATTRIBUTE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT CREATE CUBE DIMENSION TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY OPERATOR TO "BD_BANCO_SANGRE" ;
GRANT CREATE CUBE TO "BD_BANCO_SANGRE" ;
GRANT ALTER RESOURCE COST TO "BD_BANCO_SANGRE" ;
GRANT ALTER ANY HIERARCHY TO "BD_BANCO_SANGRE" ;
GRANT DROP TABLESPACE TO "BD_BANCO_SANGRE" ;
GRANT ALTER ROLLBACK SEGMENT TO "BD_BANCO_SANGRE" ;
GRANT PURGE DBA_RECYCLEBIN TO "BD_BANCO_SANGRE" ;


--GENERACION DE PERFILES
create profile c##permedico limit
  failed_login_attempts 5
  password_life_time 15
  password_lock_time unlimited
  password_grace_time 2
  sessions_per_user 1
  idle_time 30
  connect_time 480;
  
create profile c##peradministrativo limit
  failed_login_attempts 3
  password_life_time 30
  password_lock_time unlimited
  password_grace_time 2
  sessions_per_user 1
  idle_time 10
  connect_time 480;
