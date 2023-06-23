import React, { useEffect, useState } from "react";
const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3ZW1haWxAZW1haWwuaXQiLCJpYXQiOjE2ODc1MjUzODcsImV4cCI6MTY4NzYxMTc4N30.MACVNIpW4UdtPfIXAyjLxAhWpltnfN78CfXM7MFYKek";
const Clienti = () => {
  const [clienti, setClienti] = useState();

  useEffect(() => {
    fetchClienti();
  }, []);

  const fetchClienti = async () => {
    try {
      const response = await fetch("http://localhost:3142/clienti", {
        headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
        }
    });// Inserisci l'URL corretto per ottenere i dati delle fatture
      const data = await response.json();
      setClienti(data);
    } catch (error) {
      console.error("Errore durante il recupero dei clienti:", error);
    }
  };

  return (
    <div>
      <div className="row">
        <div className="col">Cognome</div>
        <div className="col">DataInserimento</div>
        <div className="col">dataUltimoContatto</div>
        <div className="col">emailCliente</div>
        <div className="col">emailUtente</div>
        <div className="col">fatturatoAnnuale</div>
        <div className="col">nome</div>
        <div className="col">partitaIva</div>
        <div className="col">pec</div>
        <div className="col">ragioneSociale</div>
        <div className="col">telefono</div>
        <div className="col">tipoCliente</div>
      </div>
      {clienti && clienti.content && clienti.content.map((cliente) => (
        <div key={cliente.idCliente} className="row">
          <div className="col">{cliente.cognome}</div>
          <div className="col">{cliente.dataInserimento}</div>
          <div className="col">{cliente.dataUltimoContatto}</div>
          <div className="col">{cliente.emailCliente}</div>
          <div className="col">{cliente.emailUtente}</div>
          <div className="col">{cliente.fatturatoAnnuale}</div>
          <div className="col">{cliente.nome}</div>
          <div className="col">{cliente.partitaIva}</div>
          <div className="col">{cliente.pec}</div>
          <div className="col">{cliente.ragioneSociale}</div>
          <div className="col">{cliente.telefono}</div>
          <div className="col">{cliente.tipoCliente}</div>
        </div>
      ))}
    </div>
  );
};

export default Clienti;
