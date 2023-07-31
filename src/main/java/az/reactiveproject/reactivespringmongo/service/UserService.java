package az.reactiveproject.reactivespringmongo.service;

import az.reactiveproject.reactivespringmongo.entity.User;
import az.reactiveproject.reactivespringmongo.repository.UserRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Flux<User> getAllUsers() {
        return repository.findAll();
    }

    public Mono<User> findById(User user) {
        return repository.findById(user.getId());
    }
    public Mono<User> findByiId(int id) {
        return repository.findById(id);
    }
    public Mono<User> saveUser(User user){
       return repository.save(user);
    }

}
