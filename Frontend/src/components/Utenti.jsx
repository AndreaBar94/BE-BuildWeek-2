import React, { useEffect, useState } from "react";

const Utenti = () => {
  const [utenti, setUtenti] = useState([]);

  useEffect(() => {
    fetchUtenti();
  }, []);

  const fetchUtenti = async () => {
    try {
      const response = await fetch("http://localhost:3165/auth/utenti"); // Inserisci l'URL corretto per ottenere i dati delle fatture
      const data = await response.json();
      setUtenti(data);
    } catch (error) {
      console.error("Errore durante il recupero degli utenti:", error);
    }
  };
  return (
    <div>
      <div className="row">
        <div className="col">ID</div>
        <div className="col">emailUtente</div>
        <div className="col">nome</div>
        <div className="col">password</div>
        <div className="col">ruolo</div>
        <div className="col">surname</div>
        <div className="col">username</div>
      </div>
      {utenti.map((utente) => (
        <div key={utente.id} className="row">
          <div className="col">{utente.id}</div>
          <div className="col">{utente.emailUtente}</div>
          <div className="col">{utente.nome}</div>
          <div className="col">{utente.password}</div>
          <div className="col">{utente.ruolo}</div>
          <div className="col">{utente.surname}</div>
          <div className="col">{utente.username}</div>
        </div>
      ))}
    </div>
  );
};

export default Utenti;
