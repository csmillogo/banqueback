package bf.lonab.banqueback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByNom(String nom);
	List<Client> findByPrenom(String prenom);
}
