// src/components/Register.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Container, Typography, MenuItem, Select } from '@mui/material';
import GoogleMapsComponent from './GoogleMapsComponent';


const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('');
    const [latitude, setLatitude] = useState(null);
    const [longitude, setLongitude] = useState(null);
    const [locationName, setLocationName] = useState('');

    const handleRegister = async () => {
        try {
            const user = { username, password, role, latitude, longitude, available: true };
            console.log(user);
            const response = await axios.post('http://localhost:8080/api/users/register', user);
            console.log(response);
            alert('User registered successfully');
        } catch (error) {
            alert('Registration failed');
        }
    };

    const handleLocationSelect = (location) => {
        setLatitude(location.lat);
        setLongitude(location.lng);
        setLocationName(location.locationName);
    };

    return (
        <Container>
            <Typography variant="h4" component="h1" gutterBottom>
                Register
            </Typography>
            <TextField
                label="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Select
                labelId="role-label"
                id="role-select"
                value={role}
                onChange={(e) => setRole(e.target.value)}
                label="Role"
            >
                <MenuItem value="RIDER">Rider</MenuItem>
                <MenuItem value="DRIVER">Driver</MenuItem>
            </Select>
            <Typography variant="h6" component="h2" gutterBottom>
                Select Pickup Location
            </Typography>
            <GoogleMapsComponent onLocationSelect={handleLocationSelect} />
            {locationName && (
                <div>
                    <Typography variant="body1">
                        Selected Location: {locationName}
                    </Typography>
                </div>
            )}
            <Button variant="contained" color="primary" onClick={handleRegister} style={{ marginTop: '20px' }}>
                Register
            </Button>
        </Container>
    );
};

export default Register;
