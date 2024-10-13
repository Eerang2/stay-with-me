package jwlee.staywithme.domain.service;

import jwlee.staywithme.BaseRedisTest;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.AccommodationImage;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AccommodationServiceTest extends BaseRedisTest {

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void 숙소조회() {
        Accommodation accommodation = accommodationService.findAccommodationById(1L);
        assertThat(accommodation).isNotNull();
    }

    @Test
    @DisplayName("findAccById 로 API 조회")
    void jpaGetName() {
        AccommodationEntity entity = accommodationRepository.findAccommodationEntitiesById(1L).get();
        assertEquals("서울 호텔", entity.getName());
    }

    @Test
    @DisplayName("숙소 등록")
    void saveAccommodation() {
        Accommodation accommodation = Accommodation.builder()
                .name("신라호텔")
                .description("5성급 최고급 호텔")
                .geoLocation(new GeoLocation(123.123, 10.10))
                .type(AccommodationType.HOTEL)
                .status(AccommodationStatus.AVAILABLE)
                .parkingInfo(new ParkingInfo(true, ParkingType.MACHINE))
                .locationGuideText("예약 후에 문자 드려요")
                .build();
        Accommodation saved = accommodationService.create(accommodation, Arrays.asList(
                AccommodationImage.builder().imageType(ImageType.MAIN).path("/img/test1.jpg").build()
                , AccommodationImage.builder().imageType(ImageType.DETAIL).path("/img/test2.jpg").build()
                , AccommodationImage.builder().imageType(ImageType.THUMBNAIL).path("/img/test3.jpg").build()
        ));
        assertThat(saved).isNotNull();
    }

    @Test
    @DisplayName("숙소 캐시 잘 걸리는지 테스트")
    void accommodationCache() {
        assertThat(cacheManager.getCache("accommodation").get(1L)).isNull();
        accommodationService.findAccommodationById(1L);
        assertThat(cacheManager.getCache("accommodation").get(1L)).isNotNull();
    }
}