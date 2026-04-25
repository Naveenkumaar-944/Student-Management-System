create database school;

use school;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20),
    student_name VARCHAR(50),
    student_department VARCHAR(50),
    student_gender VARCHAR(10),
    student_age VARCHAR(10)
);

DELIMITER $$
CREATE TRIGGER student_id_generator
BEFORE INSERT ON student
FOR EACH ROW
BEGIN
    DECLARE next_id INT;

    SELECT AUTO_INCREMENT
    INTO next_id
    FROM information_schema.TABLES
    WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'student';

    SET NEW.student_id =
        CONCAT('ST', YEAR(CURDATE()), LPAD(next_id,3,'0'));
END$$
DELIMITER ;

select * from student;
