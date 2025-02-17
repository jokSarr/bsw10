import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Connexion from './components/Connexion';
import Inscription from './components/Inscription';

function App() {
    return (
        <Router>
           

            <Routes>
                <Route path="/inscrire" element={<Inscription />} />
                <Route path="/connecter" element={<Connexion />} />
            </Routes>
        </Router>
    );
}

export default App;
