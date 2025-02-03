package uasz.sn.Gestion_Enseignement.Maquettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Maquettes.model.EC;
import uasz.sn.Gestion_Enseignement.Maquettes.repository.ECRepository;

import java.util.List;

@Service
@Transactional
public class ECService {

    @Autowired
    private ECRepository ecRepository;

    public EC ajouterEC(EC ec) {
        return ecRepository.save(ec);
    }

    public List<EC> lister(){
        return ecRepository.findAll();
    }

    public List<EC> listerECdeUE(Long id){
        return ecRepository.findUeById(id);
    }

    public EC rechercher(Long id){
        return ecRepository.findById(id).get();
    }

    public EC modifier(EC ec){
        EC ecn = ecRepository.findById(ec.getId()).get();
        ecn.setCm(ec.getCm());
        ecn.setLibelle(ecn.getLibelle());
        ecn.setCode(ec.getCode());
        ecn.setDescription(ec.getDescription());
        ecn.setTd(ec.getTd());
        ecn.setTp(ec.getTp());
        return ecRepository.save(ecn);
    }

    public void supprimer(Long id){
        ecRepository.deleteById(id);
    }

}
