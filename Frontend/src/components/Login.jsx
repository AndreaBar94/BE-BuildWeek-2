import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    username: '',
    name: '',
    surname: '',
    emailUtente: '',
    password: '',
  });

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleRegistrationSubmit = (event) => {
    event.preventDefault();

    fetch('http://localhost:3142/auth/registration', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + token,
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the registration response data
        alert('registration ok!');
        console.log('Registration data:', data);
      })
      .catch((error) => {
        // Handle registration errors
        console.error('Registration error:', error);
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
        // Handle the login response data
        alert('Login ok!');
        console.log('Login data:', data);
      })
      .catch((error) => {
        // Handle login errors
        console.error('Login error:', error);
      });
  };

  return (
    <div className="container">
      <h2>Registration</h2>
      <form onSubmit={handleRegistrationSubmit}>
        <div className="form-group">
          <label>Username:</label>
          <input
            type="text"
            className="form-control"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Surname:</label>
          <input
            type="text"
            className="form-control"
            name="surname"
            value={formData.surname}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            className="form-control"
            name="emailUtente"
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
            value={formData.password}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Register
        </button>
      </form>

      <h2>Login</h2>
      <form onSubmit={handleLoginSubmit}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            className="form-control"
            name="email"
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
            value={formData.password}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Login
        </button>
      </form>
    </div>
  );
};

export default RegistrationForm;
