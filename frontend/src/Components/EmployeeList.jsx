import React, { useState, useEffect } from "react";
import { Modal } from "@mui/material";
import EmployeeCard from "./EmployeeCard";
import useEmployeeDetails from "../Hooks/useEmployeeDetails";
import { updateEmployee, disburseSalaries } from "../Utils/httputils";
import { toast, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const EmployeeList = () => {
  const { employees, loading, error, fetchEmployees } = useEmployeeDetails();
  const [selectedEmployees, setSelectedEmployees] = useState(new Set());
  const [openModal, setOpenModal] = useState(false);
  const [editedEmployee, setEditedEmployee] = useState(null);

  const filteredEmployees = employees; 
  const handleCheckboxChange = (event, employee) => {
    const updatedSelection = new Set(selectedEmployees);
    event.target.checked ? updatedSelection.add(employee) : updatedSelection.delete(employee);
    setSelectedEmployees(updatedSelection);
  };

  const handleModifyClick = (employee) => {
    setEditedEmployee({ ...employee });
    setOpenModal(true);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedEmployee((prev) => ({ ...prev, [name]: value }));
  };

  const handleFormSubmit = async () => {
    try {
      await updateEmployee(editedEmployee);
      fetchEmployees();
      setOpenModal(false);
      toast.success('Employee Modified', {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
        transition: Bounce,
      });
    } catch (error) {
      toast.error("Error updating employee: " + error.message, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
        transition: Bounce,
      });
    }
  };

  const handleDisburse = async () => {
    if (selectedEmployees.size > 0) {
      const employees = Array.from(selectedEmployees);
      const employeeIds = employees.map((employee) => employee.employee_id);
      await disburseSalaries(employeeIds);
      setSelectedEmployees(new Set());
      toast.success('Salary Disbursed', {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
        transition: Bounce,
      });
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p className="error">{error}</p>;

  return (
    <div>
        {filteredEmployees.map((employee) => (
          <EmployeeCard
            key={employee.employee_id}
            employee={employee}
            isSelected={selectedEmployees.has(employee)}
            onCheckboxChange={handleCheckboxChange}
            onModifyClick={handleModifyClick}
          />
        ))}

      <Modal open={openModal} onClose={() => setOpenModal(false)}>
      <div className="container" style={{ width: '400px', padding: '16px', backgroundColor: 'white', margin: 'auto', marginTop: '15%' }}>
          <h6>Edit Employee</h6>

          <div className="mb-3">
            <label htmlFor="first_name" className="form-label">First Name</label>
            <input 
              type="text" 
              id="first_name" 
              className="form-control" 
              name="first_name" 
              value={editedEmployee?.first_name || ""} 
              onChange={handleInputChange} 
              disabled 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="last_name" className="form-label">Last Name</label>
            <input 
              type="text" 
              id="last_name" 
              className="form-control" 
              name="last_name" 
              value={editedEmployee?.last_name || ""} 
              onChange={handleInputChange} 
              disabled 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input 
              type="email" 
              id="email" 
              className="form-control" 
              name="email" 
              value={editedEmployee?.email || ""} 
              onChange={handleInputChange} 
              disabled 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="title" className="form-label">Title</label>
            <input 
              type="text" 
              id="title" 
              className="form-control" 
              name="title" 
              value={editedEmployee?.title || ""} 
              onChange={handleInputChange} 
              disabled 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="salary" className="form-label">Salary</label>
            <input 
              type="number" 
              id="salary" 
              className="form-control" 
              name="salary" 
              value={editedEmployee?.salary || ""} 
              onChange={handleInputChange} 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="department" className="form-label">Department</label>
            <input 
              type="text" 
              id="department" 
              className="form-control" 
              name="department" 
              value={editedEmployee?.name || ""} 
              onChange={handleInputChange} 
              disabled 
            />
          </div>

          <button className="btn btn-primary" onClick={handleFormSubmit}>Save Changes</button>
        </div>

      </Modal>

      {selectedEmployees.size > 0 && (
        <div className="d-flex justify-content-end mb-2">
          <button className="btn btn-success" onClick={handleDisburse}>
            Disburse
          </button>
        </div>
      )}
    </div>
  );
};

export default EmployeeList;