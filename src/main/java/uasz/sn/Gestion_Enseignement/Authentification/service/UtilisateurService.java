package uasz.sn.Gestion_Enseignement.Authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Authentification.model.Role;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.repository.RoleRepository;
import uasz.sn.Gestion_Enseignement.Authentification.repository.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Utilisateur ajouter_Utilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    public Role ajouter_Role(Role role) {
        roleRepository.save(role);
        return role;
    }

    public void ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
        Utilisateur user = utilisateurRepository.findByUsername(utilisateur.getUsername());
        Role profil = roleRepository.findByRole(role.getRole());
        user.getRoles().add(profil);
    }

    public Utilisateur rechercher_Utilisateur(String username){
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        return utilisateur;
    }

    public Utilisateur modifier_Utilisateur(Utilisateur utilisateur){
        Utilisateur user = utilisateurRepository.findByUsername(utilisateur.getUsername());
        utilisateur.setDateCreation(user.getDateCreation());
        utilisateur.setActive(user.isActive());
        utilisateur.setRoles(user.getRoles());
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }


}
