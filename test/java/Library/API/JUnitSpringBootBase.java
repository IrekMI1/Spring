package Library.API;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JUnitSpringBootBase.TestControllerConfiguration.class)
@AutoConfigureWebTestClient
public abstract class JUnitSpringBootBase {
    @TestConfiguration
    static class TestControllerConfiguration {
    }
}
