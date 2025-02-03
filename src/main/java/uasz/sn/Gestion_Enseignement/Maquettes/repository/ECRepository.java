package uasz.sn.Gestion_Enseignement.Maquettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.model.EC;

import java.util.List;

public interface ECRepository extends JpaRepository<EC, Long> {

    List<EC> findUeById(Long id);
}
