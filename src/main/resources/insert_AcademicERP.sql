INSERT INTO Departments (department_id, name, capacity) VALUES 
(1, 'Computer Science', 10),
(2, 'Accounts', 5),
(3, 'MLAI', 10);

INSERT INTO Employees (employee_id, first_name, last_name, email, photograph_path, department) VALUES
(1, 'John','Doe','john.doe@academicuni.ac.in', 'Dean', '/images/john_doe.png', 1),
(2, 'Jane','Doe','jane.doe@academicuni.ac.in', 'Professor', '/images/jane_doe.png', 2),
(3, 'John','Smith','john.smith@academicuni.ac.in', 'Professor', '/images/john_smith.png', 3);

INSERT INTO Employees_Salary (id, employee_id, payment_date, amount, description) VALUES
(1, 1, '2024-11-20', 75000.00, 'October Month Salary'),
(2, 2, '2024-11-20', 40000.00, 'October Month Salary'),
(3, 3, '2024-11-20', 50000.00, 'October Month Salary');

INSERT INTO Employee_Accounts (id, employee_id, credit_date, employee_balance) VALUES 
(1, 1, '2024-11-20', 75000.00),
(2, 2, '2024-11-20', 40000.00),
(3, 3, '2024-11-20', 50000.00);
