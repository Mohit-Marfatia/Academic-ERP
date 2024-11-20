USE AcademicERP;

ALTER TABLE Employees
ADD CONSTRAINT fk_employees_dempartment
FOREIGN KEY (department)
REFRENCES Departments(department_id);

ALTER TABLE Employee_Salary
ADD CONSTRAINT fk_employees_salary
FOREIGN KEY (employee_id)
REFRENCES Employees(employee_id);

ALTER TABLE Employee_Accounts
ADD CONSTRAINT fk_employees_accounts
FOREIGN KEY (employee_id)
REFRENCES Employees(employee_id);

ALTER TABLE Employee_Auth
ADD CONSTRAINT fk_employees_auth
FOREIGN KEY (email)
REFRENCES Employees(email);