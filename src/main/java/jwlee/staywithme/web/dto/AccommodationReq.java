package jwlee.staywithme.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.model.AccAddress;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import lombok.*;

@Getter
public class AccommodationReq {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
        @NotBlank(message = "숙소명은 필수값입니다")
        private String name;

        @NotBlank
        private String description;

        @NotNull
        private ParkingInfo parkingInfo;

        @NotNull
        private AccommodationType type;

        @NotNull
        private AccommodationStatus status;

        @NotNull
        private GeoLocation geoLocation;

        private String locationGuideText;

        private String tel;

        public Accommodation toAccommodation() {
            return Accommodation.builder()
                    .name(this.name)
                    .description(this.description)
                    .geoLocation(this.geoLocation)
                    .type(this.type)
                    .locationGuideText(this.locationGuideText)
                    .status(AccommodationStatus.AVAILABLE)
                    .parkingInfo(this.parkingInfo)
                    .build();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Update {

        @NotNull
        private Long id;

        @NotNull
        private String name;

        @NotNull
        private String description;

        @NotNull
        private String usageGuide; // 이용안내

        @NotNull
        private String reservationGuide;

        @NotNull
        private AccAddress address;

        @NotNull
        private GeoLocation geoLocation;

        @NotNull
        private String tel;
    }
}
