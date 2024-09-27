package jwlee.staywithme.web.accommodation;

import jakarta.validation.Valid;
import jwlee.staywithme.domain.service.AccommodationService;
import jwlee.staywithme.domain.model.Accommodation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accommodation")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @GetMapping("/{id}")
    public Accommodation getAccommodation(@PathVariable long id) {
        return accommodationService.getAccommodationById(id);
    }


    @PostMapping("/save")
    public Accommodation save(@RequestBody @Valid Accommodation accommodationReq) {
        log.info("accommodation: {}", accommodationReq);
        accommodationService.saveAccommodation(accommodationReq);

        return null;
    }
}
