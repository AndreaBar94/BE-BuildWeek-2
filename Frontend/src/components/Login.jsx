import React, { useState } from 'react';
const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9';
const RegistrationForm = () => {
    const [formData, setFormData] = useState({
      username: '',
      name: '',
      surname: '',
      email: '',
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
        'Authorization': 'Bearer ' + token
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the registration response data
        alert('registration ok!')
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
        'Authorization': 'Bearer ' + token
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the login response data
        alert('Login ok!')
        console.log('Login data:', data);
      })
      .catch((error) => {
        // Handle login errors
        console.error('Login error:', error);
      });
  };

  return (
    <div>
     <h2>Registration</h2>
      <form onSubmit={handleRegistrationSubmit}>
        <label>
          Username:
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
          Surname:
          <input
            type="text"
            name="surname"
            value={formData.surname}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
          Email:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <button type="submit">Register</button>
      </form>

      <h2>Login</h2>
      <form onSubmit={handleLoginSubmit}>
        <label>
          Email:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default RegistrationForm;
