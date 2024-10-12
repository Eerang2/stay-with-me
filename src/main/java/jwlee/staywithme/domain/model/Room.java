package jwlee.staywithme.domain.model;

import jwlee.staywithme.domain.repository.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private Long id;
    private String roomName;
    private String description;
    private Integer minOccupancy;
    private Integer maxOccupancy;
    private String bedTypeDescription;
    private int bedCount;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String promotionText;

    public static Room from(RoomEntity entity) {
        return Room.builder()
                .id(entity.getId())
                .roomName(entity.getRoomName())
                .description(entity.getDescription())
                .minOccupancy(entity.getMinOccupancy())
                .maxOccupancy(entity.getMaxOccupancy())
                .bedCount(entity.getBedCount())
                .checkInTime(entity.getCheckInTime())
                .checkOutTime(entity.getCheckOutTime())
                .promotionText(entity.getPromotionText())
                .build();
    }
}
