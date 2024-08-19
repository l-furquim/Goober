package back.adapter.out.persistence.announcement;

import back.adapter.out.persistence.mapper.announcement.AnnouncementMapper;
import back.adapter.out.persistence.repository.announcement.AnnouncementJpaRepository;
import back.domain.model.announcement.Announcement;
import back.domain.port.out.AnnouncementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementPersistenceAdapter implements AnnouncementRepository {

    private static final Logger log = LoggerFactory.getLogger(AnnouncementPersistenceAdapter.class);
    private final AnnouncementMapper announcementMapper;
    private final AnnouncementJpaRepository announcementJpaRepository;

    public AnnouncementPersistenceAdapter(AnnouncementMapper announcementMapper, AnnouncementJpaRepository announcementJpaRepository) {
        this.announcementMapper = announcementMapper;
        this.announcementJpaRepository = announcementJpaRepository;
    }

    @Override
    public void save(Announcement announcement) {
        var anEntity = announcementMapper.toEntity(announcement);

        log.info(announcement.getProducts().toString());

        announcementJpaRepository.save(anEntity.get());
    }

    @Override
    public void delete(Announcement announcement) {
        var anEntity = announcementMapper.toEntity(announcement);



        announcementJpaRepository.save(anEntity.get());
    }

    @Override
    public Optional<Announcement> findAnnouncementById(Integer id) {
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
    public Optional<List<Announcement>> findAnnouncementByUpperName(String name) {
        var ansEntity = announcementJpaRepository.findAnnouncementsByUpperName(name);

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
}
