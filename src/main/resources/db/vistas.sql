USE [AgenciApp_DB]
GO
--ALTER VIEW [dbo].[e_v_BConcesionario] 
AS
select cast(n_legajo as numeric(8)) as legajo,
	   cast(RTRIM(xc_nombrefantasia) as varchar(80)) as nombre_agencia
from PJuegos.dbo.bconcesionario
where n_legajo between 700000 and 790000 and c_estado not IN ('C','J','G','R')
GO

USE [AgenciApp_DB]
GO
--ALTER VIEW [dbo].[e_v_Agenciero] 
AS
select cast(n_legajo as numeric(8)) as legajo, 
	   RTRIM(t_apyn) as titular_agencia, 
	   RTRIM(t_calle) as calle, RTRIM(t_numero) as numero, RTRIM(t_localidad) as localidad, RTRIM(c_codigopostal) as cp, 
	   cast(c_comisionista as numeric(3)) as comisionista, 
	   cast(n_cantterminales as numeric(3)) as cant_terminales,
	   cast(n_cuit as numeric(15)) as cuit, 
	   cast(n_sucursal as numeric(8)) as sucursal_banco, 
	   cast(n_cuenta as numeric(15)) as n_cuenta, 
	   c_DebAut as tiene_DA, 
	   cast(RTRIM(t_tipocuenta) as varchar(20)) as tipo_cuenta, 
	   cast(n_porcenIB as decimal(5,2)) as porc_IIBB, 
	   cast((t_CBU_1 + t_CBU_2) as varchar(50)) as cbu
from CCorrientes.dbo.Agenciero a
   inner join CCorrientes.dbo.TipoCuenta tc
	   on a.c_tipocuenta = tc.c_tipocuenta  
GO

USE [AgenciApp_DB]
GO
--ALTER VIEW [dbo].[e_v_TPadronEmail] 
AS
select cast(n_legajo as numeric(8)) as legajo, 
	   cast(RTRIM(t_email) as varchar(30)) as mail
from PadronMensajeria.dbo.TPadron tp
inner join PadronMensajeria.dbo.TPadronEmail tpe
on tp.c_padron = tpe.c_padron 
GO

--Sólo para dos usuarios por ahora, para pruebas
USE [AgenciApp_DB]
GO
--ALTER VIEW [dbo].[v_Users] 
AS
select cc.*, ag.nombre_agencia, ce.mail from e_v_BConcesionario ag
inner join e_v_Agenciero cc
	on ag.legajo = cc.legajo
inner join e_v_TPadronEmail ce
	on ag.legajo = ce.legajo
where ag.legajo in (700058, 723204)
GO