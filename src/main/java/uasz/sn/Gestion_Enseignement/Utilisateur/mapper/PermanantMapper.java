package uasz.sn.Gestion_Enseignement.Utilisateur.mapper;

import org.mapstruct.Mapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.PermanantDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.model.Permanant;

import java.util.List;

@Mapper
public interface PermanantMapper {

    PermanantDTO permanantToDTO(Permanant permanent);

    Permanant dtoToPermanant(PermanantDTO permanantDTO);

    List<PermanantDTO> permanantToDTOs (List<Permanant> permanents);
}
