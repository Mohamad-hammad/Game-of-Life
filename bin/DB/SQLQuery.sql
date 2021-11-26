
CREATE TABLE Save_State(
Save_id int PRIMARY KEY, 
Counter int, 
Box_row INT,
Box_Column INT
);

CREATE TABLE Box(
Box_num int,
Box_id int,
Status varchar(5),
Save_id int,
PRIMARY KEY(Box_num,Save_id)
);

CREATE PROCEDURE getindb_Box
@mystring varchar(18)
AS
BEGIN
DECLARE @i int
SELECT @i = LEN(@mystring)
DECLARE @j int = 0
DECLARE @lastsave int
if(EXISTS (Select * from Box))
BEGIN
SELECT @lastsave = Save_id FROM Box Where Save_id=(SELECT max(Save_id) FROM Box)
SET @lastsave = @lastsave + 1
END
ELSE
BEGIN 
SET @lastsave = 1
END 
WHILE(@j<@i)
BEGIN
SET @j= @j+1
DECLARE @box_id varchar(1)
Declare @box_id_int int
SELECT @box_id = substring(@mystring, @j, @j)
SELECT @box_id_int = CAST(@box_id AS int)
IF(@box_id_int=1)
BEGIN
INSERT INTO Box(Box_num,Box_id,
Status,
Save_id)values(@j, @box_id_int, 'ALIVE', @lastsave )
END
ELSE
BEGIN
INSERT INTO Box(Box_num,Box_id,
Status,
Save_id)values(@j, @box_id_int, 'DEAD', @lastsave )END
END
END

EXEC getindb_Box @mystring = '010111101011100101';

SELECT * FROM Box ORDER BY Save_id, Box_num

CREATE PROCEDURE getindb_Save_State
@Save_id int, 
@Counter int, 
@Box_row int,
@Box_Column INT
AS 
BEGIN
INSERT INTO Save_State (Save_id, 
Counter, 
Box_row, Box_Column) VALUES (@Save_id, 
@Counter, 
@Box_row, @Box_Column)
END

SELECT * FROM Save_State

CREATE PROCEDURE Update_Counter
@Counter int,
@Save_id int
AS
BEGIN 
Update Save_State
SET Counter = @Counter
WHERE Save_id = @Save_id
END

EXEC Update_Counter @Counter = 20, @Save_id = 1

CREATE PROCEDURE Update_Grid_size
@Grid_size int,
@Save_id int
AS
BEGIN 
Update Save_State
SET Grid_size = @Grid_size
WHERE Save_id = @Save_id
END

EXEC Update_Grid_size @Grid_size = 30, @Save_id = 1


INSERT INTO Save_State (Save_id, 
Counter, 
Box_row, Box_Column) VALUES (1, 2, 6, 3)

