import React, { useState } from "react";
import Navbar from "./Navbar";
import Fatture from "./Fatture";
import Utenti from "./Utenti";
import Clienti from "./Clienti";

const Home = () => {
  const [activeTab, setActiveTab] = useState("fatture");

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  return (
    <>
      <Navbar />
      <div className="d-flex justify-content-center my-3">
        <button
          className={`btn btn-primary me-2 ${
            activeTab === "fatture" ? "active" : ""
          }`}
          onClick={() => handleTabClick("fatture")}
        >
          Fatture
        </button>
        <button
          className={`btn btn-primary me-2 ${
            activeTab === "utenti" ? "active" : ""
          }`}
          onClick={() => handleTabClick("utenti")}
        >
          Utenti
        </button>
        <button
          className={`btn btn-primary ${
            activeTab === "clienti" ? "active" : ""
          }`}
          onClick={() => handleTabClick("clienti")}
        >
          Clienti
        </button>
      </div>
      <div className="container my-5">
        <div className="row">
          <div
            className={`col mx-3 p-3 border ${
              activeTab === "fatture" ? "" : "d-none"
            }`}
          >
            <h3>Fatture</h3>
            <Fatture />
          </div>

          <div
            className={`col mx-3 p-3 border ${
              activeTab === "utenti" ? "" : "d-none"
            }`}
          >
            <h3>Utenti</h3>
            <Utenti/>
          </div>

          <div
            className={`col mx-3 p-3 border ${
              activeTab === "clienti" ? "" : "d-none"
            }`}
          >
            <h3>Clienti</h3>
            <Clienti />
          </div>
        </div>
      </div>

      
    </>
  );
};

export default Home;
