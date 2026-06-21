import React, { useEffect, useState } from 'react';
import { StyleSheet, View, Text, ActivityIndicator } from 'react-native';
import MapView, { Polyline } from 'react-native-maps';
import axios from 'axios';

export default function App() {
  const [paths, setPaths] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get('http://192.168.1.7:8080/api/v1/paths')
      .then(response => {
        setPaths(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Error fetching paths from Strivio API:", error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <View style={styles.center}>
        <ActivityIndicator size="large" color="#10B981" />
        <Text style={styles.loadingText}>Connecting to Strivio Engine...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={{
          latitude: 18.5362,
          longitude: 73.8920,
          latitudeDelta: 0.015,
          longitudeDelta: 0.015,
        }}
      >
        {paths.map((path) => (
          <Polyline
            key={path.id}
            coordinates={path.coordinates}
            strokeColor="#10B981"
            strokeWidth={5}
            lineCap="round"
          />
        ))}
      </MapView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1 },
  map: { width: '100%', height: '100%' },
  center: { flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#F9FAFB' },
  loadingText: { marginTop: 10, color: '#374151', fontWeight: '500' },
});