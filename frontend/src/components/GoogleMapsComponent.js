import React, { useCallback, useRef } from 'react';
import { GoogleMap, useLoadScript, Marker } from '@react-google-maps/api';

const mapContainerStyle = {
    width: '100%',
    height: '400px',
};

const center = {
    lat: 13.0827, // Chennai latitude
    lng: 80.2707, // Chennai longitude
};

const GoogleMapsComponent = ({ onLocationSelect }) => {
    const { isLoaded, loadError } = useLoadScript({
        googleMapsApiKey: 'AIzaSyAKDx_3GpVUyam-qHIdOWaDm3YUH_78_Zg', // Replace with your API key
        libraries: ['places'],
    });

    const mapRef = useRef();
    const onMapLoad = useCallback((map) => {
        mapRef.current = map;
    }, []);

    const geocodeLatLng = (lat, lng) => {
        const geocoder = new window.google.maps.Geocoder();
        const latlng = { lat, lng };

        geocoder.geocode({ location: latlng }, (results, status) => {
            if (status === 'OK') {
                if (results[0]) {
                    const locationName = results[0].formatted_address;
                    onLocationSelect({ lat, lng, locationName });
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    };

    const handleMapClick = useCallback((event) => {
        const lat = event.latLng.lat();
        const lng = event.latLng.lng();
        geocodeLatLng(lat, lng);
    }, [onLocationSelect]);

    if (loadError) return <div>Error loading maps</div>;
    if (!isLoaded) return <div>Loading Maps</div>;

    return (
        <GoogleMap
            mapContainerStyle={mapContainerStyle}
            zoom={12}
            center={center}
            onClick={handleMapClick}
            onLoad={onMapLoad}
        >
            <Marker position={center} />
        </GoogleMap>
    );
};

export default GoogleMapsComponent;
