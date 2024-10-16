package jwlee.staywithme.testContainers;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import jwlee.staywithme.BaseEsTest;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import jwlee.staywithme.domain.service.AccommodationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ElasticSearchTest extends BaseEsTest {

    public static final String INDEX_NAME = "accommodation";

    @Autowired
    private ElasticsearchClient esClient;

    @Autowired
    private AccommodationService accommodationService;

    @Test
    @DisplayName("es 에 인덱스 생성하고 인덱스에 데이터 넣고 조회하기")
    void es_test() throws Exception {
        boolean indexExists = esClient.indices().exists(e -> e.index(INDEX_NAME)).value();
        if (!indexExists) {
            esClient.indices().create(c -> c.index(INDEX_NAME));
        }

        Accommodation accommodation1 = Accommodation.builder()
                .id(1L)
                .name("비산호텔")
                .type(AccommodationType.HOTEL)
                .locationGuideText("비산사거리에 위치한 호텔")
                .geoLocation(new GeoLocation(37.39716397544742, 126.93087253698494))
                .parkingInfo(new ParkingInfo(true, ParkingType.MACHINE))
                .description("안양 최고의 호텔")
                .build();

        Accommodation accommodation2 = Accommodation.builder()
                .id(2L)
                .name("테스트모텔")
                .type(AccommodationType.MOTEL)
                .locationGuideText("비산 관악산 재개발 지역 인근")
                .geoLocation(new GeoLocation(23.2312412, 112.3423423))
                .parkingInfo(new ParkingInfo(true, ParkingType.FIELD))
                .description("안양 그럭저럭 모텔")
                .build();

        IndexResponse response1 = esClient.index(i -> i
                .index(INDEX_NAME)
                .id(String.valueOf(accommodation1.getId()))
                .document(accommodation1)
        );

        IndexResponse response2 = esClient.index(i -> i
                .index(INDEX_NAME)
                .id(String.valueOf(accommodation2.getId()))
                .document(accommodation2)
        );
        GetResponse<Accommodation> document1 = esClient.get(g -> g
                .index(INDEX_NAME)
                .id("1"),
                Accommodation.class
        );

        Accommodation accommodation1FromEs = document1.source();
        Assertions.assertThat(accommodation1FromEs.getId()).isEqualTo(accommodation1.getId());
        Assertions.assertThat(accommodation1FromEs.getName()).isEqualTo(accommodation1.getName());
    }
}
