package com.example.zaman_application_service.service;

import com.example.zaman_resource_service.entity.Location;
import com.example.zaman_application_service.repository.LocationRepository;
import com.example.zaman_resource_service.dto.CreateLocationDto;
import com.example.zaman_resource_service.dto.UpdateLocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void create(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void update(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location getOne(long locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> getAllByUser(long userId) {
        return locationRepository.findAllById(Collections.singleton(userId));
    }

    @Override
    public void delete(long locationId) {
        locationRepository.deleteById(locationId);
    }
}
