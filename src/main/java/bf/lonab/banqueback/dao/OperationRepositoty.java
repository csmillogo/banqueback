package bf.lonab.banqueback.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Operation;

public interface OperationRepositoty extends JpaRepository<Operation, Long> {

}
