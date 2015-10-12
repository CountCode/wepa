package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.FileObject;
import wad.domain.Image;
import wad.repository.FileObjectRepository;
import wad.repository.ImageRepository;
import wad.service.ImageService;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileObjectRepository fileRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getImages(Model model) {
        model.addAttribute("images", imageRepository.findAll());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addFile( RedirectAttributes redirectAttributes,
            @RequestParam("metadata") String metadata,
            @RequestParam("file") MultipartFile file) throws IOException {
        Image image = new Image();
        image.setMetadata(metadata);

        redirectAttributes.addAttribute("id", image.getId());
       // redirectAttributes.addFlashAttribute("image", image);
        
        imageService.add(image, file.getContentType(), file.getOriginalFilename(), file.getBytes());
        return "redirect:/images/{id}";
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String getImagesId(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        model.addAttribute("image", imageRepository.findOne(id));
       
        return "index";
    }
    
    @RequestMapping(value = {"/thumbnails/{id}", "/originals/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage( @RequestHeader(value="If-None-Match", required=false) String header,
            @PathVariable String id) {
        if (header!=null){  
            
            final HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
            //headers.setContentLength(fo.getContentLength());
            //headers.setETag(imageRepository.findOne(id).geteTag());
            
            headers.setETag("\""+id+"\"");
            headers.setCacheControl("public");
            headers.setExpires(Long.MAX_VALUE);           
            return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);                    
        }
        return createResponseEntity(fileRepository.findOne(id), id);
    }

    private ResponseEntity<byte[]> createResponseEntity(FileObject fo, String id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.setCacheControl("public");
        headers.setExpires(Long.MAX_VALUE);
        //headers.setETag(imageRepository.findOne(fo.getId()).geteTag());
        headers.setETag("\""+id+"\"");
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }
}
