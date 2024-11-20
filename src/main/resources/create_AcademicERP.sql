CREATE DATABASE IF NOT EXISTS AcademicERP;

USE AcademicERP;

CREATE Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    capacity INT
);

CREATE Employees(
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    title VARCHAR(255),
    photograph_path VARCHAR(255),
    salary DECIMAL(10,2),
    department INT
);

CREATE Employee_Salary(
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    description TEXT
);

CREATE Employee_Accounts(
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT, 
    credit_date DATE,
    employee_balance DECIMAL(10,2)
);

CREATE Employee_Auth(
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
)