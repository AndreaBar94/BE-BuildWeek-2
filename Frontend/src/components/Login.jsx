import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, Navigate, useNavigate } from 'react-router-dom';

const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9';

const RegistrationForm = () => {
        const [loginError, setLoginError] = useState(false);
        const [isLoggedIn, setLoggedIn] = useState(false);
        const [formData, setFormData] = useState({
            emailUtente: '',
            password: ''
        });

        const navigate = useNavigate();

        const handleInputChange = (event) => {
            setFormData({
            ...formData,
            [event.target.name]: event.target.value,
            });
        };

        const handleLoginSubmit = (event) => {
            event.preventDefault();

        fetch('http://localhost:3142/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + token,
        },
        body: JSON.stringify(formData),
        })
        .then((response) => response.json())
        .then((data) => {
            if (data.ok) {
                setLoggedIn(true);
                navigate("/Home");
            } else {
            setLoginError(true);
            }
        })
        .catch((error) => {
            console.error('Login error:', error);
            setLoginError(true);
        });
    };

    

    if(isLoggedIn === true) {
                    return <Navigate to="/Home" />
                };

    return (
        <div className="container">
        
        <div className="container">
        <h2>Login</h2>
        {/* {loginError && <p>Login failed. Please try again.</p>} */}
        <form onSubmit={handleLoginSubmit}>
            <div className="form-group">
            <label>Email:</label>
            <input
                type="email"
                className="form-control"
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
                className="form-control"
                name="password"
                placeholder="Enter password"
                value={formData.password}
                onChange={handleInputChange}
            />
            </div>
                <button type="submit" className="btn btn-primary mt-2">
            Login
            </button>
            <p>
            Don't have an account? <Link to="/SignIn">Register</Link>
            </p>
        </form>
        </div>
        </div>
    );
};

export default RegistrationForm;
