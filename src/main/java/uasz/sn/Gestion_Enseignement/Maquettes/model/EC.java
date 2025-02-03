package uasz.sn.Gestion_Enseignement.Maquettes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="ec")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private String description;
    private int coefficient;
    private int cm;
    private int td;
    private int tp;
    
    @ManyToOne
    @JoinColumn(name = "ue_id")
    private UE ue;

}
