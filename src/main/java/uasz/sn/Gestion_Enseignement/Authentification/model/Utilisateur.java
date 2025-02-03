package uasz.sn.Gestion_Enseignement.Authentification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="utilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    @NotNull
    private String password;
    private String nom;
    private String prenom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
