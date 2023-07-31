package az.reactiveproject.reactivespringmongo.repository;

import az.reactiveproject.reactivespringmongo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,Integer> {

}
