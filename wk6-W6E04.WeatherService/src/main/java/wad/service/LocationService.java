package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wad.domain.Location;
import wad.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
        
    @Cacheable("locations")
    public Location findOne(Long id){
        return locationRepository.findOne(id);
    }
    
    @Cacheable("locations")
    public List<Location> findAll(){
        return locationRepository.findAll();
    }    
    
    //@Cacheable("locations")
    @CacheEvict(value="locations", allEntries = true)    
    public void save(Location loc){
          locationRepository.save(loc);
    }   
    
    //@Cacheable("locations") 
    @CacheEvict(value = "locations", allEntries = true)
    public void flushCache() {
    // Intentionally blank
  }    
}
