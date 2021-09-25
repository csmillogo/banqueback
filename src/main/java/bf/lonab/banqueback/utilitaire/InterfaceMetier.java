package bf.lonab.banqueback.utilitaire;

import java.util.List;

import bf.lonab.banqueback.exception.InvalideBanqueBackException;

/* Interface generique */
public interface InterfaceMetier<T,U> {
	public T creer(T entite) throws InvalideBanqueBackException;
	public T modifier(T entite);
	public boolean supprimer(U identifiant);
	public List<T> lister();
	public T trouver(U identifiant);
}
