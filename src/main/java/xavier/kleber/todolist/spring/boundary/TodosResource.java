package xavier.kleber.todolist.spring.boundary;

import java.util.List;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xavier.kleber.todolist.spring.entity.Todo;
import xavier.kleber.todolist.spring.entity.TodoRepository;

/**
 *
 * @author Kleber
 */
@Component
@Path("/todos")
public class TodosResource {

    @Autowired
    TodoRepository repository;

    @GET
    public JsonArray getTodos() {
        List<Todo> todos = repository.findAll();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        todos.forEach(todo -> arrayBuilder.add(todo.toJson()));
        return arrayBuilder.build();
    }

    @POST
    public Response createTodo(@FormParam("name") String name) {
        Todo todo = new Todo();
        todo.setName(name);
        Todo newTodo = repository.save(todo);
        return Response.status(201).entity(newTodo.toJson()).build();
    }

    @PUT
    @Path("/{todoId}")
    public Response updateTodo(@PathParam("todoId") String todoId, @FormParam("completed") boolean completed) {
        Optional<Todo> optionalTodo = repository.findById(todoId);
        return optionalTodo
                .map(todo -> updateStatus(todo, completed))
                .orElse(Response.status(404))
                .build();
    }

    @GET
    @Path("/{todoId}")
    public Response getTodoById(@PathParam("todoId") String todoId) {
        Optional<Todo> todo = repository.findById(todoId);
        return todo.map(t -> Response.ok(t.toJson()))
                .orElse(Response.status(404))
                .build();
    }

    @DELETE
    @Path("/{todoId}")
    public Response deleteTodo(@PathParam("todoId") String todoId) {
        repository.deleteById(todoId);
        return Response.ok().build();
    }
    
    private ResponseBuilder updateStatus(Todo todo, boolean completed) {
            todo.setCompleted(completed);
            Todo updatedTodo = repository.save(todo);
            return Response.ok(updatedTodo.toJson());    
    }

}
