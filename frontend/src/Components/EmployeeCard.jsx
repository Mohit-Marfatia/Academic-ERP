import React from "react";

const EmployeeCard = ({ employee, isSelected, onCheckboxChange, onModifyClick }) => {

  const getInitials = (name) => {
    const initials = name
      .split(" ")
      .map((n) => n[0])
      .join("");
    return initials.toUpperCase();
  };

  return (
    <div className="card mb-3 shadow-sm p-3">
      <div className="row align-items-center">
        <div className="col-auto">
          <input
            type="checkbox"
            className="form-check-input"
            checked={isSelected}
            onChange={(event) => onCheckboxChange(event, employee)}
          />
        </div>

        <div className="col-auto">
          <div
            className="d-flex justify-content-center align-items-center rounded-circle bg-primary text-white"
            style={{
              width: "50px",
              height: "50px",
              fontSize: "18px",
              fontWeight: "bold",
            }}
          >
            {getInitials(employee.fullName())}
          </div>
        </div>

        <div className="col">
          <h5 className="mb-1">{employee.fullName()}</h5>
          <p className="mb-0 text-muted">
            {employee.name} | {employee.title} | {employee.email}
          </p>
        </div>

        <div className="col-auto">
          <button
            className="btn btn-outline-primary"
            onClick={() => onModifyClick(employee)}
          >
            Modify
          </button>
        </div>
      </div>
    </div>
  );
};

export default EmployeeCard;
