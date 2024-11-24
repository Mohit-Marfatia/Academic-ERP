import React, { useState } from "react";
import { login } from "../controller/AuthController";
import { parseErrorMessage } from "../utils/handleErrors";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    console.log("clicked")
    // e.preventDefault(); // Prevent page refresh
    try {
      const data = await login(email, password); // Call the Axios-based API
      localStorage.setItem("jwt", data.token);
      console.log("Login successful:", data);
      window.location.href = "/dashboard";
    } catch (err) {
      console.error("Login error:", err);
      setError(parseErrorMessage(err)); // Use error handling utility
    }
  };
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        handleLogin();
      }}
    >
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
        data-mdb-button-init
        data-mdb-ripple-init
        className="btn btn-primary btn-block mb-4 my-4 w-100"
        // onClick=
      >
        Login
      </button>
    </form>
  );
}

export default Login;
