import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link, Navigate, useNavigate } from "react-router-dom";
import logo from "../logo.svg";
const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

const RegistrationForm = () => {

    const [formData, setFormData] = useState({
        emailUtente: "",
        password: "",
    });

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setFormData({
        ...formData,
        [event.target.name]: event.target.value,
        });
    };

    const handleLoginSubmit = async (event) => {
        event.preventDefault();
    
        try {
        const response = await fetch("http://localhost:3142/auth/login", {
            method: "POST",
            headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
            },
            body: JSON.stringify(formData),
        });
        const data = await response.json();
            if (response.ok) {
                navigate("/Home");
            } else {
                alert(data.message);
            }
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div className="container mt-4 p-3 border border-3 border-primary rounded-4 shadow">
        <div className="d-flex justify-content-between">
            <h2>Login</h2>
            <img className="logo " src={logo} alt="logo" />
        </div>
        {/* {loginError && <p>Login failed. Please try again.</p>} */}
        <form onSubmit={handleLoginSubmit}>
            <div className="form-group">
            <label>Email:</label>
            <input
                type="email"
                className="form-control shadow mb-3 border border-2 border-primary"
                name="emailUtente"
                placeholder="Enter email"
                value={formData.emailUtente}
                onChange={handleInputChange}
            />
            </div>
            <div className="form-group">
            <label>Password:</label>
            <input
                type="password"
                className="form-control shadow border border-2 border-primary"
                name="password"
                placeholder="Enter password"
                value={formData.password}
                onChange={handleInputChange}
            />
            </div>
            <button type="submit" className="btn-form btn btn-primary mt-3 shadow">
            Login
            </button>
            <p className="mt-4">
            Don't have an account?{" "}
            <Link className="text-decoration-none fw-bold" to="/SignIn">
                Register
            </Link>
            </p>
        </form>
        </div>
    );
};

export default RegistrationForm;
