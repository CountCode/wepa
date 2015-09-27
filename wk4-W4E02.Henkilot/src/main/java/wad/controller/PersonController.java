package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import wad.domain.Person;
import wad.repository.PersonRepository;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> getPersons() {
        return personRepository.findAll();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findOne(id);
    }
    
    /*
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        personRepository.delete(id);  
        // return personRepository.delete(id); 
    }    
*/
    
    @RequestMapping(method=RequestMethod.POST)
    public Person postPerson(@RequestBody Person person) {
        // check 
        String nimi=person.getName();
        List<Person> lista = personRepository.findByName(nimi);
        
        if (lista.isEmpty()){
              
            return personRepository.save(person); 
       } 
      return null; // mitä pitäisi palauttaa??
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Person putPerson(@RequestBody Person person, @PathVariable Long id) {
        Person person2 = personRepository.findOne(id);
        person2.setName(person.getName());
        return personRepository.save(person2);
    }    
     
}