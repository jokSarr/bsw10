package uasz.sn.Gestion_Enseignement.Utilisateur.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;

@Entity
@Table(name="enseignant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Enseignant extends Utilisateur {

    private String specialite;
    private boolean archive;
}
