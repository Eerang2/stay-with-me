package jwlee.staywithme.domain.repository;

import jwlee.staywithme.domain.repository.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
    Optional<AccommodationEntity> findAccommodationEntitiesById(long id);
}
