package uasz.sn.Gestion_Enseignement.Maquettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.model.UE;

import java.util.List;

public interface UERepository extends JpaRepository<UE, Long> {
     List<UE> findBySemestre(int semestre);
     List<UE> findByMaquetteId(Long idMaquette);

}
