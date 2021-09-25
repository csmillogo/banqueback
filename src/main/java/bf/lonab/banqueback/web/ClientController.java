package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Client;
import bf.lonab.banqueback.metier.IClientMetier;
import bf.lonab.banqueback.utilitaire.GestionDesExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
public class ClientController {
	
	@Autowired
	private IClientMetier iCltM;
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/client")
	public String creer(@RequestBody Client c) throws JsonProcessingException{
		Reponse<Client> reponse;
		try {
			Client clt = iCltM.creer(c);			
			List<String> message = new  ArrayList<>();
			message.add(String.format("%s a été creé avec succès.", clt.getId()));
			reponse = new Reponse<Client>(0,message,clt);
			
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Client>(3, GestionDesExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	
	@PutMapping("/client")
	public String modifier(@RequestBody Client c) throws JsonProcessingException {
		Reponse<Client> reponse = null;
		Client clt = iCltM.trouver(c.getId());
		List<String> message = new  ArrayList<>();
		if(clt!=null) {
			try {
				Client c2 = iCltM.modifier(c);
				message.add(String.format("%s a été modifier avec succès.", clt.getId()));
				reponse = new Reponse<Client>(0,message,clt);
			} catch (Exception e) {
				// TODO: handle exception
				reponse = new Reponse<Client>(3, GestionDesExceptions.getErrorForException(e),null);
			}
		}else {
			message.add(String.format("Client inexistant"));
			reponse = new Reponse<Client>(3, message,null);
		}
		
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/client")
	public String lister() throws JsonProcessingException{
		Reponse<List<Client>> reponse;
		try {
			List<Client> listClients = iCltM.lister();
			if(listClients!=null && !listClients.isEmpty()) {
				reponse = new Reponse<List<Client>>(0,null,listClients);
			}else {
				List<String> message = new  ArrayList<>();
				message.add("Pas de client enregistré!");
				reponse = new Reponse<List<Client>>(1,message,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<List<Client>>(3, GestionDesExceptions.getErrorForException(e),null);
		}		
		return mapper.writeValueAsString(reponse);
	}
	
	@DeleteMapping("/client/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		Reponse<Boolean> reponse = null;
		try {
			reponse = new Reponse<Boolean>(0,null,iCltM.supprimer(id));
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Boolean>(3, GestionDesExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
