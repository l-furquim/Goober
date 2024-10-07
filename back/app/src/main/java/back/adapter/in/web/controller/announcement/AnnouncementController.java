package back.adapter.in.web.controller.announcement;

import back.adapter.in.web.controller.announcement.dto.*;
import back.domain.port.in.AnnouncementService;
import back.domain.port.in.AuthService;
import back.domain.port.in.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private static final Logger log = LoggerFactory.getLogger(AnnouncementController.class);
    private final AnnouncementService announcementService;
    private final AuthService authService;
    private final ImageService imageService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, AuthService authService, ImageService imageService) {
        this.announcementService = announcementService;
        this.authService = authService;
        this.imageService = imageService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAnnouncementResponseDto> createAnnouncement(@RequestPart CreateAnnouncementRequestDto announcementJson,
                                                                            @RequestHeader("Authorization") String token,
                                                                            @RequestPart List<MultipartFile> announcementImages){


        var announcerId = authService.getUserByToken(token.substring(7));
        var imagesPath = imageService.saveMultipleImages(announcementImages, "announcement/" + announcementJson.announcementName() + "/");

        announcementService.createAnnouncement(announcementJson ,announcerId , imagesPath);


        return ResponseEntity.ok().body(new CreateAnnouncementResponseDto("Anuncio criado com sucesso"));
    }
    @GetMapping("/find/search={words}/price={lowPrice}/{highPrice}")
    public ResponseEntity<FindAnnouncementIfContainsResponseDto> findAnnouncementIfContains(@PathVariable("words")
                                                                                            String words, @PathVariable
                                                                                            ("lowPrice") Double lowPrice,
                                                                                            @PathVariable("highPrice")Double highPrice){

        var announcement = announcementService.findAnnouncementNamePriceFilter(words, lowPrice, highPrice);

        return ResponseEntity.ok().body(new FindAnnouncementIfContainsResponseDto(announcement));
    }

    @GetMapping("/find/all")
    public ResponseEntity<FindAllAnnouncementsResponseDto> findAllAnnounces(){

        var announces = announcementService.findAll();

        return ResponseEntity.ok().body(new FindAllAnnouncementsResponseDto(announces));
    }

    @GetMapping("/find/search={words}")
    public ResponseEntity<FindAnnouncementIfContainsResponseDto> findAnnouncementIfContainsName(@PathVariable("words") String words){

        var announcements = announcementService.findAnnouncementNameFilter(words);

        return ResponseEntity.ok().body(new FindAnnouncementIfContainsResponseDto(announcements));
    }


    @GetMapping("/get/images/src/main/resources/static/images/announcement/{announcementPath}")
    public ResponseEntity<byte[]> getAnnouncementsImages(@PathVariable("announcementPath") String annPath){
        var images=  imageService.findImageByDirName("announcement/"+ annPath);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(images);
    }

    @DeleteMapping("/delete/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") String id){
        announcementService.deleteAnnouncement(new DeleteAnnouncementRequestDto(UUID.fromString(id)));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FindAnnouncementByIdResponseDto> findAnnouncementById(@PathVariable("id")String id){
        var announcement = announcementService.findAnnouncementById(id);


        return ResponseEntity.ok().body(new FindAnnouncementByIdResponseDto(announcement));
    }


}
