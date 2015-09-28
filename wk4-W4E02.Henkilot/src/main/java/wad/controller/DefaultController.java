package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Controller
public class DefaultController {
    
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/persons";
    }
    
    @RequestMapping(value="/authenticate", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity authenticate(@RequestBody Person person) {

        String kayttaja=person.getUsername();
        if (!kayttaja.isEmpty()){
           Person dbPerson=personRepository.findByUsername(kayttaja);
            if (dbPerson!=null){
                if (dbPerson.getPassword().equals(person.getPassword())){
                    ResponseEntity<String> vastaus = new ResponseEntity<>(dbPerson.getName(), HttpStatus.OK);
                    return vastaus; // <200>
                }
            }
        }
        
        ResponseEntity<String> vastaus = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 401
        return vastaus;
    }

}
