package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import wad.domain.Message;
import wad.service.MessageService;

    
@Controller
public class MessageController {
    
    @Autowired
    MessageService messageService;
   
    @MessageMapping("/messages") 
    public void handleMessage(Message message) throws Exception {

       
        messageService.addMessage(message);
    }   
}