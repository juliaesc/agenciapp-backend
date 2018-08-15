USE [AgenciApp_DB]
GO

ALTER PROC [dbo].[CC_LiquidacionPorJuegoEvento] @fecha_actual smalldatetime
AS

DECLARE @RC int, @CntFilas int

if @fecha_actual is null return -3

SELECT l.n_legajo as legajo,
	   n_cuenta as cuenta,
	   RTRIM(ex.t_explot) as juego,
	   ev.n_evento as sorteo,
	   l.f_vencimiento as vencimiento,
	   CASE WHEN l.i_Liquidacion > 0 THEN i_Liquidacion ELSE 0 END debitos,
	   CASE WHEN l.i_Liquidacion < 0 THEN i_Liquidacion * -1 ELSE 0 END creditos,
	   isnull(l.i_interes, 0.00) as interes,
	   el.t_estadoliquidacion as estado,
	   tp.t_tipomoneda as moneda,
	   CASE m.c_operatoria
			WHEN 'DA' THEN 'Débito Automático'
			WHEN 'CA' THEN 'Crédito Automático'
			WHEN 'CT' THEN 'Contra asiento (Corrección)'
			WHEN 'LI' THEN 'Liquidación individual'
			WHEN 'LV' THEN 'Liquidación por vencimiento'
			ELSE '' END as tipo,
	   f_macroliquidacion as fecha_macroliquidacion
FROM CCorrientes.dbo.v_Liquidacion l
INNER JOIN CCorrientes.dbo.v_agenciero a ON l.n_legajo = a.n_legajo
INNER JOIN CCorrientes.dbo.v_EstadoLiquidacion el ON l.c_estadoliquidacion = el.c_estadoliquidacion
INNER JOIN CCorrientes.dbo.v_TipoMoneda tp ON l.c_tipomoneda = tp.c_tipomoneda
INNER JOIN CCorrientes.dbo.v_Explotacion ex ON l.c_explot = ex.c_explot
INNER JOIN CCorrientes.dbo.v_Evento ev ON l.f_Evento = ev.f_evento and l.c_explot = ev.c_explot
INNER JOIN CCorrientes.dbo.v_MacroliquidacionLiquidacion ml ON l.c_Liquidacion = ml.c_Liquidacion
INNER JOIN CCorrientes.dbo.v_MacroLiquidacion m ON ml.c_MacroLiquidacion = m.c_MacroLiquidacion
WHERE m.c_operacion in (SELECT distinct c_operacion as operaciones FROM CCorrientes.dbo.v_MacroLiquidacion
where CONVERT(DATE,f_macroliquidacion) = @fecha_actual and c_operatoria in ('DA', 'CA', 'CT', 'LI', 'LL', 'LV'))

SELECT @rc = @@error,@CntFilas = @@RowCount

if @rc <> 0	return @rc
if @CntFilas = 0 return -501
GO