Create Database Example;

use Example;

Create table Pokemons(
	id int(20) auto_increment PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Trainer varchar(255) NOT NULL
);

Desc Pokemons;

INSERT INTO Pokemons (Name, Trainer) VALUES
('Pikachu', 'Ash Ketchum'),
('Charmander', 'Ash Ketchum'),
('Bulbasaur', 'Ash Ketchum'),
('Squirtle', 'Ash Ketchum'),
('Eevee', 'Serena'),
('Jigglypuff', 'Misty'),
('Meowth', 'Jessie'),
('Psyduck', 'Misty'),
('Snorlax', 'Ash Ketchum'),
('Gengar', 'Goh');


delimiter $$
Create Procedure poke_list()
BEGIN
select * from Pokemons;
END ;
$$


CALL poke_list();

DELIMITER $$
Create procedure get_pokeByTrainers(IN trainerr varchar(255))
BEGIN
select * from Pokemons WHERE Trainer = trainerr;
END ;
$$
CALL get_pokeByTrainers('Misty');
$$

CREATE TABLE Student (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Marks INT NOT NULL,
    Percentage DECIMAL(5,2) NOT NULL
);
$$
INSERT INTO Student (Name, Marks, Percentage) VALUES
('Alice Johnson', 450, 90.00),
('Bob Smith', 395, 79.00),
('Charlie Brown', 410, 82.00),
('Diana Prince', 475, 95.00),
('Ethan Hunt', 360, 72.00);
$$
Select * from Student;

DELIMITER $$
Create Procedure compute_percentage(IN total_marks int, IN in_marks int, OUT Percent double)
BEGIN 
SET Percent = (in_marks / total_marks) * 100;
END;
$$
SET @percent = 0;
CALL compute_percentage(500, 499, @percent);
Select @percent as "Pecentage Scored";

Delimiter $$
Create Procedure Compute_percent_IfElse(IN total_marks int, IN in_marks int, OUT Percent double)
BEGIN
	IF in_marks > total_marks THEN
		SET Percent = 0;
	ELSE 
		SET Percent = (in_marks / total_marks) * 100;
	END IF;
END;
$$

SET @percent = 0;
CALL Compute_percent_IfElse(500, 600, @percent);
Select @percent;

$$
Create Procedure Compute_consumption(IN units int, OUT bill int)
BEGIN 
	IF units <= 200 THEN
		SET bill = units * 8;
	ELSEIF units > 200 THEN
		SET bill =  200 * 8 + (units - 200) * 10;
	ELSEIF units <= 10 THEN
		SET bill = 25 * 8;
	END IF;
END;
$$

set @total_bill = 0;
CALL Compute_consumption(300, @total_bill);
Select @total_bill;
$$


CREATE TABLE Employee (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Emp_name VARCHAR(100) NOT NULL,
    Dept VARCHAR(50) NOT NULL,
    Salary DECIMAL(10,2) NOT NULL
);
$$
INSERT INTO Employee (Emp_name, Dept, Salary) VALUES
('John Doe', 'HR', 50000.00),
('Jane Smith', 'IT', 75000.00),
('Robert Brown', 'Finance', 65000.00),
('Emily Davis', 'Marketing', 58000.00),
('Michael Scott', 'Sales', 62000.00);
$$

SET session sql_safe_updates = 0;
$$

$$
Create procedure update_salary(IN deptt varchar(255), IN percent double)
BEGIN 
	Update Employee SET Salary = Salary + (Salary * (percent / 100)) WHERE Dept = deptt;
END;

$$

CALL update_salary('HR', 2);
SET session sql_safe_updates = 1;

$$ 

Select * from Employee;


-- loops
DELIMITER $$
Create procedure While_loop(IN final_num int)
BEGIN
	declare i int DEFAULT 1;
    declare result varchar(255) default "";
    
    WHILE i<=final_num DO 
		SET result  = concat(result, " " , i);
        SET i = i + 1;
	END WHILE;
    Select result;
END
$$

CALL While_loop(5);

$$

DELIMITER $$

Create procedure Basic_loop(IN final_num int)
BEGIN
	declare i int DEFAULT 1;
    declare result varchar(255) default "";
    
    loop_lbl: LOOP
		SET result  = concat(result, " " , i);
        SET i = i + 1;
        
        IF i > final_num THEN
			LEAVE loop_lbl;
		END IF;
	END LOOP loop_lbl;
    Select result;
END;

$$

CALL Basic_loop(5);

$$

Create Procedure fetch_ids()
BEGIN 
	declare i int default 0;
    declare total_rows int;
    declare ids varchar(255) default "";
    declare result varchar(255) default "";
    Select count(ID) into total_rows from Employee;
    
    WHILE i<total_rows DO
		Select ID into ids from Employee LIMIT i,1;
        SET result = concat(result, " ", ids);
        SET i = i+1;
	END While;
    
    Select result;
END;
$$


CALL fetch_ids;

$$

Create Procedure increment_salaries(IN dept_name varchar(255), IN percent double)
BEGIN 
	Declare i int default 0;
    Declare total_rows int default 0;
    Declare ids int;
    
    Select count(ID) into total_rows from Employee;
    
    WHILE i<=total_rows DO
		Select ID into ids from Employee WHERE dept = dept_name LIMIT i,1;
        
        Update Employee SET salary = salary + (salary * (percent / 100)) WHERE ID = ids;
        
        SET i = i+1;
	END While;
END

$$

CALL increment_salaries("IT", 5);

$$ 
Select * from Employee;

Delimiter $$
create procedure get_salary_byId(INOUT myvar varchar(255))
BEGIN 
	select salary into myvar from Employee WHERE
	ID = myvar;
END
$$
SET @x = 5; -- pass in the id we want to get
CALL get_salary_byId(@x);
select @x as "Current Salary";

$$

CREATE TABLE Book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publication_house VARCHAR(100),
    category VARCHAR(50),
    book_count INT,
    status ENUM('IN STOCK', 'OUT_OF_STOCK')
);

$$

INSERT INTO Book (title, price, author, publication_house, category, book_count, status) VALUES
('The War Code', 399.99, 'James Hunt', 'Mcgraw Hill', 'WAR', 12, 'IN STOCK'),
('Fiction Fever', 299.50, 'Samantha Ray', 'DreamFolks', 'FICTION', 5, 'IN STOCK'),
('Laugh Out Loud', 199.99, 'Tom Hardy', 'Warner Bros', 'COMEDY', 8, 'OUT_OF_STOCK'),
('Champions of Sport', 349.00, 'Alex Morgan', 'Mcgraw Hill', 'SPORTS', 10, 'IN STOCK'),
('Dream Fiction', 420.75, 'Nina Fox', 'DreamFolks', 'FICTION', 7, 'OUT_OF_STOCK');
$$

DELIMITER $$
Create Procedure fetch_in_stock_values(IN value int)
BEGIN
	Select * from Book WHERE status = 'IN STOCK' AND price <= value;
END
$$

CALL fetch_in_stock_values(400);

DELIMITER $$

Create Procedure delete_specific_publications(IN publicationHouse varchar(255))
BEGIN 
	Declare i int default 0;
    Declare total_rows int default 0;
    Declare ids int;
    
	Select Count(id) into total_rows from Book;
    
    WHILE i<=total_rows DO
		Select id into ids from Book WHERE publication_house = publicationHouse LIMIT i,1;
        DELETE From Book Where id = ids;
        SET i = i+1;
	END WHILE;
END;
$$

CALL delete_specific_publications('Mcgraw Hill');

Select * from Book;
$$

Create Procedure update_price(IN categ varchar(255), IN percent double)
BEGIN 
	Declare i int default 0;
    Declare total_rows int default 0;
    Declare ids int;
    
    Select count(id) into total_rows from Book;
    
	WHILE i<=total_rows DO
		Select id into ids from Book WHERE category = categ LIMIT i,1;
        UPDATE Book SET price = price + (price * (percent / 100)) WHERE id = ids;
        SET i = i+1;
	END WHILE;
END;

$$

CALL update_price('FICTION', 5);
$$

-- Triggers

$$
Select * from Employee;
$$

Create table Emp_log(Id int PRIMARY KEY AUTO_INCREMENT, oldd double, neww double, datee Date, username varchar(255));

$$

$$
Create TRIGGER trigger_emp_update
BEFORE Update ON Employee
FOR EACH ROW 
BEGIN 
	INSERT INTO Emp_log(oldd, neww, datee, username)
	VALUES(old.salary, new.salary, now(), user());
END;
$$

Update Employee SET salary = 90000 Where ID = 5;

$$

Select * from emp_log;
$$


Select Employee.ID, dept, salary, neww, oldd from Employee JOIN emp_log ON
Employee.salary = emp_log.oldd;

-- Salary should not be greater than 90000
$$
Create TRIGGER salary_validation
BEFORE INSERT ON Employee
FOR EACH ROW 
BEGIN 
	IF NEW.salary > 90000 THEN
		SIGNAL SQLSTATE '45000'
		SET message_text = "ERROR, YOU CANNOT INSERT SALARY GREATER THAN 90000";
	END IF;
END;
$$

INSERT INTO Employee (emp_name, dept, salary) VALUES("Ash", "IT", 95000);

$$

create view view_employee AS select id, emp_name, dept from employee;
$$
Select * from view_employee;

$$

-- Create A view for the statistics of the Employee Table & Analysis Simulation

Create VIEW emp_stats AS select dept, count(id) from Employee GROUP BY dept;
$$
Select * from emp_stats;
$$
Create VIEW emp_analysis AS Select Max(salary), Min(salary), dept, count(id) from Employee GROUP BY salary, dept;
$$
Select * from Emp_analysis;
$$

Create Function getMaxSalary()
returns double
deterministic
BEGIN
	DECLARE maxs double;

    Select max(salary) into maxs From Employee;
    return maxs;
END;

$$

Select getMaxSalary() as "Max Salary";

$$

Create Function get_salary(e_id INT)
returns Double
DETERMINISTIC
BEGIN 
	DECLARE sal double;
    Select Salary into sal from Employee where ID = e_id;
	return sal;
END;
$$
Select get_salary(5);
    
    
