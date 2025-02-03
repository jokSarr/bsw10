package uasz.sn.Gestion_Enseignement.Maquettes.Formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Classe;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Formation;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.repository.ClasseRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.model.EC;
import uasz.sn.Gestion_Enseignement.Maquettes.repository.ECRepository;

import java.util.List;

@Service
@Transactional
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    public Classe ajouterClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    public List<Classe> lister(){
        return classeRepository.findAll();
    }

    public List<Classe> listerClassedeFormation(Long id){
        return classeRepository.findClasseById(id);
    }

    public Classe rechercher(Long id){
        return classeRepository.findById(id).get();
    }

    public void archiver(Long id) {
        Classe classe = classeRepository.findById(id).get();
        if (classe.isArchive() == true){
            classe.setArchive(false);
        }
        else {
            classe.setArchive(true);
        }
        classeRepository.save(classe);
    }


    public Classe modifier(Classe classe){
        Classe cn = classeRepository.findById(classe.getId()).get();
        cn.setNom(classe.getNom());
        cn.setNiveau(classe.getNiveau());
        classeRepository.save(cn);
        return cn;
    }

    public void supprimer(Long id){
        classeRepository.deleteById(id);
    }

}
