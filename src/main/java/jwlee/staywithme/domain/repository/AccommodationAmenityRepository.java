package jwlee.staywithme.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import jwlee.staywithme.domain.repository.entity.AccommodationAmenityEntity;
import jwlee.staywithme.domain.repository.entity.AmenityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationAmenityRepository extends CrudRepository<AccommodationAmenityEntity, Long>  {

    @Query("SELECT a.amenity FROM AccommodationAmenityEntity a WHERE a.accommodation.id = :accommodationId")
    List<AmenityEntity> findAmenitiesByAccommodationId(@Param("accommodationId") Long accommodationId);
}
