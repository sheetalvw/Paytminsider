---------SCHEMA
create table Employee(
EmpID int PRIMARY KEY,
EmpName varchar(255),
Date_of_birth date 
 );
 create table Salary( 
salary Int, 
EmpID Int,
FOREIGN KEY (`EmpID`) REFERENCES `Employee` (`EmpID`)
 
);
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('1', 'Ramesh', Date '1993/03/03');
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('2', 'Suresh', Date '1197/04/01');

INSERT INTO Salary (salary, EmpId) VALUES (10000, 1);
INSERT INTO Salary(salary, EmpId) VALUES (20000, 2);


  create table Employee(
  EmpID int PRIMARY KEY,
  EmpName varchar(255),
  Date_of_birth date 
   )

  create table Salary( 
  salary Int, 
  EmpID Int,
  )

  INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('1', 'Ramesh', '1993/03/03')
  INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('2', 'Suresh', '1985/04/01')
  INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('3', 'Dinesh', '1988/03/03')
  INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES ('4', 'Mukesh', '1983/04/01')

  INSERT INTO Salary (salary, EmpId) VALUES (10000, 1)
  INSERT INTO Salary(salary, EmpId) VALUES (20000, 2)
  INSERT INTO Salary (salary, EmpId) VALUES (300000, 3)
  INSERT INTO Salary(salary, EmpId) VALUES (40000, 4)

----------------------ANSWERS:

a] Write an SQL query to find nth largest salary along with employee name.

SELECT T1.EmpName, T2.MAX_SAL
FROM Employee AS T1
INNER JOIN 
(
    SELECT TOP 1 B.EmpId,
          MAX(B.salary) AS MAX_SAL
    FROM Employee AS A
    INNER JOIN Salary AS B
      ON A.EmpID = B.EmpID
     GROUP BY B.EmpId
     ORDER BY 2 DESC
 ) AS T2
 ON T1.EmpId = T2.EmpID
 
B] Write a query to update salary of employees to 5000 whose age is 30+

UPDATE B
SET salary = 5000
FROM Employee AS A
INNER JOIN Salary AS B
ON A.EmpId= B.EmpID
where CAST(DATEDIFF(dd, Date_of_birth,GETDATE())/365.25 AS Int) >30;

Select * from Salary;

