package jwlee.staywithme.web.accommodation;

import jwlee.staywithme.domain.service.AccommodationService;
import jwlee.staywithme.domain.model.Accommodation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @GetMapping("/accommodation/{id}")
    public Accommodation getAccommodation(@PathVariable long id) {
        return accommodationService.getAccommodationById(id);
    }
}
