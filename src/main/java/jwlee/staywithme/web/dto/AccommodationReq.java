package jwlee.staywithme.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.model.*;
import jwlee.staywithme.web.validators.ValidAccommodationImage;
import lombok.*;

import java.util.List;

@Getter
public class AccommodationReq {

    @Getter
    public static class Create {
        @NotBlank(message = "숙소명은 필수값입니다")
        private String name;

        @NotBlank
        private String description;

        @NotNull
        private ParkingInfo parkingInfo;

        @NotNull
        private AccommodationType type;

        private AccommodationStatus status;

        @NotNull
        private GeoLocation geoLocation;

        private String locationGuideText;

        @NotNull
        private Long regionId;

        @NotNull
        @ValidAccommodationImage
        private List<ImageOnCreate> imageList;

        @Builder
        public Create(String name, String description, ParkingInfo parkingInfo, AccommodationType type, AccommodationStatus status, GeoLocation geoLocation, String locationGuideText, List<ImageOnCreate> imageList, Long regionId) {
            this.name = name;
            this.description = description;
            this.parkingInfo = parkingInfo;
            this.type = type;
            this.status = status;
            this.geoLocation = geoLocation;
            this.locationGuideText = locationGuideText;
            this.imageList = imageList;
            this.regionId = regionId;
        }

        public Accommodation toAccommodation() {
            return Accommodation.builder()
                    .name(name)
                    .description(description)
                    .parkingInfo(parkingInfo)
                    .type(type)
                    .status(status)
                    .geoLocation(geoLocation)
                    .locationGuideText(locationGuideText)
                    .parkingInfo(parkingInfo)
                    .region(regionId)
                    .build();
        }
    }
    @Getter
    public static class ImageOnCreate {
        private ImageType imageType;
        private String path;

        @Builder
        public ImageOnCreate(ImageType imageType, String path) {
            this.imageType = imageType;
            this.path = path;
        }
        public AccommodationImage toAccommodationImage() {
            return AccommodationImage.builder()
                    .imageType(imageType)
                    .path(path)
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
