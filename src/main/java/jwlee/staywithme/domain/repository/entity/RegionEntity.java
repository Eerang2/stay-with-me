package jwlee.staywithme.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "region")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_no", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(nullable = false)
    private String name;        // 지역 이름 (ex : 부산, 서울, 뉴욕 )

    @Column(nullable = false)
    private String country;     // 나라 구분 (ex : KOR, USA, JPN )

}
