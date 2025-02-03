package uasz.sn.Gestion_Enseignement.Maquettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Maquettes.model.Maquette;
import uasz.sn.Gestion_Enseignement.Maquettes.repository.MaquetteRepository;


import java.util.List;

@Service
@Transactional
public class MaquetteService {

    @Autowired
    private MaquetteRepository maquetteRepository;


    public Maquette ajoutMaquette(Maquette maquette) {
        return maquetteRepository.save(maquette);
    }

    public Maquette rechercherMaquette(Long id) {
        return maquetteRepository.findById(id).get();
    }

    public Maquette modifierMaquette(Maquette maquette) {
        Maquette mqt = maquetteRepository.findById(maquette.getId()).get();
        mqt.setId(maquette.getId());
        mqt.setUes(maquette.getUes());
        return maquetteRepository.save(mqt);
    }

    public List<Maquette> listMaquette() {
        return maquetteRepository.findAll();
    }



    public void supprimerMaquette(Long id) {
        maquetteRepository.deleteById(id);
    }


    public Maquette trouverParId(Long id) {
        return maquetteRepository.findById(id).get();
    }


}
