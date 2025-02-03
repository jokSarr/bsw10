package uasz.sn.Gestion_Enseignement.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;

@Entity
@Table(name="administratif")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administratif extends Utilisateur {
    private String matricule;
}
