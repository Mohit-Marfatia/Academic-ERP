import React from "react";
import 'react-toastify/dist/ReactToastify.css';

import LogoutButton from "../../Components/LogoutButton";
import EmployeeList from "../../Components/EmployeeList";

const Dashboard = () => {

  return (
    <div className="dashboard p-0">
      <nav className="navbar navbar-expand-lg navbar-light bg-dark">
        <div className="container">
          <p className="navbar-brand text-white">Academic-ERP</p>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div
            className="collapse navbar-collapse justify-content-end"
            id="navbarNav"
          >
            <ul className="navbar-nav">
              <li className="nav-item">
              <LogoutButton />
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="p-3 my-5">
        <h2 className="mb-5">Select the employees to disburse salary:</h2>
        <EmployeeList />
      </div>
    </div>
  );
};

export default Dashboard;
