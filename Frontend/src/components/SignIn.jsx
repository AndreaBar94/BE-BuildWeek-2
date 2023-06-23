import React, { useState } from 'react'
import { Link } from 'react-router-dom';
const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9';

const SignIn = () => {
  const [signIn, setSignIn] = useState(false);
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

  // const renderAlert = () => {
  //   if (signIn) {
  //     return alert('Registration successful!');
  //   } else {
  //     return alert('Error occurred with registration!');
  //   }
  // };

  const handleRegistrationSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:3142/auth/registration', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + token,
        },
        body: JSON.stringify(formData),
      });
      const data = await response.json();

      if (response.status === 201) {
        alert('Registration successful!');
        console.log('Registration data: ' + data);
      } else {
        alert('Error occurred with registration!');
      }
    } catch (error) {
      console.error('Registration error:', error);
    }
  };

  return (
    <>
    <div className="container">
      <h2>Registration</h2>
      <form onSubmit={handleRegistrationSubmit}>
        <div className="form-group">
          <label>Username:</label>
          <input
          required
            type="text"
            className="form-control"
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
            className="form-control"
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
            className="form-control"
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
          required
            type="password"
            className="form-control"
            name="password"
            placeholder="Enter password (at least 8 characters, one digit, one letter, and one special character)"
            value={formData.password}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit" className="btn btn-primary mt-2">
          Register
        </button>
      </form>
      <p>
          Return to <Link to="/">Login Page</Link>
        </p>
      </div>
    </>
    
  )
}

export default SignIn;