package jwlee.staywithme.domain.service;

import jwlee.staywithme.BaseTest;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.AccommodationImage;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

class AccommodationServiceTest extends BaseTest {

    @Autowired
    private AccommodationService accommodationService;

    @Test
    void 숙소조회() {
        Accommodation accommodation = accommodationService.getAccommodationById(1L);
        Assertions.assertThat(accommodation).isNotNull();
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
                .tel("010-1234-5678")
                .build();
        Accommodation saved = accommodationService.create(accommodation, Arrays.asList(
                AccommodationImage.builder().imageType(ImageType.MAIN).path("/img/test1.jpg").build()
                , AccommodationImage.builder().imageType(ImageType.DETAIL).path("/img/test2.jpg").build()
                , AccommodationImage.builder().imageType(ImageType.THUMBNAIL).path("/img/test3.jpg").build()
        ));
    }
}