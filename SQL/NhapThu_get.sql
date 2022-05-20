CREATE PROCEDURE NhapThu_get
(
@p_Ngay			VARCHAR(50),
@p_Ghi_Chu		VARCHAR(50),
@p_So_Tien		VARCHAR(50),
@p_Danh_Muc		VARCHAR(50),
@p_Sua			NVARCHAR(1)
)
AS

BEGIN
	IF @p_Sua = 'M'
		INSERT INTO dbo.receive_spend1
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
		UPDATE dbo.receive_spend1 SET date = @p_Ngay, amount = @p_So_Tien, type = @p_Danh_Muc
			WHERE note = @p_Ghi_Chu	
END