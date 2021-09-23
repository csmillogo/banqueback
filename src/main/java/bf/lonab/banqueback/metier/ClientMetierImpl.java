package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.ClientRepository;
import bf.lonab.banqueback.entites.Client;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientMetierImpl implements IClientMetier{
	
	private ClientRepository cltr;
	
	@Override
	public Client creer(Client entite) {
		// TODO Auto-generated method stub
		return cltr.save(entite);
	}

	@Override
	public Client modifier(Client entite) {
		// TODO Auto-generated method stub
		return cltr.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		cltr.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Client> lister() {
		// TODO Auto-generated method stub
		return cltr.findAll();
	}

	@Override
	public Client trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return cltr.findById(identifiant).get();
	}

}

