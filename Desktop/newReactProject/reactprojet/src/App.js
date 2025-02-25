import React from 'react';
import { BrowserRouter as Router, Routes, Route  } from 'react-router-dom';
import Connexion from './components/Connexion';
import Inscription from './components/Inscription';
import ChefDepartement from './components/ChefDepartement';
import Vacataire from './components/Vacataire';
import Etudiant from './components/Etudiant';
import Permanent from './components/Permanent'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/inscription" element={<Inscription />} />
                <Route path="/connexion" element={<Connexion />} />
                <Route path="/chefDepartement" element={<ChefDepartement />} />
                <Route path="/vacataire" element={<Vacataire />} />
                <Route path="/etudiant" element={<Etudiant />} />
                <Route path="/permanent" element={<Permanent/>}/>
            </Routes>
        </Router>
    );
}

export default App;
