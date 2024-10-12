package jwlee.staywithme.domain.repository;

import jwlee.staywithme.domain.repository.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    List<RoomEntity> findAllByAccommodationId(Long accommodationId);

}
