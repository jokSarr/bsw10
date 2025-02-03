package uasz.sn.Gestion_Enseignement.Maquettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.model.Maquette;

import java.util.Optional;

public interface MaquetteRepository extends JpaRepository<Maquette, Long> {
    Optional<Maquette> findById(Long id);
}
