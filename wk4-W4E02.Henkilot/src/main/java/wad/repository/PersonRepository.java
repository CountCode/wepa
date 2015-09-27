package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import wad.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    
    /**
     * Find apartment by name.
     */
    public List<Person> findByName(String name);    

}