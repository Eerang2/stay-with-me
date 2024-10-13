package jwlee.staywithme.domain.repository;

import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.repository.entity.AmenityEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccommodationAmenityRepositoryTest extends BaseJpaTest {

    @Autowired
    private AccommodationAmenityRepository accommodationAmenityRepository;

    @Test
    @DisplayName("특정 amenity 가져오기")
    void getAmenity() {
        List<AmenityEntity> amenityEntities = accommodationAmenityRepository.findAmenitiesByAccommodationId(1L);
        assertThat(amenityEntities.size()).isEqualTo(3);
        assertThat(amenityEntities.get(0).getName()).isEqualTo("에어컨");
    }

}