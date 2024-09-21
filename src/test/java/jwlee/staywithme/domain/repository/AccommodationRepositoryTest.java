package jwlee.staywithme.domain.repository;

import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class AccommodationRepositoryTest extends BaseJpaTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void DB에서숙소조회하기() {
        Optional<AccommodationEntity> accommodationEntityOptional = accommodationRepository.findAccommodationEntitiesById(1L);
        Assertions.assertTrue(accommodationEntityOptional.isPresent());
        Assertions.assertEquals("서울 게스트하우스", accommodationEntityOptional.get().getName());
    }
}