package uasz.sn.Gestion_Enseignement.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;

import java.util.Optional;

public interface PermanantRepository extends JpaRepository<Permanant, Long> {
    @Query("SELECT p from Permanant p where p.matricule= ?1")
    Optional<Permanant> findByMatricule(String matricule);
}
