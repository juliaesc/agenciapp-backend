USE [AgenciApp_DB]
GO

ALTER VIEW [dbo].[v_Users]
AS
select cast(permisos.n_legajo as numeric(8)) as legajo,
	   t_Nombre as nombre,
	   t_Apellido as apellido,
       cast(RTRIM(b_nombreFantasia) as varchar(80)) as nombre_agencia,
       cast(RTRIM(t_email) as varchar(30)) as mail,
	   isnull(RTRIM(t_calle),'') + ' Nº ' + isnull(RTRIM(t_numero),'') + ' Localidad: ' + isnull(RTRIM(agencieros.t_localidad),'') + ' C.P.: ' + isnull(RTRIM(agencieros.c_codigopostal),'') as domicilio,
	   cast(c_comisionista as numeric(3)) as comisionista,
	   cast(n_cantterminales as numeric(3)) as cant_terminales,
	   cast(personas.n_cuit as numeric(15)) as cuit,
	   RTRIM(t_apyn) as titular_cuenta,
	   cast(RTRIM(t_tipocuenta) as varchar(20)) as tipo_cuenta,
	   cast(n_cuenta as numeric(15)) as n_cuenta,
	   cast(n_sucursal as numeric(8)) as sucursal_banco,
	   cast((t_CBU_1 + t_CBU_2) as varchar(50)) as cbu,
	   c_DebAut as tiene_DA,
	   cast(n_porcenIB as decimal(5,2)) as porc_IIBB
from PJuegos.dbo.bconcesionario maetate
inner join CoNcesionarios.dbo.BPermiso permisos
		on maetate.n_legajo = permisos.n_legajo
inner join CoNcesionarios.dbo.BTitular titulares
		on permisos.id_titular = titulares.id_titular
inner join CoNcesionarios.dbo.e_v_Personas personas
		on titulares.c_persona = personas.c_Persona
inner join CCorrientes.dbo.Agenciero agencieros
		on permisos.n_legajo = agencieros.n_legajo
inner join CCorrientes.dbo.TipoCuenta tipocuenta
		on agencieros.c_tipocuenta = tipocuenta.c_tipocuenta
inner join PadronMensajeria.dbo.TPadron padron
		on permisos.n_legajo = padron.n_legajo
inner join PadronMensajeria.dbo.TPadronEmail mails
		on padron.c_padron = mails.c_padron
where permisos.n_legajo between 700000 and 790000 and maetate.c_estado not IN ('C','J','G','R')
--Legajos con datos completos en Desarrollo en CoNcesionarios
and permisos.n_legajo in (700058,700083,700095,707090)
GO