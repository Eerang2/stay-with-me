package jwlee.staywithme.web.accommodation;


import jwlee.staywithme.domain.enums.ImageType;
import jwlee.staywithme.domain.repository.entity.ImageEntity;
import jwlee.staywithme.domain.service.UploadService;
import jwlee.staywithme.web.common.BaseApiController;
import jwlee.staywithme.web.dto.AccommodationImageRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageUploadRestController extends BaseApiController {

    private final UploadService uploadService;

    @PostMapping("/upload/image/accommodation")
    public AccommodationImageRes.Upload uploadForAccommodation(@RequestParam("imageType") ImageType imageType,
                                                               @RequestParam("image") MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }
        ImageEntity imageEntity = uploadService.uploadAccommodationImage(imageType, imageFile);

        return AccommodationImageRes.Upload.from(imageEntity);
    }

}
