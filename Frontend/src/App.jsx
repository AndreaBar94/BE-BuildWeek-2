import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Profile from './components/profile';
import Navbar from './components/Navbar';
import Home from './components/Home';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    // Effettua l'accesso e imposta isLoggedIn a true
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    // Effettua il logout e imposta isLoggedIn a false
    setIsLoggedIn(false);
  };

  return (
    <div id="all">
      <BrowserRouter>
        <Navbar isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
        <Routes>
          <Route path="/" element={<Login handleLogin={handleLogin} />} />
          {isLoggedIn ? (
            <>
              <Route path="/profile" element={<Profile />} />
              <Route path="/home" element={<Home />} />
            </>
          ) : (
            <Route path="/*" element={<Navigate to="/" replace={true} />} />
          )}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
