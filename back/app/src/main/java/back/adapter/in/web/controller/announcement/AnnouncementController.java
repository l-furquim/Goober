package back.adapter.in.web.controller.announcement;


import back.adapter.in.web.controller.announcement.dto.CreateAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.CreateAnnouncementResponseDto;
import back.adapter.in.web.controller.announcement.dto.FindAllAnnouncementsResponseDto;
import back.adapter.in.web.controller.announcement.dto.FindAnnouncementIfContainsResponseDto;
import back.domain.port.in.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private static final Logger log = LoggerFactory.getLogger(AnnouncementController.class);
    private final AnnouncementService announcementService;


    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAnnouncementResponseDto> createAnnouncement(@RequestBody CreateAnnouncementRequestDto createAnnouncementRequestDto){


        announcementService.createAnnouncement(createAnnouncementRequestDto);


        return ResponseEntity.ok().body(new CreateAnnouncementResponseDto("Anuncio criado com sucesso"));
    }
    @GetMapping("/find/search={words}/price={lowPrice}-{highPrice}")
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



}
