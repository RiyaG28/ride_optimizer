import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './components/Registration';
import CreateRide from './components/CreateRide';
import { AppBar, Toolbar, Typography } from '@mui/material';
import ButtonAppBar from './components/ButtonAppbar';
import PathOptimiser from './components/PathOptimiser';

const App = () => {
  return (
    <Router>
      <div>
        <ButtonAppBar position="static" />
        <Routes>
          <Route path="/" element={<PathOptimiser />} />
          <Route path="/register" element={<Register />} />
          <Route path="/create-ride" element={<CreateRide />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
