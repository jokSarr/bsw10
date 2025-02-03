    package uasz.sn.Gestion_Enseignement.Maquettes.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import uasz.sn.Gestion_Enseignement.Maquettes.model.Maquette;
    import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;
    import uasz.sn.Gestion_Enseignement.Maquettes.repository.MaquetteRepository;
    import uasz.sn.Gestion_Enseignement.Maquettes.repository.UERepository;

    import java.util.List;

    @Service
    @Transactional
    public class UEService {

        @Autowired
        private UERepository ueRepository;


        public UE ajouterUE(UE ue) {
            return ueRepository.save(ue);
        }

        public List<UE> listerUE() {
            return ueRepository.findAll();
        }


        public UE rechercherUE(Long id){
            return ueRepository.findById(id).get();
        }

        public UE modifierUE(UE ue) {
            UE uen = ueRepository.findById(ue.getId()).get();
            uen.setLibelle(ue.getLibelle());
            uen.setCode(ue.getCode());
            uen.setDescription(ue.getDescription());
            uen.setCredits(ue.getCredits());
            uen.setCoefficient(ue.getCoefficient());
            ueRepository.save(uen);
            return uen;
        }






        public List<UE> listerUEParSemestre(int semestre) {
            return ueRepository.findBySemestre(semestre);
        }

        public void supprimerUE(Long id) {
            ueRepository.deleteById(id);
        }

    }
