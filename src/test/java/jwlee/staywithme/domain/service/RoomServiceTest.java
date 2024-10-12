package jwlee.staywithme.domain.service;

import jakarta.transaction.Transactional;
import jwlee.staywithme.BaseTest;
import jwlee.staywithme.domain.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Transactional
public class RoomServiceTest extends BaseTest {

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("방 조회")
    void getRoom() {
        List<Room> roomList = roomService.findRoomByAccommodationId(1L);
        Assertions.assertThat(roomList.size()).isGreaterThan(0);
    }


}
