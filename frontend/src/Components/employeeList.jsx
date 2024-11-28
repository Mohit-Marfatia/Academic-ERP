import React, { useState, useEffect } from "react";
import { List, Modal, Box, Typography, TextField, Button } from "@mui/material";
import EmployeeCard from "./EmployeeCard";
import useEmployeeDetails from "../Hooks/useEmployeeDetails";
import { updateEmployee, disburseSalaries } from "../Utils/httputils";
import { ToastContainer, toast, Bounce } from 'react-toastify';
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
      alert("Error updating employee: " + error.message);
    }
  };

  // Handle disburse action
  const handleDisburse = () => {
    if (selectedEmployees.size > 0) {
      const employees = Array.from(selectedEmployees);
      const employeeIds = employees.map((employee) => employee.employee_id);
      disburseSalaries(employeeIds);
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
      {/* <List> */}
        {filteredEmployees.map((employee) => (
          <EmployeeCard
            key={employee.employee_id}
            employee={employee}
            isSelected={selectedEmployees.has(employee)}
            onCheckboxChange={handleCheckboxChange}
            onModifyClick={handleModifyClick}
          />
        ))}
      {/* </List> */}

      {/* Modal */}
      <Modal open={openModal} onClose={() => setOpenModal(false)}>
        <Box sx={{ width: 400, p: 4, bgcolor: "background.paper", margin: "auto", mt: "15%" }}>
          <Typography variant="h6">Edit Employee</Typography>
          <TextField disabled name="first_name" label="First Name" fullWidth margin="normal" value={editedEmployee?.first_name || ""} onChange={handleInputChange} />
          <TextField disabled name="last_name" label="Last Name" fullWidth margin="normal" value={editedEmployee?.last_name || ""} onChange={handleInputChange} />
          <TextField disabled name="email" label="Email" fullWidth margin="normal" value={editedEmployee?.email || ""} onChange={handleInputChange} />
          <TextField disabled name="title" label="Title" fullWidth margin="normal" value={editedEmployee?.title || ""} onChange={handleInputChange} />
          <TextField name="salary" label="Salary" fullWidth margin="normal" value={editedEmployee?.salary || ""} onChange={handleInputChange} />
          <TextField disabled name="department" label="Department" fullWidth margin="normal" value={editedEmployee?.department || ""} onChange={handleInputChange} />
          <Button variant="contained" color="primary" onClick={handleFormSubmit}>Save Changes</Button>
        </Box>
      </Modal>

      {/* Disburse Button */}
      {selectedEmployees.size > 0 && (
        <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginBottom: 2 }}>
          <Button variant="contained" color="success" onClick={handleDisburse}>
            Disburse
          </Button>
        </Box>
      )}
    </div>
  );
};

export default EmployeeList;
