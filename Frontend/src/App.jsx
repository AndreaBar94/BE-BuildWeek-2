import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Profile from './components/profile';
import Home from './components/Home';
import SignIn from './components/SignIn';


function App() {

  return (
    <div id="all">
      <BrowserRouter>
        
        <Routes>
          <Route path="/" element={<Login  />} />

            <>
              <Route path="/profile" element={<Profile />} />
              <Route path="/Home" element={<Home />} />
              <Route path="/SignIn" element={<SignIn />} />
            </>

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
