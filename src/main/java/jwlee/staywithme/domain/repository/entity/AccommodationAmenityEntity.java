package jwlee.staywithme.domain.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AccommodationAmenityEntity {
    @Id
    private Long id;

}
