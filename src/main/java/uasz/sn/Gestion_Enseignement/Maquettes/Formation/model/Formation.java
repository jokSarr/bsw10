package uasz.sn.Gestion_Enseignement.Maquettes.Formation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;

@Entity
@Table(name="formation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private boolean archive;
    private int semestre;
    private String niveau;

    @OneToMany(mappedBy = "formation")
    private Collection<Classe> classes;
}

