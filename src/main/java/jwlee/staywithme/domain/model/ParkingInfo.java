package jwlee.staywithme.domain.model;


import jwlee.staywithme.domain.enums.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingInfo {

    private Boolean isFree;
    private ParkingType parkingType;
}
