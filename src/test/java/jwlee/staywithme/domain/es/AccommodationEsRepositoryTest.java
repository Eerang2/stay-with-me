package jwlee.staywithme.domain.es;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jwlee.staywithme.BaseTest;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.es.documents.AccommodationDoc;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public class AccommodationEsRepositoryTest extends BaseTest {

    @Autowired
    AccommodationEsRepository accommodationEsRepository;

    @Autowired
    ElasticsearchClient esClient;

    public static final String INDEX_NAME = "accommodation";

    @Test
    @DisplayName("es에서 숙소명 Like 검색")
    public void getAccommodationFromEs() throws IOException {
        boolean indexExists = esClient.indices().exists(e -> e.index(INDEX_NAME)).value();

        if (!indexExists) {
            esClient.indices().create(c -> c.index(INDEX_NAME));
        }

        AccommodationDoc accommodation1 = AccommodationDoc.builder()
                .id(1L)
                .name("비산호텔")
                .type(AccommodationType.HOTEL)
                .locationGuideText("비산사거리에 위치한 호텔")
                .longitude(37.39716397544742)
                .latitude(126.93087253698494)
                .parkingType(ParkingType.MACHINE)
                .description("안양 최고의 호텔")
                .isFreeParking(true)
                .build();

        AccommodationDoc accommodation2 = AccommodationDoc.builder()
                .id(2L)
                .name("종각호텔")
                .type(AccommodationType.HOTEL)
                .locationGuideText("종각역 근처")
                .longitude(37.19716397544742)
                .latitude(126.13087253698494)
                .parkingType(ParkingType.FIELD)
                .description("종각역 근처 최고의 비지니스 호텔")
                .build();

        accommodationEsRepository.save(accommodation1);
        accommodationEsRepository.save(accommodation2);
        esClient.indices().refresh(r -> r.index(INDEX_NAME));   // index refresh

        List<AccommodationDoc> accommodationDocList = accommodationEsRepository.findByNameLike("비산");
        Assertions.assertThat(accommodationDocList.size()).isEqualTo(1);
        Assertions.assertThat(accommodationDocList.get(0).getName()).isEqualTo(accommodation1.getName());
    }




}
