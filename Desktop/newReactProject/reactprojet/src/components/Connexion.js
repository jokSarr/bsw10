import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Connexion.css';
import { Link } from 'react-router-dom';



const Connexion = () => {
    const [login, setLogin] = useState({
        username: '',
        password: '',
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLogin({ ...login, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8081/auth/connecter', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(login)
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP : ${response.status}`);
            }

            const data = await response.json();
            localStorage.setItem('token', data.token);
            alert('Connexion réussie !');
            navigate('/');
        } catch (error) {
            alert('Erreur de connexion');
            console.error('Erreur Fetch :', error);
        }
    };

    return (
        <div className="connexion-container">
            <h2>Connexion</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Nom d'utilisateur</label>
                    <input
                        type="text"
                        name="username"
                        value={login.username}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Mot de passe</label>
                    <input
                        type="password"
                        name="password"
                        value={login.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">Se connecter</button>
            </form>
        </div>
    );
};

export default Connexion;
