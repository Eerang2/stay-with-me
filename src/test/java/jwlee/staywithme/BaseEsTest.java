package jwlee.staywithme;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BaseEsTest extends BaseRedisTest {

    static final String ES_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:8.10.0";
    static final GenericContainer<?> ES_CONTAINER;

    static {
        ES_CONTAINER = new GenericContainer<>(ES_IMAGE)
                .withEnv("discovery.type", "single-node")
                .withEnv("xpack.security.enabled", "false")
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(9200), new ExposedPort(9200)))
                        ))
                .withReuse(true);
        ES_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", () -> ES_CONTAINER.getHost() + ":" + ES_CONTAINER.getMappedPort(9200));
    }
}
