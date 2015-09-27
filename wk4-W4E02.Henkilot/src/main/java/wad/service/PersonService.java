package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Service
public class PersonService {
        
    private RestTemplate restTemplate;
    
    public PersonService() {
        this.restTemplate = new RestTemplate();
    }
    
    @Autowired
    private PersonRepository personRepository;

}