package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Compte;
import bf.lonab.banqueback.metier.ICompteMetier;
import bf.lonab.banqueback.utilitaire.GestionDesExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
public class CompteController {
	
	@Autowired
	private ICompteMetier icm;
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/compte")
	public String creer(@RequestBody Compte c) throws JsonProcessingException{
		Reponse<Compte> reponse;
		try {
			Compte cpte = icm.creer(c);			
			List<String> message = new  ArrayList<>();
			message.add(String.format("%s a été creé avec succès.", cpte.getId()));
			reponse = new Reponse<Compte>(0,message,cpte);
			
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Compte>(1, GestionDesExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	
	@GetMapping("/compte")
	public String lister() throws JsonProcessingException{
		Reponse<List<Compte>> reponse;
		try {
			List<Compte> listCptes = icm.lister();
			if(listCptes!=null && !listCptes.isEmpty()) {
				reponse = new Reponse<List<Compte>>(0,null,listCptes);
			}else {
				List<String> message = new  ArrayList<>();
				message.add("Pas de compte enregistré!");
				reponse = new Reponse<List<Compte>>(1,message,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<List<Compte>>(1, GestionDesExceptions.getErrorForException(e),null);
		}
		
		return mapper.writeValueAsString(reponse);
	}
}
