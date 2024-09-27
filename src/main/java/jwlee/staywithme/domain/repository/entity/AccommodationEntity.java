package jwlee.staywithme.domain.repository.entity;


import jakarta.persistence.*;
import jwlee.staywithme.domain.repository.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accommodation")
@Getter
@NoArgsConstructor
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

    @Column(nullable = false, length = 15)
    private String tel;


    @Builder
    public AccommodationEntity (Long id, String name, String description, String postNo, String address1, String address2, String tel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.postNo = postNo;
        this.address1 = address1;
        this.address2 = address2;
        this.tel = tel;
    }
}
