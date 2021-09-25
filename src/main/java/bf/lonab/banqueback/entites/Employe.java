package bf.lonab.banqueback.entites;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employe", 
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {
						"email"
				})
		})
@Data @NoArgsConstructor @AllArgsConstructor
public class Employe extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Size(max=20)
	private String nom;
	private String prenom;
	private String nomComplet;
	@Email
	private String email;
	private String pwd;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (name = "Roles_Employe",
		joinColumns = @JoinColumn(name = "employeId"),
		inverseJoinColumns = @JoinColumn(name = "RoleId"))
	private Set<Role> roles = new HashSet<>();
	
	@PrePersist
	public void setNomComplet() {
		this.nomComplet = nom + " " +prenom;
	}
}
