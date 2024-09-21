package jwlee.staywithme.domain.model;

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
}
