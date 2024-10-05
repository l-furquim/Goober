package back.application.service.announcement;

import back.adapter.in.web.controller.announcement.dto.CreateAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.DeleteAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementNameRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementPriceRequestDto;
import back.adapter.out.persistence.mapper.announcement.AnnouncementMapper;
import back.domain.enums.ProductCategories;
import back.domain.exception.AnnouncementException;
import back.domain.exception.UserException;
import back.domain.model.announcement.Announcement;
import back.domain.model.product.Product;
import back.domain.model.user.User;
import back.domain.port.in.AnnouncementService;
import back.domain.port.out.AnnouncementRepository;
import back.domain.port.out.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private static final Logger log = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
    private final AnnouncementRepository announcementRepository;
    private final ProductRepository productRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, ProductRepository productRepository) {
        this.announcementRepository = announcementRepository;
        this.productRepository = productRepository;
    }

    @Override
    @CacheEvict(value = "announcements", allEntries = true)
    public Announcement createAnnouncement(CreateAnnouncementRequestDto createAnnouncementRequestDto,
                                           User announcer, String imagesPath) {
        if(createAnnouncementRequestDto.announcementName().isBlank()){
            throw new AnnouncementException("Nao e possivel criar um anuncio sem nome !");
        }

        var product = new Product(
                UUID.randomUUID(),
                createAnnouncementRequestDto.announcementName(),
                BigDecimal.valueOf(createAnnouncementRequestDto.announcementPrice()),
                ProductCategories.valueOf(createAnnouncementRequestDto.announcementCategorie()),
                createAnnouncementRequestDto.announcementDescription(),
                imagesPath
        );

        var announcement = new Announcement(
                UUID.randomUUID(),
                BigDecimal.valueOf(createAnnouncementRequestDto.announcementPrice()),
                createAnnouncementRequestDto.announcementName(),
                0,
                0,
                announcer.getUserId().toString(),
                imagesPath,
                new ArrayList<Product>()
        );


        try{
            announcementRepository.save(announcement);
            productRepository.save(product);
            return announcement;
        }catch (IllegalArgumentException e){
            throw new UserException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }
    }

    @Override
    @CacheEvict(value = "announcements", key = "#deleteAnnouncementRequestDto.id()")
    public void deleteAnnouncement(DeleteAnnouncementRequestDto deleteAnnouncementRequestDto) {
        var aAnnouncement = announcementRepository.findAnnouncementById(deleteAnnouncementRequestDto.id());

        if(aAnnouncement.isEmpty()) {
            throw new AnnouncementException("Nao e possivel excluir o anuncio, ele nao existe.");
        }

        try{
            announcementRepository.delete(aAnnouncement.get());
        }catch (IllegalArgumentException e){
            throw new UserException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }

    }
    @Transactional
    @Override
    @CachePut(value = "announcements", key = "#updateAnnouncementPriceRequestDto.id()")
    public void updateAnnouncementPrice(UpdateAnnouncementPriceRequestDto updateAnnouncementPriceRequestDto) {
        var aAnnouncement = announcementRepository.findAnnouncementById(updateAnnouncementPriceRequestDto.id());


        if(aAnnouncement.isEmpty()) {
            throw new AnnouncementException("Nao e possivel mudaro o preço, anuncio nao existe.");
        }

        aAnnouncement.get().setAnnouncementPrice(BigDecimal.valueOf(updateAnnouncementPriceRequestDto.newPrice()));
    }

    @Transactional
    @Override
    @CachePut(value = "announcements", key = "#updateAnnouncementNameRequestDto.id()")
    public void updateAnnouncementName(UpdateAnnouncementNameRequestDto updateAnnouncementNameRequestDto) {
        var aAnnouncement = announcementRepository.findAnnouncementById(updateAnnouncementNameRequestDto.id());


        if(aAnnouncement.isEmpty()) {
            throw new AnnouncementException("Nao e possivel mudaro o nome, anuncio nao existe.");
        }

        aAnnouncement.get().setAnnouncementName(updateAnnouncementNameRequestDto.newName());
    }

    @Override
    public List<Announcement> findAnnouncementNamePriceFilter(String something, Double lowPrice, Double highPrice) {
        var arrayOfWords = something.split(" ");
        var listOfWords = Arrays.stream(arrayOfWords).toList();
        List<Announcement> uniqueAnnouncements = new ArrayList<>();

        for (String singleWord : listOfWords) {
            var announcementsList = announcementRepository.findAnnouncementNamePriceFilter(singleWord, lowPrice,highPrice);

            if (announcementsList.isPresent()) {
                for (Announcement a : announcementsList.get()) {
                    boolean alreadyExists = false;

                    for (Announcement an : uniqueAnnouncements) {
                        if (a.getAnnouncementId().equals(an.getAnnouncementId())) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        uniqueAnnouncements.add(a);
                    }
                }
            }
        }

        return uniqueAnnouncements;
    }

    @Override
    public Optional<List<Announcement>> findAnnouncementByAnnouncerName(String name) {
        return announcementRepository.findAnnouncementsByAnnouncerName(name);
    }

    @Override
    @Cacheable("announcements")
    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    @Override
    public List<Announcement> findAnnouncementNameFilter(String word) {
        var arrayOfWords = word.split(" ");
        var listOfWords = Arrays.stream(arrayOfWords).toList();
        List<Announcement> uniqueAnnouncements = new ArrayList<>();

        for (String singleWord : listOfWords) {
            var announcementsList = announcementRepository.findAnnouncewmentNameFilter(singleWord);

            if (announcementsList.isPresent()) {
                for (Announcement a : announcementsList.get()) {
                    boolean alreadyExists = false;

                    for (Announcement an : uniqueAnnouncements) {
                        if (a.getAnnouncementId().equals(an.getAnnouncementId())) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        uniqueAnnouncements.add(a);
                    }
                }
            }
        }

        return uniqueAnnouncements;
    }

    @Override
    public Announcement findAnnouncementById(String id) {
        var announcement = announcementRepository.findAnnouncementById(UUID.fromString(id));

        return announcement.get();
    }
}
