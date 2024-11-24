import React from "react";
import Login from "../../components/Login"
import "./Login.css";

const LoginScreen = () => {
    return (
      <div className="Login">
        <section className="background-login overflow-hidden">
          <div className="overlay px-4 py-5 px-md-5 align-items-center d-flex text-lg-start min-vh-100">
            <div className="col-md-6 d-flex align-items-center justify-content-center mb-5 mb-lg-0">
              <h1 className="my-5 display-5 fw-bold ls-tight text-black glass">
                Please Login with your
                <br />
                <span className="text-primary">Account!</span>
              </h1>
            </div>
            <div className="row gx-lg-5 align-items-center mb-5 col-12">
              <div className="col-md-6 mb-5 mb-lg-0 position-relative">
                <div className="card bg-card">
                  <div className="card-body px-4 py-5 px-md-5">
                    <Login />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    );
  };
  
  export default LoginScreen;
  