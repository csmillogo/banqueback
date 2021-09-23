package bf.lonab.banqueback.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double decouvert;
}
