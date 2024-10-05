package jwlee.staywithme.web.accommodation;

import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseMockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class ImageUploadRestControllerTest extends BaseMockMvcTest {

    private static final String TEMP_PATH = "tmpFile/accommodation/MAIN";

    @Test
    @DisplayName("컨트롤러 이미지 업로드")
    void imageUploadFromController() throws Exception {
        MockMultipartFile file = new MockMultipartFile("image", "image-test.jpg", MediaType.IMAGE_JPEG_VALUE, "Test Image content".getBytes());

        mockMvc.perform(
                        multipart("/api/upload/image/accommodation")
                                .file(file)
                                .param("imageType", "MAIN")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isOk());
    }
}