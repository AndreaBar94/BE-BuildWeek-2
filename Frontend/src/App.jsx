import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Profile from './components/profile';
import Navbar from './components/Navbar';
import Home from './components/Home';
import SignUp from './components/SignUp';

function App() {

  return (
    <div id="all">
      <BrowserRouter>
        
        <Routes>
          <Route path="/" element={<Login  />} />

            <>
              <Route path="/profile" element={<Profile />} />
              <Route path="/home" element={<Home />} />
              <Route path="/SignUp" element={<SignUp />} />
            </>

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
