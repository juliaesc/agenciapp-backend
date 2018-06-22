USE [AgenciApp_DB]
GO
--ALTER VIEW [dbo].[v_Users] 
AS
select cast(ag.n_legajo as numeric(8)) as legajo,
	   RTRIM(t_apyn) as titular_agencia,
	   isnull(RTRIM(t_calle),'') + ' Nº ' + isnull(RTRIM(t_numero),'') + ' Localidad: ' + isnull(RTRIM(a.t_localidad),'') + ' C.P.: ' + isnull(RTRIM(a.c_codigopostal),'') as direccion, 
	   cast(c_comisionista as numeric(3)) as comisionista, 
	   cast(n_cantterminales as numeric(3)) as cant_terminales,
	   cast(n_cuit as numeric(15)) as cuit, 
	   cast(n_sucursal as numeric(8)) as sucursal_banco, 
	   cast(n_cuenta as numeric(15)) as n_cuenta, 
	   c_DebAut as tiene_DA, 
	   cast(RTRIM(t_tipocuenta) as varchar(20)) as tipo_cuenta, 
	   cast(n_porcenIB as decimal(5,2)) as porc_IIBB, 
	   cast((t_CBU_1 + t_CBU_2) as varchar(50)) as cbu,
	   cast(RTRIM(xc_nombrefantasia) as varchar(80)) as nombre_agencia,
	   cast(RTRIM(t_email) as varchar(30)) as mail
from PJuegos.dbo.bconcesionario ag
inner join
	CCorrientes.dbo.Agenciero a
		on ag.n_legajo = a.n_legajo
inner join
	CCorrientes.dbo.TipoCuenta tc
		on a.c_tipocuenta = tc.c_tipocuenta
inner join
	PadronMensajeria.dbo.TPadron tp
		on ag.n_legajo = tp.n_legajo
inner join
	PadronMensajeria.dbo.TPadronEmail tpe
		on tp.c_padron = tpe.c_padron
where ag.n_legajo in (700058, 723204, 701233, 746666, 758530, 736442) and ag.n_legajo between 700000 and 790000 and ag.c_estado not IN ('C','J','G','R')
GO
