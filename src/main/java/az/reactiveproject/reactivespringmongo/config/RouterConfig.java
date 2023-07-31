package az.reactiveproject.reactivespringmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {
    static int requestCounter = 0;

    @Bean
    public RouterFunction<ServerResponse> routing (CustomHandler handler) {
        return RouterFunctions
                .route(GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getUser)
                .andRoute(POST("/post").and(accept(MediaType.APPLICATION_JSON)),handler::saveUser);
//                .filter(filterSomeRequests());

    }

//    private HandlerFilterFunction filterSomeRequests() {
//        return (request, next) -> {
//            if (requestCounter++ % 10 == 0) {
//                return ServerResponse.status(HttpStatusCode.valueOf(200)).build();
//            }
//            return next.handle(request);
//        };
//    }

}
