package jwlee.staywithme.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "room")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_no", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer minOccupancy;

    @Column(nullable = false)
    private Integer maxOccupancy;

    @Column(nullable = false)
    private String bedTypeDescription;

    @Column(nullable = false)
    private int bedCount;

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    @Column(nullable = false)
    private LocalDateTime checkOutTime;

    @Column(nullable = true, length = 255)
    private String promotionText;

    @Column(name = "acmd_no", nullable = false)
    private long accommodationId;
}
