package jwlee.staywithme.testContainers;

import jwlee.staywithme.BaseTest;
import jwlee.staywithme.domain.infra.RedisCrudService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;

public class RedisContainerTest extends BaseTest {

    @Autowired
    private RedisCrudService redisCrudService;

    static final String REDIS_IMAGE = "redis:latest";
    static final GenericContainer<?> REDIS_CONTAINER;
    static {
        REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
                .withExposedPorts(6379)
                .withReuse(true);
        REDIS_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> "" + REDIS_CONTAINER.getMappedPort(6379));
    }

    @Test
    @DisplayName("레디스 get, set 테스트")
    void redis_set_get() {
        redisCrudService.setKey("test_key", "test_value");
        String valueFromRedis = redisCrudService.getKey("test_key");
        Assertions.assertThat(valueFromRedis).isEqualTo("test_value");
    }
}
