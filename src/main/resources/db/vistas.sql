USE [AgenciApp_DB]
GO
--CREATE VIEW [dbo].[e_v_BConcesionario] 
AS
select n_legajo 
from PJuegos.dbo.bconcesionario
where n_legajo between 700000 and 790000 and c_estado not IN ('C','J','G','R')
GO

USE [AgenciApp_DB]
GO
--CREATE VIEW [dbo].[e_v_Agenciero] 
AS
select n_legajo, t_apyn, t_calle, t_numero, t_localidad, c_codigopostal, c_comisionista, n_cantterminales,
	   n_cuit, n_sucursal, n_cuenta, c_DebAut, c_tipocuenta, n_porcenIB, t_CBU_2 
from CCorrientes.dbo.Agenciero
GO

USE [AgenciApp_DB]
GO
--CREATE VIEW [dbo].[e_v_TPadronEmail] 
AS
select n_legajo, t_email
from PadronMensajeria.dbo.TPadron tp
inner join PadronMensajeria.dbo.TPadronEmail tpe
on tp.c_padron = tpe.c_padron 
GO

--Sólo para dos usuarios por ahora, para pruebas
USE [AgenciApp_DB]
GO
--CREATE VIEW [dbo].[v_Users] 
AS
select cc.*, ce.t_email from [e_v_BConcesionario] ag
inner join [e_v_Agenciero] cc
	on ag.n_legajo = cc.n_legajo
inner join [e_v_TPadronEmail] ce
	on ag.n_legajo = ce.n_legajo
where ag.n_legajo in (700058, 723204)
GO