package jwlee.staywithme.domain.repository;

import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.assertj.core.api.Assertions;
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
}