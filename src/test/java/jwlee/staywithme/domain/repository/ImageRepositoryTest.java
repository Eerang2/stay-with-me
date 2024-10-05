package jwlee.staywithme.domain.repository;

import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.exceptions.NotfoundImageException;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
public class ImageRepositoryTest extends BaseJpaTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("메인이미지 조회")
    void getImage() {
        ImageEntity imageEntity = imageRepository.findByAccommodationIdAndImageType(1L, ImageType.MAIN).orElseThrow(NotfoundImageException::new);
        Assertions.assertNotNull(imageEntity);
        assertThat(imageEntity).isNotNull();
    }

    @Test
    @DisplayName("이미지 저장")
    void saveImage() {
        ImageEntity imageEntity = ImageEntity.builder()
                .accommodationId(1L)
                .imageType(ImageType.MAIN)
                .path("/img/test/123.png")
                .build();

        ImageEntity saved = imageRepository.save(imageEntity);
        assertThat(saved).isNotNull();
        assertThat(saved.getPath()).isEqualTo(imageEntity.getPath());
    }

}
