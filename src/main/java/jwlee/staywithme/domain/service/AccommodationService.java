package jwlee.staywithme.domain.service;

import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.exceptions.NoSuchAccommodation;
import jwlee.staywithme.domain.exceptions.NotfoundImageException;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.AccommodationImage;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.domain.repository.ImageRepository;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ImageRepository imageRepository;

    public Accommodation getAccommodationById(long id) {
        AccommodationEntity accommodationEntity = accommodationRepository.findAccommodationEntitiesById(id).orElseThrow(() -> new NoSuchAccommodation());
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
}
