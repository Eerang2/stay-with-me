package jwlee.staywithme.domain.repository;

import jwlee.staywithme.BaseJpaTest;
import jwlee.staywithme.domain.repository.entity.RoomPriceEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomPriceRepositoryTest extends BaseJpaTest {

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Test
    @DisplayName("방 가격 조회 테스트")
    void get_roomPrice() {
        List<RoomPriceEntity> roomPriceEntities = roomPriceRepository.findAllByRoomId(1L);
        Assertions.assertThat(roomPriceEntities.size()).isGreaterThan(0);
    }
}
