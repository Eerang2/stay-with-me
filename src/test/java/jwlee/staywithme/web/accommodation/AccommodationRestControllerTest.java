package jwlee.staywithme.web.accommodation;

import jwlee.staywithme.BaseMockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccommodationRestControllerTest extends BaseMockMvcTest {
    @Test
    void 숙소조회API테스트() throws Exception {
        final ResultActions actions = mockMvc.perform(get("/api/accommodation/1").contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울 게스트하우스"))

        ;
    }
}