package az.reactiveproject.reactivespringmongo.config;


import az.reactiveproject.reactivespringmongo.entity.User;
import az.reactiveproject.reactivespringmongo.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Configuration
public class CustomHandler {
    private UserService service;

    public CustomHandler(UserService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        String path = request.pathVariable("id");
        Mono<ServerResponse> notfound = ServerResponse.notFound().build();
        int id;
        if (path != null) {
            try {
                String str = path.trim();
                if (str.equals("")) {
                    return notfound;
                } else {
                    return service.findByiId(Integer.parseInt(str))
                            .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(user)))
                            .switchIfEmpty(ServerResponse.notFound().build());
                }
            } catch (NumberFormatException formatException) {
                formatException.printStackTrace();
            }
        }
        return notfound;
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> service.saveUser(user))
                .doOnSuccess(s -> System.out.println(s))
                .doOnError(throwable -> throwable.printStackTrace())
                .flatMap(user -> ServerResponse.ok().build());

    }


}
