ALTER PROCEDURE NhapChi_get
(
@p_Ngay			date,
@p_Ghi_Chu		NCHAR(50),
@p_So_Tien		int,
@p_Danh_Muc		int,
@p_Sua			NVARCHAR(1)
)
AS

BEGIN
	IF @p_Sua = 'M'
		INSERT INTO dbo.receive_spend
			(
			date,
			note,
			amount,
			type
			) 
			VALUES
			(
			@p_Ngay,
			@p_Ghi_Chu,
			@p_So_Tien,
			@p_Danh_Muc
			)
	ELSE
		UPDATE dbo.receive_spend SET date = @p_Ngay, note = @p_Ghi_Chu, amount = @p_So_Tien, type = @p_Danh_Muc
			WHERE note = @p_Ghi_Chu	
END
