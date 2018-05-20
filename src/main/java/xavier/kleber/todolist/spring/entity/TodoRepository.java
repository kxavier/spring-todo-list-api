package xavier.kleber.todolist.spring.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Kleber
 */
public interface TodoRepository extends MongoRepository<Todo,String>{
    
}
