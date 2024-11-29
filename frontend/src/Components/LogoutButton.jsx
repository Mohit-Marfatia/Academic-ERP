import React from "react";
import { useNavigate } from "react-router-dom";

const LogoutButton = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("sessionId");
        navigate("/");
    };

    return <button type="button" className="btn text-white" onClick={handleLogout}>Logout</button>
};

export default LogoutButton;