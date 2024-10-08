package jwlee.staywithme.domain.model;

import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {

    private long id;

    private String name;

    private String description;

    private String locationGuideText;

    private GeoLocation geoLocation;

    private ParkingInfo parkingInfo;

    private AccommodationStatus status;

    private AccommodationType type;

    private String tel;

    @Setter
    private String mainImagePath;

    public static Accommodation from(AccommodationEntity entity, String mainImagePath) {
        return Accommodation.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .parkingInfo(new ParkingInfo(entity.isFreeParking(), entity.getParkingType()))
                .geoLocation(new GeoLocation(entity.getLatitude(), entity.getLongitude()))
                .type(entity.getType())
                .locationGuideText(entity.getLocationGuideText())
                .tel(entity.getTel())
                .mainImagePath(mainImagePath)
                .build();
    }

    public AccommodationEntity toEntity() {
        return AccommodationEntity.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .isFreeParking(this.parkingInfo.getIsFree())
                .parkingType(this.parkingInfo.getParkingType())
                .locationGuideText(this.locationGuideText)
                .type(this.type)
                .tel(this.tel)
                .build();
    }
}

