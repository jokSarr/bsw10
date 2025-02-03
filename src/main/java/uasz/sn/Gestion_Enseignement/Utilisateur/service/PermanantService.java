package uasz.sn.Gestion_Enseignement.Utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.PermanantRepository;
import uasz.sn.Gestion_Enseignement.exception.ResourceAlreadyExistException;
import uasz.sn.Gestion_Enseignement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermanantService {

    @Autowired
    private PermanantRepository permanantRepository;

    public Permanant ajouter(Permanant permanant) {
        Optional<Permanant> optionalEtudiant = permanantRepository.findByMatricule(permanant.getMatricule());
        if (optionalEtudiant.isPresent()) {
            throw new ResourceAlreadyExistException("Le matricule " + permanant.getMatricule() + " existe déja");
        }
         try {
            return permanantRepository.save(permanant);
        }catch (Exception exception) {
            throw new ResourceNotFoundException("Erreur lors de l'ajout");
            }
        }


    public List<Permanant> lister() {
        return permanantRepository.findAll();
    }

    public Permanant rechercher(Long id) {
        return permanantRepository.findById(id).get();
    }

    public Permanant modifier(Permanant permanant) {
        return permanantRepository.save(permanant);
    }

    public void supprimer(Long id) {
        permanantRepository.deleteById(id);
    }
}
