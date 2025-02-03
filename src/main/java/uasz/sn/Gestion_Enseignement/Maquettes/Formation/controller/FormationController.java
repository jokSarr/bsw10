package uasz.sn.Gestion_Enseignement.Maquettes.Formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Formation;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.service.FormationService;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
import uasz.sn.Gestion_Enseignement.Maquettes.service.UEService;


import java.util.List;

@Controller
public class FormationController {

    @Autowired
    private FormationService formationService;

    @Autowired
    private UEService ueService;

    @RequestMapping(value = "/ChefDepartement/ajouterFormation", method = RequestMethod.POST)
    public String ajouter_Formation(Formation formation){
        formationService.ajouterFormation(formation);
        return "redirect:/ChefDepartement/Formations";
    }

    @RequestMapping(value = "/ChefDepartement/modifierFormation", method = RequestMethod.POST)
    public String modifier_Formation(Formation formation){
        formationService.modifierFormation(formation);
        return "redirect:/ChefDepartement/Formations";
    }

    @RequestMapping(value = "/ChefDepartement/Formations", method = RequestMethod.GET)
    public String liste_Formation(Model model){
        List<Formation> formations = formationService.listerFormation();
        List<UE> listUe = ueService.listerUE();
        model.addAttribute("formations", formations);
        model.addAttribute("listUe", listUe);
        return "formation";
    }

    @RequestMapping(value = "/ChefDepartement/supprimer_Formation", method = RequestMethod.POST)
    public String supprimer_Formation(Long id){
        formationService.supprimerFormation(id);
        return "redirect:/ChefDepartement/Formations";
    }

    @RequestMapping(value="/ChefDepartement/archiverFormation", method = RequestMethod.POST)
    public String archiver_formation(Long id){
        formationService.archiver(id);
        return "redirect:/ChefDepartement/Formations";
    }

}
