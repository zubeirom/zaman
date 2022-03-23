package com.example.zaman_application_service.service;


import com.example.zaman_resource_service.entity.Location;
import com.example.zaman_resource_service.dto.CreateLocationDto;
import com.example.zaman_resource_service.dto.UpdateLocationDto;

import java.util.List;

public interface ILocationService {
    //void create(CreateLocationDto createLocationDto);
    void create(Location location);
    //void update(UpdateLocationDto updateLocationDto);
    void update(Location location);
    Location getOne(long locationId);
    List<Location> getAll();
    List<Location> getAllByUser(long userId);
    void delete(long locationId);
}
