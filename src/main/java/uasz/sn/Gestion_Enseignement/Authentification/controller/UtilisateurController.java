package uasz.sn.Gestion_Enseignement.Authentification.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.repository.UtilisateurRepository;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;

import java.security.Principal;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @RequestMapping(value="/login")
    public String index(){
        return "login";
    }

    @RequestMapping(value="/")
    public String login(Principal principal){
       String url = "login";
       Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
       if(!utilisateur.isActive()){
           url = "redirect:/login";
       }
       else if(utilisateur.getRoles().get(0).getRole().equals("Permanant")){
           url = "redirect:/Permanent/Accueil";
       }
       else if(utilisateur.getRoles().get(0).getRole().equals("Vacataire")){
           url = "redirect:/Vacataire/Accueil";
       }
       else if(utilisateur.getRoles().get(0).getRole().equals("ChefDepartement")){
           url = "redirect:/ChefDepartement/Accueil";
       }
       else if(utilisateur.getRoles().get(0).getRole().equals("Etudiant")){
           url = "redirect:/Etudiant/Accueil";
       }
       else if(utilisateur.getRoles().get(0).getRole().equals("Responsableclasse")){
           url = "redirect:/Responsableclasse/Accueil";
       }
       return url;
    }

    @RequestMapping(value="/logout")
    public String logOutAndRedirectToLoginPage(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        if (authentication != null){
            new SecurityContextLogoutHandler().logout( request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }

    @RequestMapping(value="/profil", method = RequestMethod.GET)
    public String profil_Etudiant(Model model, Principal principal){
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "profil";
    }

    @RequestMapping(value="/modifierUser", method = RequestMethod.POST)
    public String modifierUtilisateur(Utilisateur utilisateur){
        utilisateurService.modifier_Utilisateur(utilisateur);
        return "redirect:/profil";
    }
}
