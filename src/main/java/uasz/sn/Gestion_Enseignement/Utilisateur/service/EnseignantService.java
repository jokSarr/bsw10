package uasz.sn.Gestion_Enseignement.Utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Enseignant;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.EnseignantRepository;

import java.util.List;

@Service
@Transactional
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public Enseignant ajouter(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> lister(){
        return enseignantRepository.findAll();
    }

    public Enseignant rechercher(long id) {
        return enseignantRepository.findById(id).get();
    }

    public Enseignant modifier(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public void supprimer(long id) {
        enseignantRepository.deleteById(id);
    }

    public void activer(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).get();
        if (enseignant.isActive() == true){
            enseignant.setActive(false);
        }
        else {
            enseignant.setActive(true);
        }
        enseignantRepository.save(enseignant);
    }

    public void archiver(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).get();
        if (enseignant.isArchive() == true){
            enseignant.setArchive(false);
        }
        else {
            enseignant.setArchive(true);
        }
        enseignantRepository.save(enseignant);
    }
}
