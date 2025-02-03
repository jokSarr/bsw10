package uasz.sn.Gestion_Enseignement.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;

import java.util.Date;

@Entity
@Table(name="etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends Utilisateur {
    private String matricule;
}
