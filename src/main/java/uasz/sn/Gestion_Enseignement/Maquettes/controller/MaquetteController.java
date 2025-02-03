package uasz.sn.Gestion_Enseignement.Maquettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Maquettes.model.Maquette;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
import uasz.sn.Gestion_Enseignement.Maquettes.service.MaquetteService;
import uasz.sn.Gestion_Enseignement.Maquettes.service.UEService;

import java.util.List;


@Controller
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;

    @Autowired
    private UEService ueService;


    @RequestMapping(value="/ChefDepartement/modifierMaquette", method = RequestMethod.POST)
    public String modifierMaquette(Maquette maquette){
         maquetteService.modifierMaquette(maquette);
         return "redirect:/ChefDepartement/Formations";
    }

    @RequestMapping(value="/ChefDepartement/AjoutUE", method = RequestMethod.POST)
    public String ajoutUEAMaquette(@RequestParam Long idMaquette, @RequestParam Long idUE) {
        Maquette maquette = maquetteService.trouverParId(idMaquette);
        UE ue = ueService.rechercherUE(idUE);

        if (maquette != null && ue != null) {
            List<Maquette> mqt = maquetteService.listMaquette();
            ue.setMaquette(mqt);
            maquette.getUes().add(ue);
            maquetteService.ajoutMaquette(maquette);
        }
        return "maquette";
    }

    @RequestMapping(value="/ChefDepartement/listerMaquette", method = RequestMethod.GET)
    public String listerMaquette(Model model, @RequestParam("id") Long id){
        List<Maquette> maquette = maquetteService.listMaquette();
        UE ue = ueService.rechercherUE(id);
        List<UE> listUe = ueService.listerUE();
        model.addAttribute("listUe", listUe);
        model.addAttribute("maquette", maquette);
        model.addAttribute("ue", ue);
        return "maquette_details";
    }



    @RequestMapping(value="/ChefDepartement/supprimerMaquette", method = RequestMethod.POST)
    public String supprimerMaquette(Maquette maquette){
        maquetteService.supprimerMaquette(maquette.getId());
        return "redirect:/ChefDepartement/Formations";
    }
}
