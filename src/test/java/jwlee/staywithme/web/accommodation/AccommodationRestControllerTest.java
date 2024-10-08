package jwlee.staywithme.web.accommodation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseMockMvcTest;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class AccommodationRestControllerTest extends BaseMockMvcTest {

    private static final String CREATE_POST_PATH = "/api/accommodation/create";

    @Test
    void 숙소조회API테스트() throws Exception {
        final ResultActions actions = mockMvc.perform(get("/api/accommodation/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울 게스트하우스"));
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    @DisplayName("숙소 등록 성공")
    void save() throws Exception {
        // given
        String requestBody = objectMapper.writeValueAsString(
                Accommodation.builder()
                        .name("신라호텔")
                        .description("5성급 최고급 호텔")
                        .geoLocation(new GeoLocation(123.123, 10.10))
                        .type(AccommodationType.HOTEL)
                        .status(AccommodationStatus.AVAILABLE)
                        .parkingInfo(new ParkingInfo(true, ParkingType.MACHINE))
                        .locationGuideText("예약 후에 문자 드려요")
                        .tel("010-1234-5678")
                        .build()
        );

        // when
        final ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post(CREATE_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)

        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("숙소 등록 실패")
    void save_fail() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                Accommodation.builder()
                        .name("")
                        .description("5성급 최고급 호텔")
                        .tel("010-1234-5678")
                        .build()
        );

        final ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post(CREATE_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("findAccById 로 API 조회")
    void jpaGetName() {
        AccommodationEntity entity = accommodationRepository.findAccommodationEntitiesById(1L).get();
        assertEquals("서울 게스트하우스", entity.getName());
    }
}