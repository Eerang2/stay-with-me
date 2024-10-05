package jwlee.staywithme.domain.repository.entity;


import jakarta.persistence.*;
import jwlee.staywithme.domain.repository.BaseTimeEntity;
import lombok.*;

@Entity
@Table(name = "accommodation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class AccommodationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_no")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 6)
    private String postNo;

    @Column(nullable = false, length = 100)
    private String address1;

    @Column(nullable = false, length = 100)
    private String address2;

    private double latitude;

    private double longitude;

    @Column(nullable = false, length = 500)
    private String usageGuide; // 이용안내

    @Column(nullable = false, length = 500)
    private String reservationGuide;

    @Column(nullable = false, length = 15)
    private String tel;
}


