package jwlee.staywithme.domain.es.documents;

import jakarta.persistence.Id;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "accommodation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationDoc {
    @Id
    private Long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private AccommodationType type;
    private Boolean isFreeParking;
    private ParkingType parkingType;
    private String locationGuideText;
}
