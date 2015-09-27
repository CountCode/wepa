package wad.service;
// import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.domain.Apartment;
import wad.repository.ApartmentRepository;


@Service
public class ApartmentService {
    
    private RestTemplate restTemplate;
    
    public ApartmentService() {
        this.restTemplate = new RestTemplate();
    }
    
    @Autowired
    private ApartmentRepository apartmentRepository;

}