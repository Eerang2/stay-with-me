package jwlee.staywithme.domain.repository;

import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByAccommodationIdAndImageType(Long accommodationId, ImageType imageType);
    List<ImageEntity> findAllByAccommodationIdInAndImageType(List<Long> accommodationList, ImageType imageType);
}
