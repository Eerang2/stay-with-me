package jwlee.staywithme.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Getter
@Table(name = "amenity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_no", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    private String name;
    private String description;

    @OneToMany
    private List<AccommodationAmenityEntity> accommodationAmenities;
}
