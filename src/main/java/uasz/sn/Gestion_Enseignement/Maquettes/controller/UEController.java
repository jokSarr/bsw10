package uasz.sn.Gestion_Enseignement.Maquettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Maquettes.model.Maquette;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
import uasz.sn.Gestion_Enseignement.Maquettes.service.MaquetteService;
import uasz.sn.Gestion_Enseignement.Maquettes.service.UEService;



import java.util.List;

@Controller
public class UEController {


    @Autowired
    private UEService ueService;

    @Autowired
    private MaquetteService maquetteService;

    @RequestMapping(value = "/ChefDepartement/ajouterUe", method = RequestMethod.POST)
    public String ajouter_UE(UE ue){
        ueService.ajouterUE(ue);
        return "redirect:/ChefDepartement/Maquettes";
    }

    @RequestMapping(value = "/ChefDepartement/modifierUE", method = RequestMethod.POST)
    public String modifier_UE(UE ue){
        ueService.modifierUE(ue);
        return "redirect:/ChefDepartement/Maquettes";
    }

    @RequestMapping(value = "/ChefDepartement/Maquettes", method = RequestMethod.GET)
    public String liste_UE(Model model){
        List<UE> ues = ueService.listerUE();
        model.addAttribute("ues", ues);
        return "ue";
    }

    @RequestMapping(value = "/ChefDepartement/Maquette", method = RequestMethod.GET)
    public String liste(Model model) {
        List<UE> listUe = ueService.listerUE();
        List<Maquette> maquette = maquetteService.listMaquette();
        List<UE> uesSemestre1 = ueService.listerUEParSemestre(1);
        List<UE> uesSemestre2 = ueService.listerUEParSemestre(2);
        model.addAttribute("uesSemestre1", uesSemestre1);
        model.addAttribute("uesSemestre2", uesSemestre2);
        model.addAttribute("listUe", listUe);
        model.addAttribute("maquette", maquette);
        return "maquette";
    }


    @RequestMapping(value = "/ChefDepartement/supprimer_UE", method = RequestMethod.POST)
    public String supprimer_UE(Long id){
        ueService.supprimerUE(id);
        return "redirect:/ChefDepartement/Maquettes";
    }
}
