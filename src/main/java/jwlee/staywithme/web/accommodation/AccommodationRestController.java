package jwlee.staywithme.web.accommodation;

import jakarta.validation.Valid;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.model.AccommodationImage;
import jwlee.staywithme.domain.service.AccommodationService;
import jwlee.staywithme.web.dto.AccommodationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accommodation")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @GetMapping("/{id}")
    public Accommodation getById(@PathVariable long id) {
        return accommodationService.findAccommodationById(id);
    }

    @PostMapping("/create")
    public Accommodation createAccommodation(@RequestBody @Valid AccommodationReq.Create accommodationReqCreate) {
        List<AccommodationImage> imageList = accommodationReqCreate.getImageList().stream()
                .map(AccommodationReq.ImageOnCreate::toAccommodationImage)
                .toList();
        return accommodationService.create(accommodationReqCreate.toAccommodation(), imageList);
    }

    @PostMapping("/update/{id}")
    public Accommodation update(@PathVariable long id, @RequestBody @Valid Accommodation accommodationReq) {
        log.info("accommodation: {}", accommodationReq);
//        return accommodationService.update(accommodationReq, id);
        return null;
    }

}
