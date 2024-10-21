package jwlee.staywithme.domain.service;

import jakarta.transaction.Transactional;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.exceptions.NotfoundImageException;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.AccommodationImage;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.domain.repository.ImageRepository;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ImageRepository imageRepository;

    public static final String DEFAULT_MAIN_IMG = "/img/default_main_img.jpg";

    @Transactional
    @Cacheable(cacheNames = "accommodation", key = "#id", cacheManager = "accommodationCacheManager", condition = "#id > 0")
    public Accommodation findAccommodationById(long id) {
        AccommodationEntity accommodationEntity = accommodationRepository.findAccommodationEntitiesById(id).orElseThrow(NotfoundImageException::new);
        ImageEntity imageEntity = imageRepository.findByAccommodationIdAndImageType(id, ImageType.MAIN).orElseThrow(NotfoundImageException::new);
        return Accommodation.from(accommodationEntity, imageEntity.getPath());
    }

//
//    public Accommodation update(Accommodation accommodation, long id) {
//        // 업소 꺼내기
//        AccommodationEntity entity = accommodationRepository.findAccommodationEntitiesById(id)
//                .orElseThrow(() -> new NoSuchAccommodation());
//
//        entity.toBuilder()
//                .name(accommodation.getName())
//                .description(accommodation.getDescription())
//                .tel(accommodation.getTel())
//                .longitude(accommodation.getGeoLocation().getLongitude())
//                .latitude(accommodation.getGeoLocation().getLatitude())
//                .build();
//
//        // save
//        AccommodationEntity updatedAccommodationEntity = accommodationRepository.save(entity);
//
//        // 캐스팅
//        Accommodation update = Accommodation.from(updatedAccommodationEntity);
//        return update;
//    }

    @Transactional
    public Accommodation create(Accommodation accommodation, List<AccommodationImage> imageList) {
        AccommodationEntity saveAccommodationEntity = accommodationRepository.save(accommodation.toEntity());
        long newAccommodationId = saveAccommodationEntity.getId();

        List<ImageEntity> imageEntities = imageList.stream()
                .map(accommodationImage -> accommodationImage.toSaveEntity(newAccommodationId))
                .toList();

        Iterable<ImageEntity> savedImageEntities = imageRepository.saveAll(imageEntities);
        String mainImagePath = StreamSupport.stream(savedImageEntities.spliterator(), false)
                .filter(imageEntity -> imageEntity.getImageType().equals(ImageType.MAIN))
                .map(ImageEntity::getPath)
                .findFirst().orElse("/img/default_main_image.jpg");

        return Accommodation.from(saveAccommodationEntity, mainImagePath);
    }


    @Transactional
    public List<Accommodation> findAllByRegionId(long regionId) {
        List<AccommodationEntity> accommodationEntities = accommodationRepository.findAllByRegionId(regionId);
        List<Long> accommodationIdList = accommodationEntities.stream()
                        .map(AccommodationEntity::getId)
                        .toList();

        Map<Long, String> mainImageMap = imageRepository.findAllByAccommodationIdInAndImageType(accommodationIdList, ImageType.MAIN)
                .stream()
                .collect(Collectors.toMap(ImageEntity::getAccommodationId, ImageEntity::getPath));

        return accommodationEntities.stream()
                .map(accommodationEntity -> {
                        String mainImagePath = mainImageMap.getOrDefault(accommodationEntity.getId(), DEFAULT_MAIN_IMG);
                        return Accommodation.from(accommodationEntity, mainImagePath);
                })
                .toList();
    }
}
