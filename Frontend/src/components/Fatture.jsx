import React, { useState, useEffect } from 'react';

const Fatture = () => {
  const [fatture, setFatture] = useState([]);

  useEffect(() => {
    fetchFatture();
  }, []);

  const fetchFatture = async () => {
    try {
      const response = await fetch('API_URL'); // Inserisci l'URL corretto per ottenere i dati delle fatture
      const data = await response.json();
      setFatture(data);
    } catch (error) {
      console.error('Errore durante il recupero delle fatture:', error);
    }
  };

  return (
    <div>
      <div className="row">
        <div className="col">ID</div>
        <div className="col">Anno</div>
        <div className="col">Data</div>
        <div className="col">Importo</div>
        <div className="col">Numero Fattura</div>
        <div className="col">Stato</div>
        <div className="col">ID Cliente</div>
      </div>
      {fatture.map((fattura) => (
        <div key={fattura.id} className="row">
          <div className="col">{fattura.id}</div>
          <div className="col">{fattura.anno}</div>
          <div className="col">{fattura.data}</div>
          <div className="col">{fattura.importo}</div>
          <div className="col">{fattura.numeroFattura}</div>
          <div className="col">{fattura.stato}</div>
          <div className="col">{fattura.idCliente}</div>
        </div>
      ))}
    </div>
  );
};

export default Fatture;
