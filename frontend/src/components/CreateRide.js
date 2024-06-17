// src/components/CreateRide.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Container, Typography } from '@mui/material';
import GoogleMapsComponent from './GoogleMapsComponent';

const CreateRide = () => {
    const [pickupLat, setPickupLat] = useState(null);
    const [pickupLon, setPickupLon] = useState(null);
    const [pickupLocation, setPickupLocation] = useState('');
    const [dropLat, setDropLat] = useState(null);
    const [dropLon, setDropLon] = useState(null);
    const [dropLocation, setDropLocation] = useState('');
    const [riderId, setRiderId] = useState('');

    const handlePickupLocationSelect = (location) => {
        setPickupLat(location.lat);
        setPickupLon(location.lng);
        setPickupLocation(location.locationName);
    };

    const handleDropLocationSelect = (location) => {
        setDropLat(location.lat);
        setDropLon(location.lng);
        setDropLocation(location.locationName);
    };

    const handleCreateRide = async () => {
        try {
            const ride = { pickupLat, pickupLon, dropLat, dropLon, pickupLocation, dropLocation, rider: { id: riderId }, status: 'REQUESTED' };
            const response = await axios.post('http://localhost:8080/api/rides', ride);
            console.log(response);
            alert('Ride created successfully');
        } catch (error) {
            console.error('Ride creation failed', error);
            alert('Ride creation failed');
        }
    };

    return (
        <Container>
            <Typography variant="h4" component="h1" gutterBottom>
                Create Ride
            </Typography>
            <TextField
                label="Rider ID"
                value={riderId}
                onChange={(e) => setRiderId(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Typography variant="h6" component="h2" gutterBottom>
                Select Pickup Location
            </Typography>
            <GoogleMapsComponent onLocationSelect={handlePickupLocationSelect} />
            {pickupLocation && (
                <Typography variant="body1">
                    Selected Pickup Location: {pickupLocation}
                </Typography>
            )}
            <Typography variant="h6" component="h2" gutterBottom>
                Select Drop Location
            </Typography>
            <GoogleMapsComponent onLocationSelect={handleDropLocationSelect} />
            {dropLocation && (
                <Typography variant="body1">
                    Selected Drop Location: {dropLocation}
                </Typography>
            )}
            <Button variant="contained" color="primary" onClick={handleCreateRide} style={{ marginTop: '20px' }}>
                Create Ride
            </Button>
        </Container>
    );
};

export default CreateRide;
