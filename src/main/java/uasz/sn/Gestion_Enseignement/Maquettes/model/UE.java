package uasz.sn.Gestion_Enseignement.Maquettes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="ue")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private String description;
    private int credits;
    private int coefficient;
    private int semestre;


    @OneToMany(mappedBy = "ue")
    private Collection<EC> ecs;

    @ManyToMany(mappedBy = "ues")
    private List<Maquette> maquette = new ArrayList<>();


}
