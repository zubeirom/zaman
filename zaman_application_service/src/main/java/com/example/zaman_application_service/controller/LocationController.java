package com.example.zaman_application_service.controller;

import com.example.zaman_resource_service.entity.Location;
import com.example.zaman_application_service.service.ILocationService;
import com.example.zaman_resource_service.dto.CreateLocationDto;
import com.example.zaman_resource_service.dto.UpdateLocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @PostMapping("/locations")
    @ResponseBody
    public void createLocation(@RequestBody Location location) {
        locationService.create(location);
    }

    @RequestMapping(value="/locations/{locationId}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("locationId") long locationId) {
        return locationService.getOne(locationId);
    }

    @GetMapping("/locations")
    @ResponseBody
    public List<Location> getAllLocations() {
        return locationService.getAll();
    }

    @RequestMapping(value="/locations/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocationByUser(@PathVariable("userId") long userId) {
        return locationService.getAllByUser(userId);
    }

    @PutMapping("/locations")
    @ResponseBody
    public void updateLocation(@RequestBody Location location) {
        locationService.update(location);
    }

    @DeleteMapping("/locations/{locationId}")
    @ResponseBody
    public void deleteLocation(@PathVariable("locationId") long locationId) {
        locationService.delete(locationId);
    }

}
