USE [AgenciApp_DB]
GO

CREATE PROCEDURE [dbo].[CC_ConsultarEstadoCuentaCorrientePorLegajo] @legajo int, @fecha_actual date
AS

declare @RC int
if @legajo is null return -1
if @fecha_actual is null return -2
EXEC @rc = [CCorrientes].[dbo].[CC_VerTotalImpagosPorLegajoVencimiento] @legajo, 0, @fecha_actual
if @rc <> 0 return @rc

GO