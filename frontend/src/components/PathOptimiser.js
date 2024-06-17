import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { TextField, Button, MenuItem, Typography } from '@mui/material';
import styles from "./path-optimizer.module.css"
const PathOptimiser = () => {
    const [places, setPlaces] = useState([]);
    const [pickup, setPickup] = useState('');
    const [destination, setDestination] = useState('');
    const [shortestPath, setShortestPath] = useState('');

    useEffect(() => {
        fetchPlaces();
    }, []);

    const fetchPlaces = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/places/names');
            setPlaces(response.data);
        } catch (error) {
            console.error('Error fetching places:', error);
        }
    };

    const handlePickupChange = (event) => {
        setPickup(event.target.value);
    };

    const handleDestinationChange = (event) => {
        setDestination(event.target.value);
    };

    const handleSubmit = () => {
        // Check if both pickup and destination are selected
        if (!pickup || !destination) {
            console.error('Please select both pickup and destination.');
            return;
        }

        // Call the shortest path API with the selected pickup and destination
        axios.post('http://localhost:8080/api/places/shortestPath', { start: pickup, end: destination })
            .then(response => {
                setShortestPath(response.data);
            })
            .catch(error => {
                console.error('Error fetching shortest path:', error);
            });
    };


    return (
        <div margin="auto" className={styles.container}>
            {/* classname={styles.name} */}
            <h1>Find your Shortest Route here!</h1>
            <TextField
                select
                id='Pickup'
                label="Pickup"
                value={pickup}
                onChange={handlePickupChange}
                fullWidth
                margin="normal"
            >
                {places.map((place, index) => (
                    <MenuItem key={index} value={place}>
                        {place}
                    </MenuItem>
                ))}
            </TextField>

            <TextField
                select
                id='Destination'
                label="Destination"
                value={destination}
                onChange={handleDestinationChange}
                fullWidth
                margin="normal"
            >
                {places.map((place, index) => (
                    <MenuItem key={index} value={place}>
                        {place}
                    </MenuItem>
                ))}
            </TextField>

            <Button variant="contained" color="primary" onClick={handleSubmit}>
                Find Shortest Path
            </Button>

            {shortestPath && (
                <Typography variant="h6" margin-top="20">
                    Shortest Path: {shortestPath.join(' -> ')}
                </Typography>
            )}
        </div>
    );
};

export default PathOptimiser;
