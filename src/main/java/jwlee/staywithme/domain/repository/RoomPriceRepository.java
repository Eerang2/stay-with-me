package jwlee.staywithme.domain.repository;

import jwlee.staywithme.domain.repository.entity.RoomPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPriceEntity, Long> {
    List<RoomPriceEntity> findAllByRoomId(Long roomId);
}
