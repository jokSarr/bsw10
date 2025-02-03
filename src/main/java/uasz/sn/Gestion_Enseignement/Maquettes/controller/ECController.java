package uasz.sn.Gestion_Enseignement.Maquettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Maquettes.model.EC;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
import uasz.sn.Gestion_Enseignement.Maquettes.service.ECService;
import uasz.sn.Gestion_Enseignement.Maquettes.service.UEService;

import java.util.List;

@Controller
public class ECController {


    @Autowired
    private ECService ecService;
    @Autowired
    private UEService ueService;

    @RequestMapping(value = "/ChefDepartement/ajouterEC", method = RequestMethod.POST)
    public String ajouter_EC(EC ec , @RequestParam("idue") Long idue){
        UE ue = ueService.rechercherUE(idue);
        ec.setUe(ue);
        ecService.ajouterEC(ec);
        return "redirect:/ChefDepartement/listeEC?id=" + ec.getUe().getId();
    }

    @RequestMapping(value = "/ChefDepartement/modifierEC", method = RequestMethod.POST)
    public String modifier_EC(EC ec, @RequestParam("idue") Long idue ){
        UE ue = ueService.rechercherUE(idue);
        ec.setUe(ue);
        EC ec_modif = ecService.rechercher(ec.getId());
        ec_modif.setCode(ec.getCode());
        ec_modif.setDescription(ec.getDescription());
        ec_modif.setLibelle(ec.getLibelle());
        ec_modif.setCode(ec.getCode());
        ec_modif.setCoefficient(ec.getCoefficient());
        ec_modif.setCm(ec.getCm());
        ec_modif.setTd(ec.getTd());
        ec_modif.setTp(ec.getTp());
        ecService.modifier(ec_modif);
        return "redirect:/ChefDepartement/listeEC?id=" + ec_modif.getUe().getId();
    }

    @RequestMapping(value = "/ChefDepartement/listeEC", method = RequestMethod.GET)
    public String liste_EC(Model model, @RequestParam("id") Long id ){
        UE ue = ueService.rechercherUE(id);
        List<EC> ecs = ecService.listerECdeUE(ue.getId());
        List<UE> listUe= ueService.listerUE();
        model.addAttribute("ue", ue);
        model.addAttribute("ecs", ecs);
        model.addAttribute("listUe", listUe);
        return "ue_details";
    }

    @RequestMapping(value = "/ChefDepartement/supprimerEC", method = RequestMethod.POST)
    public String supprimer_EC(Long id){
        ecService.supprimer(id);
        return "redirect:/ChefDepartement/Maquettes";
    }
}
