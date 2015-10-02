package wad.controller;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 
    @Transactional
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public String removeFile(@PathVariable Long id) {

        fileObjectRepository.delete(id);
       
        return "redirect:/files";       
    }   
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
 
        FileObject fo = fileObjectRepository.findOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());
        
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

    @Transactional    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        
        FileObject fo = new FileObject();

        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());

        fileObjectRepository.save(fo);

        return "redirect:/files";    
    }
}
