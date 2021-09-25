package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.EmployeRepository;
import bf.lonab.banqueback.entites.Employe;
import bf.lonab.banqueback.exception.InvalideBanqueBackException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeMetierImpl implements IEmployeMetier {
	private EmployeRepository empr;
	private PasswordEncoder pwssEncoder;

	@Override
	public Employe creer(Employe entite) throws InvalideBanqueBackException {
		// TODO Auto-generated method stub
		if(entite.getNom().equals(null) || entite.getNom() == "") {
			throw new InvalideBanqueBackException("le nom ne peut etre null ou vide");
		}
		entite.setPwd(pwssEncoder.encode(entite.getPwd()));
		return empr.save(entite);
	}

	@Override
	public Employe modifier(Employe entite) {
		// TODO Auto-generated method stub
		return empr.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		//empr.save(entite);
		empr.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Employe> lister() {
		// TODO Auto-generated method stub
		return empr.findAll();
	}

	@Override
	public Employe trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return empr.findById(identifiant).get();
	}

	
}
