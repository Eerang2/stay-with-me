package jwlee.staywithme.domain.model;

import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Accommodation {

    private long id;

    private String name;

    private String description;

    private String usageGuide; // 이용안내

    private String reservationGuide;

    private AccAddress address;

    private GeoLocation geoLocation;

    private String tel;

    public static Accommodation from(AccommodationEntity entity) {
        return Accommodation.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .address(
                        AccAddress.builder()
                                .postNo(entity.getPostNo())
                                .address1(entity.getAddress1())
                                .address2(entity.getAddress2())
                                .build()
                )
                .geoLocation(new GeoLocation(entity.getLatitude(), entity.getLongitude()))
                .tel(entity.getTel())
                .build();
    }

    public AccommodationEntity toEntity() {
        return AccommodationEntity.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .postNo(this.address.getPostNo())
                .address1(this.address.getAddress1())
                .address2(this.address.getAddress2())
                .tel(this.tel)
                .build();
    }
}

