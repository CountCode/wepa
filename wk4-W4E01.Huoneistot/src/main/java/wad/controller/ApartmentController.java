package wad.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Apartment;
import wad.repository.ApartmentRepository;

@RestController
@RequestMapping("apartments")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Apartment> getApartments() {
        return apartmentRepository.findAll();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Apartment getApartment(@PathVariable Long id) {
        return apartmentRepository.findOne(id);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteApartment(@PathVariable Long id) {
        apartmentRepository.delete(id);  
        // return apartmentRepository.delete(id); // palauttaa void pitäisikö palauttaa Apartment?
    }    

    @RequestMapping(method=RequestMethod.POST)
    public Apartment postApartment(@RequestBody Apartment apartment) {
        // check 
        String nimi=apartment.getName();
        List<Apartment> lista = apartmentRepository.findByName(nimi);
        
        if (lista.isEmpty()){
              
            return apartmentRepository.save(apartment); 
       } 
      return null; // mitä pitäisi palauttaa??
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Apartment putApartment(@RequestBody Apartment apartment, @PathVariable Long id) {
        Apartment apartment2 = apartmentRepository.findOne(id);
        apartment2.setName(apartment.getName());
        return apartmentRepository.save(apartment2);
    }    
     
}