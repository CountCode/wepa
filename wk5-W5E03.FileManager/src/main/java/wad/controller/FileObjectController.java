package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.repository.FileObjectRepository;

@Controller
@RequestMapping("files")
public class FileObjectController {
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String redirect(Model model) {
        model.addAttribute("files", fileObjectRepository.findAll());
        return "files";
    }  
 
    /*
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String get(@PathVariable Long id, Model model) {
 
        model.addAttribute("count", fileObjectRepository.count());
        if (fileObjectRepository.exists(id+1)) model.addAttribute("next", id+1);
        if (fileObjectRepository.exists(id-1)) model.addAttribute("previous", id-1);
        if (fileObjectRepository.exists(id)) model.addAttribute("current", id);
        
        return "files";
    }

    @RequestMapping(value="{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
      
        return fileObjectRepository.findOne(id).getContent();

    }
    */
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        
        FileObject fo = new FileObject();

        fo.setName(file.getName());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
       // fo.setContent(file.getBytes());

        fileObjectRepository.save(fo);

        return "redirect:/files";    

    }

}
