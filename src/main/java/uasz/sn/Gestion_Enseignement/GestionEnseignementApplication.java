package uasz.sn.Gestion_Enseignement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.Gestion_Enseignement.Authentification.model.Role;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Vacataire;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;

import java.util.Date;

@SpringBootApplication
public class GestionEnseignementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionEnseignementApplication.class, args);
	}

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;
	private PasswordEncoder passwordEncoder;

	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String...args) throws Exception{
		Role permanant = utilisateurService.ajouter_Role(new Role("Permanant"));
		Role vacataire = utilisateurService.ajouter_Role(new Role("Vacataire"));
		Role chefDepartement = utilisateurService.ajouter_Role(new Role("ChefDepartement"));
		String password = passwordEncoder().encode("Passer123");


		Permanant user = new Permanant();
		user.setNom("Diagne");
		user.setPrenom("Serigne");
		user.setUsername("sdiagne@uasz.sn");
		user.setPassword(password);
		user.setDateCreation(new Date());
		user.setActive(true);
		user.setSpecialite("Base de données");
		user.setMatricule("SD2304");
		user.setGrade("Professeur");
		enseignantService.ajouter(user);
		utilisateurService.ajouter_UtilisateurRoles(user, chefDepartement);

		Permanant user_1 = new Permanant();
		user_1.setUsername("idiop@uasz.sn");
		user_1.setNom("Diop");
		user_1.setPrenom("Ibrahima");
		user_1.setDateCreation(new Date());
		user_1.setActive(true);
		user_1.setSpecialite("Web Semantique");
		user_1.setPassword(password);
		user_1.setMatricule("2667");
		user_1.setGrade("Professeur");
		enseignantService.ajouter(user_1);
		utilisateurService.ajouter_UtilisateurRoles(user_1, permanant);

		Vacataire user_2 = new Vacataire();
		user_2.setUsername("cmalak@uasz.sn");
		user_2.setNom("Malack");
		user_2.setPrenom("Camir");
		user_2.setDateCreation(new Date());
		user_2.setActive(true);
		user_2.setSpecialite("Ingenierie de connaissance");
		user_2.setPassword(password);
		user_2.setNiveau("Doctorant");
		enseignantService.ajouter(user_2);
		utilisateurService.ajouter_UtilisateurRoles(user_2, vacataire);

	}

}
