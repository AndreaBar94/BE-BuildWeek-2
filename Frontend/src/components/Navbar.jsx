import React, { useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const Navbar = () => {
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);

  const toggleSettings = () => {
    setIsSettingsOpen(!isSettingsOpen);
  };

  return (
  <div className="text-center mb-5">
        <Link className="navbar-brand p-3 me-3" to="/home">
        Home
        </Link>

        <Link className="navbar-brand p-3" to="/profile">
        Profile
        </Link>
  </div>

  );
};

export default Navbar;
