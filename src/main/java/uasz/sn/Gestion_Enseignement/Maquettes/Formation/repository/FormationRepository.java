package uasz.sn.Gestion_Enseignement.Maquettes.Formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Formation;


public interface FormationRepository extends JpaRepository<Formation, Long> {

}
