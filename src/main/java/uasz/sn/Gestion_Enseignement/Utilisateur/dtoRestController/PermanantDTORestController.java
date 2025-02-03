package uasz.sn.Gestion_Enseignement.Utilisateur.dtoRestController;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.PermanantDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.mapper.PermanantMapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanantService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apiDTO")
public class PermanantDTORestController {

    @Autowired
    private PermanantService permanantService;

    private PermanantMapper permanantMapper = Mappers.getMapper(PermanantMapper.class);

    @PostMapping(path = "/permanent")
    public ResponseEntity<PermanantDTO> ajouter(@RequestBody PermanantDTO permanantDTO){
        Permanant permanant = permanantMapper.dtoToPermanant(permanantDTO);
        permanantService.ajouter(permanant);
    return ResponseEntity.status(HttpStatus.CREATED).body(permanantDTO);
    }

    @GetMapping(path = "/permanant/{id}")
    public ResponseEntity<PermanantDTO> rechercher(@PathVariable Long id){
        Permanant permanant = permanantService.rechercher(id);
        PermanantDTO permanantDTO = permanantMapper.permanantToDTO(permanant);
        return ResponseEntity.ok(permanantDTO);
    }

    @PutMapping (path = "/permanent")
    public ResponseEntity<PermanantDTO> modifier (@RequestBody PermanantDTO permanantDTO){
        Permanant permanant = permanantService.rechercher(permanantDTO.getId());
        permanant.setMatricule(permanant.getMatricule());
        permanant.setNom(permanantDTO.getNom());
        permanant.setPrenom(permanantDTO.getPrenom());
        permanant.setGrade(permanantDTO.getGrade());
        permanantService.modifier(permanant);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(permanantDTO);
    }

    @DeleteMapping(path = "/permanent/{id}")
    public ResponseEntity<?> supprimer (@PathVariable Long id){
        permanantService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping (path = "/permanents")
    public ResponseEntity<List<PermanantDTO>> lister(){
        List<PermanantDTO> productList = permanantMapper.permanantToDTOs(permanantService.lister());
        return ResponseEntity.ok(productList);
    }
}
