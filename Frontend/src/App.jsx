import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import Profile from "./components/profile";
import Navbar from "./components/Navbar";
import Home from "./components/Home";

function App() {
  return (
    <div id="all">
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route path="/profile" element={<Profile />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
