package BEBuildWeek2.Epic_Energy_Services_CRM.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
	@Autowired
	private final ClienteService clienteService;

	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public List<Cliente> getAllClienti() {
		return clienteService.getAllClienti();
	}

	@GetMapping("/{clienteId}")
	public Cliente getClienteById(@PathVariable UUID clienteId) {
		return clienteService.getClienteById(clienteId);
	}
	
	@GetMapping(params = "fatturatoAnnuale")
	public ResponseEntity<List<Cliente>> getClientiByFatturato(@RequestParam("fatturatoAnnuale") Double fatturatoAnnuale) {
	    List<Cliente> clienti = clienteService.findClientiByFatturatoAnnuale(fatturatoAnnuale);
	    return ResponseEntity.ok(clienti);
	}
	
	@GetMapping(params = "dataInserimento")
    public ResponseEntity<List<Cliente>> getClientiByDataInserimento(@RequestParam("dataInserimento") String dataInserimento) throws ParseException {
        //salvo data in variabile
		String dateString = dataInserimento;
		//inizializzo la formattazione della data
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //applico la formattazione della data alla stringa per ottenere oggetto di tipo Date
        Date data = format.parse(dateString);
        
        List<Cliente> clientiByData = clienteService.findClientiByDataInserimento(data);
        return ResponseEntity.ok(clientiByData);
    }

    @GetMapping(params = "ultimoContatto")
    public ResponseEntity<List<Cliente>> getClientiByUltimoContatto(@RequestParam("ultimoContatto") String ultimoContatto) throws ParseException {
    	//salvo data in variabile
   		String dateString = ultimoContatto;
 		//inizializzo la formattazione della data
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //applico la formattazione della data alla stringa per ottenere oggetto di tipo Date
  	    Date data = format.parse(dateString);

        List<Cliente> clienti = clienteService.findClientiByDataUltimoContatto(data);
        return ResponseEntity.ok(clienti);
    }

    @GetMapping(params = "ragioneSociale")
    public ResponseEntity<List<Cliente>> findClientiByRagioneSociale(@RequestParam("ragioneSociale") String ragioneSociale) {
        List<Cliente> clienti = clienteService.findClientiByRagioneSociale(ragioneSociale);
        return ResponseEntity.ok(clienti);
    }
	
	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@PutMapping("/{clienteId}")
	public Cliente updateCliente(@PathVariable UUID clienteId, @RequestBody Cliente cliente) {
		return clienteService.updateCliente(clienteId, cliente);
	}

	@DeleteMapping("/{clienteId}")
	public void deleteCliente(@PathVariable UUID clienteId) {
		clienteService.deleteCliente(clienteId);
	}
}
