package uasz.sn.Gestion_Enseignement.Utilisateur.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanantService;

import java.util.List;

@CrossOrigin(origins= "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PermanantRestController {

    @Autowired
    private PermanantService permanantService;

    @GetMapping(path = "/permanents")
    public List lister_Permanent(){
        return permanantService.lister();
    }

    @PostMapping(path="/permanent")
    public Permanant ajouter_Permanent(@RequestBody Permanant permanant){
        return permanantService.ajouter(permanant);
    }

    @GetMapping(path="/permanent/{id}")
    public Permanant rechercher_Permanent(@PathVariable Long id){
        return permanantService.rechercher(id);
    }

    @PutMapping(path="/permanent")
    public Permanant modifier_Permanent(@RequestBody Permanant permanant){
        return permanantService.modifier(permanant);
    }

    @DeleteMapping(path="/permanent/{id}")
    public String supprimer_Permanent(@PathVariable Long id){
        permanantService.supprimer(id);
        return "Enseignant supprime avec succes";
    }
}
