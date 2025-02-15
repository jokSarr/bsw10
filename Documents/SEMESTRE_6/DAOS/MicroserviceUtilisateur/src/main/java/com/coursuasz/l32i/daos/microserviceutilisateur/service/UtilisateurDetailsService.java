package com.coursuasz.l32i.daos.microserviceutilisateur.service;

import com.coursuasz.l32i.daos.microserviceutilisateur.modele.Utilisateur;
import com.coursuasz.l32i.daos.microserviceutilisateur.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UtilisateurDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if(utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur inexistant" + username);
        }
        return new org.springframework.security.core.userdetails.User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(utilisateur.getRole()))
        );
    }
}