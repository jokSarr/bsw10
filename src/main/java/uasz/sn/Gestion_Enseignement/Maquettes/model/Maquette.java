package uasz.sn.Gestion_Enseignement.Maquettes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="maquette")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int semestre;


    @ManyToMany
    @JoinTable(name = "maquette_ue", joinColumns = @JoinColumn(name = "maquette_id"), inverseJoinColumns = @JoinColumn(name = "ue_id"))
    private List<UE> ues = new ArrayList<>();

}
