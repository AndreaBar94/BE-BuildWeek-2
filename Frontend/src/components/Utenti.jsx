import React, { useEffect, useState } from "react";
const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3ZW1haWxAZW1haWwuaXQiLCJpYXQiOjE2ODc1MjUzODcsImV4cCI6MTY4NzYxMTc4N30.MACVNIpW4UdtPfIXAyjLxAhWpltnfN78CfXM7MFYKek";
const Utenti = () => {
  const [utenti, setUtenti] = useState();

  useEffect(() => {
    fetchUtenti();
  }, []);

  const fetchUtenti = async () => {
    try {
      const response = await fetch("http://localhost:3142/utenti", {
        headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
        }
    }); // Inserisci l'URL corretto per ottenere i dati delle fatture
      const data = await response.json();
      setUtenti(data);
    } catch (error) {
      console.error("Errore durante il recupero degli utenti:", error);
    }
  };
  return (
    <div>
      <div className="row">
        <div className="col">emailUtente</div>
        <div className="col">nome</div>
        <div className="col">password</div>
        <div className="col">ruolo</div>
        <div className="col">surname</div>
        <div className="col">username</div>
      </div>
      {utenti && utenti.content && utenti.content.map((utente) => (
        <div key={utente.idUtente} className="row">
          <div className="col">{utente.emailUtente}</div>
          <div className="col">{utente.name}</div>
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
