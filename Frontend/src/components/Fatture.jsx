import React, { useState, useEffect } from 'react';
const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3ZW1haWxAZW1haWwuaXQiLCJpYXQiOjE2ODc1MjUzODcsImV4cCI6MTY4NzYxMTc4N30.MACVNIpW4UdtPfIXAyjLxAhWpltnfN78CfXM7MFYKek";

const Fatture = () => {
  const [fatture, setFatture] = useState();

  useEffect(() => {
    fetchFatture();
  }, []);

  const fetchFatture = async () => {
    try {
        const response = await fetch("http://localhost:3142/fatture", {
            headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
            }
        });
        const data = await response.json();
        setFatture(data);
    } catch (error) {
      console.error('Errore durante il recupero delle fatture:', error);
    }
  };

  return (
    <div>
      <div className="row">
        <div className="col">Anno</div>
        <div className="col">Data</div>
        <div className="col">Importo</div>
        <div className="col">Numero Fattura</div>
        <div className="col">Stato</div>
      </div>
      {fatture && fatture.map((fattura) => (
        <div key={fattura.idFattura} className="row">
          <div className="col">{fattura.anno}</div>
          <div className="col">{fattura.data}</div>
          <div className="col">{fattura.importo}</div>
          <div className="col">{fattura.numeroFattura}</div>
          <div className="col">{fattura.state}</div>
        </div>
      ))}
    </div>
  );
};

export default Fatture;
