import React, { useState } from 'react';
import '../styles/Inscription.css';
import { Link, useNavigate } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';

function ButtonAppBar() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
        <Button color="inherit" component={Link} to="/connexion">
            Déjà un compte ? Connectez-vous
        </Button>

        </Toolbar>
      </AppBar>
    </Box>
  );
}
const Inscription = () => {
    const navigate = useNavigate();

    const [utilisateur, setUtilisateur] = useState({
        username: '',
        prenom: '',
        nom: '',
        email: '',
        password: '',
        role: '', 
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUtilisateur({ ...utilisateur, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8081/auth/inscrire', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(utilisateur)
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP : ${response.status}`);
            }

            alert('Inscription réussie !');
            navigate('/'); 
        } catch (error) {
            alert('Erreur lors de l\'inscription');
            console.error('Erreur Fetch :', error);
        }
    };

    return (
        <div>
        <ButtonAppBar/>
    
        <div className="inscription-container">
            
            <h2>Inscription</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Nom d'utilisateur</label>
                    <input type="text" name="username" value={utilisateur.username} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Prénom</label>
                    <input type="text" name="prenom" value={utilisateur.prenom} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Nom</label>
                    <input type="text" name="nom" value={utilisateur.nom} onChange={handleChange} required />
                </div>
        
                <div className="form-group">
                    <label>Rôle</label>
                    <select name="role" value={utilisateur.role} onChange={handleChange} required>
                        <option value="permanent">Permanent</option>
                        <option value="vacataire">Vacataire</option>
                        <option value="etudiant">Étudiant</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Mot de passe</label>
                    <input type="password" name="password" value={utilisateur.password} onChange={handleChange} required />
                </div>
                <button type="submit">S'inscrire</button>
            </form>
            
        </div>
        </div>
    );
};

export default Inscription;
