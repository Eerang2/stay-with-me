package jwlee.staywithme.web.dto;


import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AccommodationImageRes {


    @Getter
    @AllArgsConstructor
    public static class Upload {
        private Long id;
        private ImageType imageType;
        private String path;

        public static Upload from(ImageEntity imageEntity) {
            return new Upload(imageEntity.getId(), imageEntity.getImageType(), imageEntity.getPath());
        }
    }
}
