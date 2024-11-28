import React from "react";
import { Button } from "@mui/material"; // Import MUI Button for better styling
import { useNavigate } from "react-router-dom"; // For React Router v6
import { ToastContainer, toast, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import EmployeeList from "./Components/employeeList";

const Dashboard = () => {
  // Use navigate instead of history in React Router v6
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear authentication state (e.g., tokens, user data)
    localStorage.removeItem("jwt"); // Example for token removal

    // Redirect to the login page after logout
    navigate("/");
  };

  return (
    <div className="dashboard p-0">
      <nav class="navbar navbar-expand-lg">
        <div class="container">
          <a class="navbar-brand">Academic-ERP</a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div
            class="collapse navbar-collapse justify-content-end"
            id="navbarNav"
          >
            <ul class="navbar-nav ml-500">
              <li class="nav-item">
              <button type="button" class="btn" onClick={handleLogout}>Logout</button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="p-3 my-5">
        <EmployeeList />
      </div>
    </div>
  );
};

export default Dashboard;
