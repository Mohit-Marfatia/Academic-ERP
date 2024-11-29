# ESD-Project: Salary Disbursement System

This project, developed as part of the **Enterprise Software Development (ESD)** coursework, focuses on creating a secure and user-friendly **Salary Disbursement System**. The system allows authenticated employees from the accounts department to manage and disburse salaries for individual faculty members or a group of faculty members.

## Project Objectives
- **User Authentication**: Ensure only authorized personnel can access and manage salary-related operations.
- **Salary Management**: Provide functionalities to view, modify, and disburse salaries.
- **User-Friendly Interface**: Develop an intuitive frontend for seamless interactions.

---

## Technology Stack
- **Frontend**: React, Axios, Bootstrap
- **Backend**: Spring Boot, JPA (Java Persistence API)
- **Database**: MySQL

---

## Application Architecture

### Backend (Spring Boot)

The backend follows a layered architecture to separate concerns and enhance maintainability:

#### 1. **Controller Layer**
   - **AuthController**: Handles user authentication and JWT generation.
   - **EmployeeController**: Manages employee-related requests, such as retrieving, updating, and disbursing salaries.

#### 2. **Service Layer**
   - **EmployeeService**: Implements business logic for employee management and salary disbursement.

#### 3. **Repository Layer** (JPA-based for database interaction)
   - **DepartmentsRepo**: Manages department-related data.
   - **EmployeeRepo**: Handles CRUD operations for employee records.
   - **EmployeeSalaryRepo**: Deals with salary records.

#### 4. **Model/Entity Layer**
   - **Departments**: Represents department details (fields: `department_id`, `capacity`, `name`).
   - **EmployeeAccounts**: Tracks employee account balances (fields: `employee_id`, `balance`).
   - **Employees**: Represents employee details (fields: `first_name`, `last_name`, `email`, `title`, `salary`, `photograph_path`, `password`, `department`).
   - **EmployeeSalary**: Logs salary disbursements (fields: `id`, `employee_id`, `payment_date`, `amount`, `description`).

---

## Frontend (React)

The frontend provides a seamless interface for managing salaries:

1. **User Login & JWT Authentication**:
   - Users log in with their credentials (email and password).
   - On successful authentication, a JWT token is generated and stored securely (local storage or cookies).
   - The token is included in subsequent requests to access protected resources.
   - Users are redirected to the login page if the token expires or becomes invalid.

2. **Employee Management**:
   - After logging in, users can view all employee profiles.
   - Employees belonging to the **Faculty** department have a checkbox next to their profiles.
   - Selecting faculty members enables the **Disburse** button, which allows salary disbursement for the selected individuals.

3. **Additional Features**:
   - **Registration**: Users can register new employees.
   - **Profile Modification**: Users can update existing employee details.

---

## Database Design

- **Departments**:
  - Fields: `department_id`, `capacity`, `name`, `department`
  
- **EmployeeAccounts**:
  - Fields: `employee_id`, `balance`
  
- **Employees**:
  - Fields: `first_name`, `last_name`, `email`, `title`, `salary`, `photograph_path`, `password`, `department`
  
- **EmployeeSalary**:
  - Fields: `id`, `employee_id`, `payment_date`, `amount`, `description`

---

## Setup Instructions

### Backend Setup
1. Clone the repository and navigate to the backend directory.
2. Configure the database connection in `application.properties`.
3. Run the SQL scripts in the `resources` folder (`create_AcademicERP.sql`, `insert_AcademicERP.sql`, and `alter_AcademicERP.sql`) to set up the MySQL database.
4. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run

### Frontend Setup
1. Navigate to the frontend directory.
2. Install dependencies:
   ```bash
   npm install
3. Run the project
   ```bash
   npm start

---

## Usage

1. **Login**: Access the system by logging in with valid credentials.
2. **View Employees**: Navigate to the employee list page to view all employee profiles.
3. **Disburse Salary**: Select faculty members and click the Disburse button to initiate salary payments.
4. **Manage Employees**: Modify existing employee salary as needed.