package uasz.sn.Gestion_Enseignement.Utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.model.Role;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Vacataire;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.VacataireRepository;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.VacataireService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VacataireController {
    @Autowired
    private VacataireService vacataireService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EnseignantService enseignantService;


    @RequestMapping(value="/Vacataire/Accueil", method = RequestMethod.GET)
    public String accueil_Vacataire(Model model, Principal principal){
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_Vacataire";
    }

    @RequestMapping(value="/ChefDepartement/ajouterVacataire", method = RequestMethod.POST)
    public String ajouter_Vacataire(Vacataire vacataire){
        String password = passwordEncoder.encode("Passer123");
        vacataire.setPassword(password);
        vacataire.setDateCreation(new Date());
        vacataire.setActive(true);
        Role role = utilisateurService.ajouter_Role(new Role("Vacataire"));
        enseignantService.ajouter(vacataire);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        vacataire.setRoles(roles);
        enseignantService.ajouter(vacataire);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/modifierVacataire", method = RequestMethod.POST)
    public String modifier_vacataire(Vacataire vacataire){
        Vacataire vacataire_modif = vacataireService.rechercher(vacataire.getId());
        vacataire_modif.setNom(vacataire.getNom());
        vacataire_modif.setPrenom(vacataire.getPrenom());
        vacataire_modif.setSpecialite(vacataire.getSpecialite());
        vacataire_modif.setNiveau(vacataire.getNiveau());
        vacataireService.modifier(vacataire_modif);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/activerVacataire", method = RequestMethod.POST)
    public String activer_vacataire(Long id){
        enseignantService.activer(id);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/archiverVacataire", method = RequestMethod.POST)
    public String archiver_vacataire(Long id){
        enseignantService.archiver(id);
        return "redirect:/ChefDepartement/Enseignant";
    }
}
