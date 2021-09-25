package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Versement;
import bf.lonab.banqueback.metier.IVersementMetier;
import bf.lonab.banqueback.utilitaire.GestionDesExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
public class VersementController {
	
	@Autowired
	private IVersementMetier iVstM;
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/versement")
	public String creer(@RequestBody Versement v) throws JsonProcessingException{
		Reponse<Versement> reponse;
		try {
			Versement vst = iVstM.creer(v);			
			List<String> message = new  ArrayList<>();
			message.add(String.format("%s a été creé avec succès.", vst.getId()));
			reponse = new Reponse<Versement>(0,message,vst);
			
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Versement>(3, GestionDesExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
}
