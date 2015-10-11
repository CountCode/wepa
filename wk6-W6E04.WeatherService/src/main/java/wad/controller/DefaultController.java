package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.service.LocationService;

@Controller
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private LocationService locationService;        

    @RequestMapping(method = RequestMethod.GET)
    public String getDefaultPage() {
        return "redirect:/locations";
    }
    
    @RequestMapping(value="/flushcaches", method=RequestMethod.GET)
    public String flushCaches(Model model) {

        locationService.flushCache();
        return "locations";
    }    
    
}
