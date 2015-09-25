package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import wad.domain.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    
    
    /**
     * Find apartment by name.
     */
    public Apartment findByName(@Param("name") String name);    

}
