package jwlee.staywithme.domain.service;

import jwlee.staywithme.BaseTest;
import jwlee.staywithme.domain.model.Accommodation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AccommodationServiceTest extends BaseTest {

    @Autowired
    private AccommodationService accommodationService;

    @Test
    void 숙소조회() {
        Accommodation accommodation = accommodationService.getAccommodationById(1L);
        Assertions.assertThat(accommodation).isNotNull();
    }
}