package xavier.kleber.todolist.spring;

import com.airhacks.cors.CorsResponseFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import xavier.kleber.todolist.spring.boundary.TodosResource;

/**
 *
 * @author Kleber
 */
@Configuration
@ApplicationPath("/api")
public class JAXRSConfig extends ResourceConfig {

    public JAXRSConfig() {
        register(TodosResource.class);
        register(CorsResponseFilter.class);
    }

}
