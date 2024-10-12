package jwlee.staywithme.domain.service;

import jakarta.transaction.Transactional;
import jwlee.staywithme.domain.model.Room;
import jwlee.staywithme.domain.repository.RoomRepository;
import jwlee.staywithme.domain.repository.entity.RoomEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findRoomByAccommodationId(long accommodationId) {
        List<RoomEntity> roomEntities = roomRepository.findAllByAccommodationId(accommodationId);
        List<Room> roomList = roomEntities.stream().map(Room::from).toList();
        //    List<Room> roomList = new ArrayList<>();  // 리스트를 초기화
        //    for (RoomEntity entity : roomEntities) {  // roomEntities 리스트를 하나씩 순차적으로 반복
        //    Room room = Room.from(entity);  // 각 RoomEntity 객체를 Room 객체로 변환
        //    roomList.add(room);  // 변환된 Room 객체를 roomList에 추가
        return roomList;
    }

}

