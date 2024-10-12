package jwlee.staywithme.domain.repository.entity;


import jakarta.persistence.*;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ParkingType;
import lombok.*;

@Entity
@Table(name = "accommodation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acmd_no")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    private double latitude;

    private double longitude;

    @Column(nullable = false)
    private String locationGuideText;

    @Column(nullable = false)
    private boolean isFreeParking;

    @Enumerated(EnumType.STRING)
    private ParkingType parkingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationType type;
}


