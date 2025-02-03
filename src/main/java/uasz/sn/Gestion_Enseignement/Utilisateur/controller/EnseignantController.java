package uasz.sn.Gestion_Enseignement.Utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Vacataire;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanantService;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.VacataireService;
import java.security.Principal;
import java.util.List;

@Controller
public class EnseignantController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PermanantService permanantService;

    @Autowired
    private VacataireService vacataireService;

    @RequestMapping(value="/ChefDepartement/Enseignant", method = RequestMethod.GET)
    public String ChefDepartement_Enseignant(Model model, Principal principal) {
        List<Permanant> permanents = permanantService.lister();
        model.addAttribute("permanents", permanents);
        List<Vacataire> vacataires = vacataireService.lister();
        model.addAttribute("vacataires", vacataires);
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom" ,utilisateur.getNom());
        model.addAttribute("prenom" ,utilisateur.getPrenom().charAt(0));
        return "chefDepartement_Enseignant";
    }
}
