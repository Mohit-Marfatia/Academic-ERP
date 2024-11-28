import React, { useState } from "react";
import { loginUser } from "../Utils/httputils";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault(); // Prevent default form submission
    try {
      const data = await loginUser(email, password);
      localStorage.setItem("jwt", data.token);
      console.log("Login successful:", data);
      navigate("/dashboard");
    } catch (err) {
      console.error("Login error:", err);

      // Safely set the error message
      const errorMessage =
        err.response?.data?.message || err.message || "Login failed. Please try again.";
      setError(errorMessage);
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <div className="form-floating mb-3">
        <input
          type="email"
          className="form-control"
          id="floatingInput"
          placeholder="name@example.com"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <label htmlFor="floatingInput">Email address</label>
      </div>
      <div className="form-floating">
        <input
          type="password"
          className="form-control"
          id="floatingPassword"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <label htmlFor="floatingPassword">Password</label>
      </div>

      {error && <p className="text-danger mt-3">{error}</p>}

      <button
        type="submit"
        className="btn btn-primary btn-block mb-4 my-4 w-100"
      >
        Login
      </button>
    </form>
  );
}

export default Login;
