package uasz.sn.Gestion_Enseignement.Authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Authentification.model.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.repository.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        String[] roles = utilisateur.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails =
                User.builder()
                    .username(utilisateur.getUsername())
                    .password(utilisateur.getPassword())
                    .roles(roles)
                    .build();
        return userDetails;

    }
}
