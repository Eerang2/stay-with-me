package jwlee.staywithme.web.accommodation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jwlee.staywithme.BaseMockMvcTest;
import jwlee.staywithme.domain.model.AccAddress;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.service.AccommodationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AccommodationControllerTest extends BaseMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("숙소 등록 성공 테스트")
    void save() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                Accommodation.builder()
                        .name("신라호텔")
                        .description("5성급 최고급 호텔")
                        .address(new AccAddress("124532", "서울시 강동구", "동성로 125번길"))
                        .tel("010-1234-5678")
                        .build()
        );
        final ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accommodation/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );
    }
    @Test
    @DisplayName("숙소 등록 실패")
    void save_fail() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                Accommodation.builder()
                        .name("")
                        .description("5성급 최고급 호텔")
                        .address(new AccAddress("124532", "서울시 강동구", "동성로 125번길"))
                        .tel("010-1234-5678")
                        .build()
        );
        final ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accommodation/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }
}