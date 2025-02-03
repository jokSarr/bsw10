package uasz.sn.Gestion_Enseignement.Maquettes.Formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Classe;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Formation;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.service.ClasseService;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.service.FormationService;
import uasz.sn.Gestion_Enseignement.Maquettes.model.EC;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
import uasz.sn.Gestion_Enseignement.Maquettes.service.ECService;
import uasz.sn.Gestion_Enseignement.Maquettes.service.UEService;

import java.util.List;

@Controller
public class ClasseController {


    @Autowired
    private ClasseService classeService;
    @Autowired
    private FormationService formationService;

    @RequestMapping(value = "/ChefDepartement/ajouterClasse", method = RequestMethod.POST)
    public String ajouter_Classe(Classe classe , @RequestParam("idf") Long idf){
        Formation formation = formationService.rechercherFormation(idf);
        classe.setFormation(formation);
        classeService.ajouterClasse(classe);
        return "redirect:/ChefDepartement/listeClasse?id=" + classe.getFormation().getId();
    }

    @RequestMapping(value = "/ChefDepartement/modifierClasse", method = RequestMethod.POST)
    public String modifier_Classe(Classe classe, @RequestParam("idf") Long idf ){
        Formation formation = formationService.rechercherFormation(idf);
        classe.setFormation(formation);
        Classe c_modif = classeService.rechercher(classe.getId());
        c_modif.setNom(classe.getNom());
        c_modif.setNiveau(classe.getNiveau());
        classeService.modifier(c_modif);
        return "redirect:/ChefDepartement/listeClasse?id=" + c_modif.getFormation().getId();
    }

    @RequestMapping(value = "/ChefDepartement/listeClasse", method = RequestMethod.GET)
    public String liste_Classe(Model model, @RequestParam("id") Long id ){
        Formation formation = formationService.rechercherFormation(id);
        List<Classe> classes = classeService.listerClassedeFormation(formation.getId());
        List<Formation> listFor= formationService.listerFormation();
        model.addAttribute("formation", formation);
        model.addAttribute("classes", classes);
        model.addAttribute("listFor", listFor);
        return "formation_details";
    }

    @RequestMapping(value = "/ChefDepartement/supprimerClasse", method = RequestMethod.POST)
    public String supprimer_Classe(Long id){
        classeService.supprimer(id);
        return "redirect:/ChefDepartement/Formations";
    }
}
