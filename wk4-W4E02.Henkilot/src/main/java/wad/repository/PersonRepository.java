package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import wad.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    
    @RestResource(exported = false)
    @Override
    public void delete(Long id);
    
    /**
     * Find person by name.
     */
    public Person findByName(String name); 
        public Person findByUsername(String name);

}