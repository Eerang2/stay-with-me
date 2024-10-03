package jwlee.staywithme.domain.repository;

import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Transactional
class AccommodationRepositoryTest extends BaseJpaTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void DB에서숙소조회하기() {
        Optional<AccommodationEntity> accommodationEntityOptional = accommodationRepository.findAccommodationEntitiesById(1L);
        Assertions.assertThat(accommodationEntityOptional.isPresent()).isTrue();
    }

    @Test
    @DisplayName("DB에서 숙소 업데이트")
    void update() {
        // given
        Optional<AccommodationEntity> accommodationEntityOptional = accommodationRepository.findAccommodationEntitiesById(1L);
        System.out.println("==> " + accommodationEntityOptional.toString());
        AccommodationEntity accommodationEntity = accommodationEntityOptional.orElseThrow(() -> new RuntimeException("error"));

        // when
        AccommodationEntity updateEntity = accommodationEntity.toBuilder()
                .name("무료 급식소")
                .build();

        AccommodationEntity saved = accommodationRepository.save(updateEntity);

        // then
        Assertions.assertThat(saved.getName()).isEqualTo("무료 급식소");
        System.out.println("update = " + saved);
    }
}