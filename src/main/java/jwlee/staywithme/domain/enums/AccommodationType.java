package jwlee.staywithme.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccommodationType {
    HOTEL("HOTEL"), MOTEL("MOTEL"), PENSION("PENSION");
    private String value;
}
