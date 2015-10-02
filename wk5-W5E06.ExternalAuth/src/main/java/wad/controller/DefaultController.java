package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.repository.MessageRepository;

@Controller
public class DefaultController {

    @Autowired
    private MessageRepository messageRepository;    
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/messages";
    }
    
    @RequestMapping(value="/error")
    public String error(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }       
}
