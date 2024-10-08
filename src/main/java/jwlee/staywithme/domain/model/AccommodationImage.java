package jwlee.staywithme.domain.model;

import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AccommodationImage {

    private Long id;
    private ImageType imageType;
    private String path;
    private Long accommodationId;

    public ImageEntity toSaveEntity(long accommodationId) {
        return ImageEntity.builder()
                .accommodationId(accommodationId)
                .imageType(this.imageType)
                .path(this.path)
                .build();
    }
}
