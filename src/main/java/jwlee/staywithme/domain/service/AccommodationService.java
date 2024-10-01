package jwlee.staywithme.domain.service;

import jwlee.staywithme.domain.exceptions.NoSuchAccommodation;
import jwlee.staywithme.domain.model.Accommodation;
import jwlee.staywithme.domain.repository.AccommodationRepository;
import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public Accommodation getAccommodationById(long id) {
        AccommodationEntity accommodationEntity = accommodationRepository.findAccommodationEntitiesById(id).orElseThrow(() -> new NoSuchAccommodation());
        return Accommodation.from(accommodationEntity);
    }

    public Accommodation create(Accommodation accommodation) {
        AccommodationEntity accommodationEntity = accommodation.toEntity();
        AccommodationEntity createdAccommodationEntity = accommodationRepository.save(accommodationEntity);
        Accommodation createdAccommodation = Accommodation.from(createdAccommodationEntity);
        return createdAccommodation;
    }
}
