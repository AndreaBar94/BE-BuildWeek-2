import React, { useState } from "react";
import { Link } from "react-router-dom";
import logo from "../logo.svg";
const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

const SignIn = () => {

  const [formData, setFormData] = useState({
    username: "",
    name: "",
    surname: "",
    emailUtente: "",
    password: "",
  });

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleRegistrationSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:3142/auth/registration", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token,
        },
        body: JSON.stringify(formData),
      });
      const data = await response.json();

      if (response.status === 201) {
        alert("Registration successful!");
        console.log("Registration data: " + data);
      } else {
        alert("Error occurred with registration!");
      }
    } catch (error) {
      console.error("Registration error:", error);
    }
  };

  return (
    <>
      <div className="container mt-4 p-3 border border-3 border-primary rounded-4 shadow">
        <div className="d-flex justify-content-between">
          <h2>Registration</h2>
          <img className="logo " src={logo} alt="logo" />
        </div>
        <form onSubmit={handleRegistrationSubmit}>
          <div className="form-group">
            <label>Username:</label>
            <input
              required
              type="text"
              className="form-control shadow mb-3 border border-2 border-primary"
              name="username"
              placeholder="Enter username"
              value={formData.username}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Name:</label>
            <input
              required
              type="text"
              className="form-control shadow mb-3 border border-2 border-primary"
              name="name"
              placeholder="Enter name"
              value={formData.name}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Surname:</label>
            <input
              required
              type="text"
              className="form-control shadow mb-3 border border-2 border-primary"
              name="surname"
              placeholder="Enter surname"
              value={formData.surname}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input
              required
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
              required
              type="password"
              className="form-control shadow border border-2 border-primary"
              name="password"
              placeholder="Enter password (at least 8 characters, one digit, one letter, and one special character)"
              value={formData.password}
              onChange={handleInputChange}
            />
          </div>
          <button
            type="submit"
            className="btn-form btn btn-primary shadow mt-3"
          >
            Register
          </button>
        </form>
        <p className="mt-4">
          Return to{" "}
          <Link className="text-decoration-none fw-bold " to="/">
            Login Page
          </Link>
        </p>
      </div>
    </>
  );
};

export default SignIn;
