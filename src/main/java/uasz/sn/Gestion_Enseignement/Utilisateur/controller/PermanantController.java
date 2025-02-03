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
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EtudiantService;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanantService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PermanantController {

    @Autowired
    private PermanantService permanantService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EnseignantService enseignantService;


    @RequestMapping(value="/Permanent/Accueil", method = RequestMethod.GET)
    public String accueil_Permanant(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_Permanent";
    }

    @RequestMapping(value="/ChefDepartement/Accueil", method = RequestMethod.GET)
    public String accueil_ChefDepartement(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_ChefDepartement";
    }

    @RequestMapping(value="/ChefDepartement/ajouterPermanent", method = RequestMethod.POST)
    public String ajouter_permanant(Permanant permanant){
        String password = passwordEncoder.encode("Passer123");
        permanant.setPassword(password);
        permanant.setActive(true);
        permanant.setDateCreation(new Date());
        Role role = utilisateurService.ajouter_Role(new Role("Permanant"));
        enseignantService.ajouter(permanant);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        permanant.setRoles(roles);
        enseignantService.ajouter(permanant);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/modifierPermanent", method = RequestMethod.POST)
    public String modifier_permanant(Permanant permanant){
        Permanant per_modif = permanantService.rechercher(permanant.getId());
        per_modif.setMatricule(permanant.getMatricule());
        per_modif.setNom(permanant.getNom());
        per_modif.setPrenom(permanant.getPrenom());
        per_modif.setSpecialite(permanant.getSpecialite());
        per_modif.setGrade(permanant.getGrade());
        enseignantService.modifier(per_modif);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/activerPermanent", method = RequestMethod.POST)
    public String activer_permanant(Long id){
        enseignantService.activer(id);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value="/ChefDepartement/archiverPermanent", method = RequestMethod.POST)
    public String archiver_permanant(Long id){
        enseignantService.archiver(id);
        return "redirect:/ChefDepartement/Enseignant";
    }
}
