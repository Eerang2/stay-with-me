package jwlee.staywithme.domain.model;

import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Accommodation {

    @Builder
    public Accommodation(long id, String name, String description, AccAddress address, String tel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.tel = tel;
    }

    private long id;
    private String name;
    private String description;
    private AccAddress address;
    private String tel;

    public static Accommodation of(AccommodationEntity entity) {
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
                .build();
    }
}
