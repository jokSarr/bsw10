package uasz.sn.Gestion_Enseignement.Maquettes.Formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.Maquettes.Formation.model.Classe;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    List<Classe> findClasseById(Long id);
}
