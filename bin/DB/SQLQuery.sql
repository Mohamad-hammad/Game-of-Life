create database db6;
use db6;

SET SQL_SAFE_UPDATES = 0;
SET GLOBAL log_bin_trust_function_creators = 1;

CREATE TABLE Save_State(
Save_id int PRIMARY KEY Not Null, 
Counter int, 
Box_row INT,
Box_Column INT
);

CREATE TABLE Box(
Box_num int,
Box_id int,
Status varchar(5),
Save_id int,
Box_ithPosition int,
Box_jthPosition int,
PRIMARY KEY(Box_num,Save_id)
);


DELIMITER //
CREATE PROCEDURE getindb_Save_State (
p_Save_id int, 
p_Counter int, 
p_Box_row int,
p_Box_Column INT)
BEGIN
INSERT INTO Save_State (Save_id, 
Counter, 
Box_row, Box_Column) VALUES (p_Save_id, 
p_Counter, 
p_Box_row, p_Box_Column);
END;
//
DELIMITER ;


DELIMITER //
CREATE FUNCTION RemoveSaveState ( RSaveID INT )
RETURNs int
BEGIN
   DECLARE StatusV int;
   IF exists(select * FROM Save_State where Save_id=RSaveID) THEN
      SET StatusV = 0;
      delete from Save_State where Save_id=RSaveID;
	  delete from Box where Save_id=RSaveID;
   ELSE
      SET StatusV = 2;
   END IF;
   RETURN StatusV;
END; //
DELIMITER ;
