package back.adapter.out.persistence.announcement;

import back.adapter.out.persistence.mapper.announcement.AnnouncementMapper;
import back.adapter.out.persistence.repository.announcement.AnnouncementJpaRepository;
import back.adapter.out.persistence.repository.product.ProductJpaRepository;
import back.domain.model.announcement.Announcement;
import back.domain.port.out.AnnouncementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.awt.SystemColor.text;

@Component
public class AnnouncementPersistenceAdapter implements AnnouncementRepository {

    private static final Logger log = LoggerFactory.getLogger(AnnouncementPersistenceAdapter.class);

    private final AnnouncementMapper announcementMapper;
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public AnnouncementPersistenceAdapter(AnnouncementMapper announcementMapper, AnnouncementJpaRepository announcementJpaRepository, ProductJpaRepository productJpaRepository) {
        this.announcementMapper = announcementMapper;
        this.announcementJpaRepository = announcementJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public void save(Announcement announcement) {
        var producEntityList = announcement.getProducts().stream().map(prod
        -> productJpaRepository.findById(prod.getProductId()).get()).toList();


        var anEntity = announcementMapper.toEntity(announcement, producEntityList);

        log.info(announcement.getProducts().toString());

        announcementJpaRepository.save(anEntity.get());
    }

    @Override
    public void delete(Announcement announcement) {


        var anEntity = announcementJpaRepository.findById(UUID.fromString(announcement.getAnnouncerId()));



        announcementJpaRepository.save(anEntity.get());
    }

    @Override
    public Optional<Announcement> findAnnouncementById(UUID id) {
        var anEntity =  announcementJpaRepository.findById(id);

        if(anEntity.isEmpty()){
            return Optional.empty();
        }

        return announcementMapper.toDomain(anEntity.get());
    }

    @Override
    public Optional<List<Announcement>> findAnnouncementsByName(String name) {

        var ansEntity = announcementJpaRepository.findAnnouncementsByName(name);

        if(ansEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(ansEntity.get().stream().map(ann -> announcementMapper.toDomain(ann).get()).toList());
    }

    @Override
    public Optional<List<Announcement>> findAnnouncementsByAnnouncerName(String name) {
        var anEntity = announcementJpaRepository.findAnnouncementsByAnnouncerName(name);

        if(anEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(anEntity.get().stream().map(an -> announcementMapper.toDomain(an).get()).toList());
    }

    @Override
    public Optional<List<Announcement>> findAnnouncementsByPrice(BigDecimal price) {
        var anEntity = announcementJpaRepository.findAnnouncementByPrice(price);

        if(anEntity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(anEntity.get().stream().map(an -> announcementMapper.toDomain(an).get()).toList());
    }

    @Override
    public Optional<List<Announcement>> findAnnouncementNamePriceFilter(String name, Double lowPrice, Double highPrice) {

        var textFormatted = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

        var ansEntity = announcementJpaRepository.findAnnouncementsByUpperNameAndPrice(textFormatted, BigDecimal.valueOf(lowPrice), BigDecimal.valueOf(highPrice));

        if(ansEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(ansEntity.get().stream().map(ann -> announcementMapper.toDomain(ann).get()).toList());
    }


    @Override
    public List<Announcement> findAll() {

        var announces = announcementJpaRepository.findAll();

        return announces.stream().map(ann -> announcementMapper.toDomain(ann).get()).toList();

    }

    @Override
    public List<Announcement> findAllWithFilter() {
        return null;
    }

    @Override
    public Optional<List<Announcement>> findAnnouncewmentNameFilter(String name) {
        var textFormatted = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

        var ansEntity = announcementJpaRepository.findAnnouncementsByUpperNameFilter(textFormatted);

        if(ansEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(ansEntity.get().stream().map(ann -> announcementMapper.toDomain(ann).get()).toList());
    }


}
