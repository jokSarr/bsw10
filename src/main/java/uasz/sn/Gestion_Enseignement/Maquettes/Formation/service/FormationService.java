package uasz.sn.Gestion_Enseignement.Maquettes.Formation.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Formation;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.repository.FormationRepository;

import java.util.List;

@Service
@Transactional
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Formation ajouterFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public List<Formation> listerFormation() {
        return formationRepository.findAll();
    }

    public Formation rechercherFormation(Long id){
        return formationRepository.findById(id).get();
    }

    public Formation modifierFormation(Formation formation) {
        Formation formation1 = formationRepository.findById(formation.getId()).get();
        formation1.setNom(formation.getNom());
        formation1.setNiveau(formation.getNiveau());
        formationRepository.save(formation1);
        return formation1;
    }


    public void archiver(Long id) {
        Formation formation = formationRepository.findById(id).get();
        if (formation.isArchive() == true){
            formation.setArchive(false);
        }
        else {
            formation.setArchive(true);
        }
        formationRepository.save(formation);
    }


    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

}

