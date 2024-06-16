import React, { useState } from 'react';
import axios from 'axios';

const RideRequest = () => {
    const [pickupLocation, setPickupLocation] = useState('');
    const [dropoffLocation, setDropoffLocation] = useState('');

    const handleRequestRide = async () => {
        try {
            const response = await axios.post('/api/rides/request', { pickupLocation, dropoffLocation });
            alert('Ride requested successfully');
        } catch (error) {
            alert('Ride request failed');
        }
    };

    return (
        <div>
            <h2>Request a Ride</h2>
            <input
                type="text"
                placeholder="Pickup Location"
                value={pickupLocation}
                onChange={(e) => setPickupLocation(e.target.value)}
            />
            <input
                type="text"
                placeholder="Dropoff Location"
                value={dropoffLocation}
                onChange={(e) => setDropoffLocation(e.target.value)}
            />
            <button onClick={handleRequestRide}>Request Ride</button>
        </div>
    );
};

export default RideRequest;
