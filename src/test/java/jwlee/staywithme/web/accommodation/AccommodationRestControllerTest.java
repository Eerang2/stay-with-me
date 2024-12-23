package jwlee.staywithme.web.accommodation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseMockMvcTest;
import jwlee.staywithme.domain.enums.AccommodationStatus;
import jwlee.staywithme.domain.enums.AccommodationType;
import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.enums.ParkingType;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.GeoLocation;
import jwlee.staywithme.domain.model.ParkingInfo;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.web.dto.AccommodationReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class AccommodationRestControllerTest extends BaseMockMvcTest {

    private static final String CREATE_POST_PATH = "/api/accommodation/create";

    @Test
    @DisplayName("숙소조회 API 테스트")
    void findAccommodation() throws Exception {
        final ResultActions actions = mockMvc.perform(get("/api/accommodation/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울 호텔"));
    }

    @Test
    @DisplayName("없는 숙소 조회")
    void noAccommodation() throws Exception {
        final ResultActions resultActions = this.mockMvc.perform(
                get("/api/accommodation/0")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(status().isBadRequest());
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
                AccommodationReq.Create.builder()
                        .name("신라호텔")
                        .description("5성급 최고급 호텔")
                        .geoLocation(new GeoLocation(123.123, 10.10))
                        .type(AccommodationType.HOTEL)
                        .status(AccommodationStatus.AVAILABLE)
                        .parkingInfo(new ParkingInfo(true, ParkingType.MACHINE))
                        .locationGuideText("예약 후에 문자 드려요")
                        .regionId(7L)
                        .imageList(Arrays.asList(
                                AccommodationReq.ImageOnCreate.builder().imageType(ImageType.MAIN).path("/img/test1.jpg").build()
                                ,AccommodationReq.ImageOnCreate.builder().imageType(ImageType.DETAIL).path("/img/test2.jpg").build()
                                ,AccommodationReq.ImageOnCreate.builder().imageType(ImageType.THUMBNAIL).path("/img/test3.jpg").build()
                        ))
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
    @DisplayName("숙소 이미지 잘못 올려보고 400에러 뜨는지 확인")
    void imageError() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                AccommodationReq.Create.builder()
                        .name("흔하디 흔한 숙소")
                        .type(AccommodationType.HOTEL)
                        .locationGuideText("비산사거리 도보 5분")
                        .geoLocation(new GeoLocation(123.233, 11.1323))
                        .parkingInfo(new ParkingInfo(true, ParkingType.MACHINE))
                        .description("시설이 좋음ㅇㅇ")
                        .imageList(Arrays.asList(
                                AccommodationReq.ImageOnCreate.builder().path("/img/test1.jpg").build()
                                ,AccommodationReq.ImageOnCreate.builder().imageType(ImageType.DETAIL).path("/img/test2.jpg").build()
                                ,AccommodationReq.ImageOnCreate.builder().imageType(ImageType.THUMBNAIL).path("/img/test3.jpg").build()
                        ))
                        .build()
        );
        final ResultActions resultActions = this.mockMvc.perform(
                post("/api/accommodation/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("지역번호로 숙소리스트 조회하기")
    void accommodationList_from_regionNo() throws Exception {
        final ResultActions resultActions = this.mockMvc.perform(
                get("/api/accommodation")
                        .param("regionId", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(status().isOk());
    }
}