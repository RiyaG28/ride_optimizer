import React from 'react';
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';

const MapContainer = () => {
    const center = { lat: -34.397, lng: 150.644 };

    return (
        <LoadScript googleMapsApiKey="AIzaSyAUeWHU57V7Grg5X1acWtu1lygfqwsFtGw">
            <GoogleMap
                mapContainerStyle={{ height: '400px', width: '100%' }}
                center={center}
                zoom={10}
            >
                <Marker position={center} />
            </GoogleMap>
        </LoadScript>
    );
};

export default MapContainer;
