package jwlee.staywithme.domain.model;

import jakarta.validation.constraints.NotNull;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "업소이름은 필수압니다.")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private AccAddress address;

    @NotNull
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

