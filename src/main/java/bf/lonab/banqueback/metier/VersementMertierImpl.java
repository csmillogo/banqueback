package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.VersementRepository;
import bf.lonab.banqueback.entites.Versement;
import bf.lonab.banqueback.exception.InvalideBanqueBackException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VersementMertierImpl implements  IVersementMetier{
	
	private VersementRepository vst;
	
	@Override
	public Versement creer(Versement entite) throws InvalideBanqueBackException {
		// TODO Auto-generated method stub
		return vst.save(entite);
	}

	@Override
	public Versement modifier(Versement entite) {
		// TODO Auto-generated method stub
		return vst.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		vst.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Versement> lister() {
		// TODO Auto-generated method stub
		return vst.findAll();
	}

	@Override
	public Versement trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return vst.findById(identifiant).get();
	}

}
