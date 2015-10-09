package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wad.domain.Location;
import wad.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Cacheable("locations")
    public Location read(Long id){
        return locationRepository.findOne(id);
    }
    
    @Cacheable("locations")
    public List<Location> readAll(){
        return locationRepository.findAll();
    }    
    
    @Cacheable("locations")
    public void read(Location loc){
        locationRepository.save(loc);
    }   
    
}
