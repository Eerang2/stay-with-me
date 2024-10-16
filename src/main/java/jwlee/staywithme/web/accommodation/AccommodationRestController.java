package jwlee.staywithme.web.accommodation;

import jakarta.validation.Valid;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.service.AccommodationService;
import jwlee.staywithme.web.dto.AccommodationReq;
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
    public Accommodation getById(@PathVariable long id) {
        return accommodationService.findAccommodationById(id);
    }

    @PostMapping("/create")
    public Accommodation create(@RequestBody @Valid AccommodationReq.Create accommodationReqCreate) {
//        Accommodation accommodation = Accommodation.builder()
//                .name(accommodationReqCreate.getName())
//                .description(accommodationReqCreate.getDescription())
//                .type(accommodationReqCreate.getType())
//                .parkingInfo(new ParkingInfo(accommodationReqCreate.getParkingInfo().getIsFree(), accommodationReqCreate.getParkingInfo().getParkingType()))
//                .geoLocation(new GeoLocation(accommodationReqCreate.getGeoLocation().getLatitude(), accommodationReqCreate.getGeoLocation().getLongitude()))
//                .locationGuideText(accommodationReqCreate.getLocationGuideText())
//                .description(accommodationReqCreate.getDescription())
//                .build();
//        Accommodation createdAccommodation = accommodationService.create(accommodation);
        return null;
    }

    @PostMapping("/update/{id}")
    public Accommodation update(@PathVariable long id, @RequestBody @Valid Accommodation accommodationReq) {
        log.info("accommodation: {}", accommodationReq);
//        return accommodationService.update(accommodationReq, id);
        return null;
    }

}
