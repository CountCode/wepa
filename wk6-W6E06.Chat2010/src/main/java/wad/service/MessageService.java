package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wad.domain.Message;


    
@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate template;
    
    private Poliitikko politikko;
    
    @Scheduled(fixedDelay = 10000)
    public void sendMessage() {
      
        Poliitikko poliitikko = new Poliitikko();
        
        Message message = poliitikko.getMessage();
        
        this.template.convertAndSend("/channel/default", message);
    }    
    

    //@Scheduled(fixedDelay = 10000)
    public void addMessage(Message message) {
  
        String channel = message.getChannel();
       // System.out.println("viesti "+message.getContent());
       // System.out.println("kanava "+ channel);
        this.template.convertAndSend("/channel/" +channel, message);
    }
}