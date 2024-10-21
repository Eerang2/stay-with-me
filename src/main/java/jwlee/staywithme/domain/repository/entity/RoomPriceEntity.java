package jwlee.staywithme.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ROOM_PRICE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_price_no", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_no", nullable = false)
    private RoomEntity room;        // 어느 방인지

    @Column(nullable = false)
    private LocalDateTime priceDate;        // 가격이 적용되는 날짜

    private BigDecimal price;               // 해달 날짜의 가격

    @Column
    private BigDecimal discountPrice;       // 할인가

    @Column
    private boolean isPromotional;      // 추가 프로모션

}
