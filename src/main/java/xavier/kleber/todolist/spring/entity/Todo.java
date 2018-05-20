package xavier.kleber.todolist.spring.entity;

import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Kleber
 */
//Maps the collection name in MongoDB
@Document(collection = "todos")
public class Todo {

    //Maps the _id field in the mongo document
    @Id
    private String id;
    private String name;
    private boolean completed;
    // Maps the field name used in the mongo document
    @Field("created_date")
    private Date createdDate = new Date();

    public Todo() {}
    
    public Todo(JsonObject json) {
        this.id = json.getString("id");
        this.name = json.getString("name", "");
        this.completed = json.getBoolean("completed", false);
    }
    
    public JsonObject toJson() {
            return Json.createObjectBuilder()
                    .add("_id", id)
                    .add("name",name)
                    .add("completed",completed)
                    .add("created_date",createdDate.toString())
                    .build();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", name=" + name + ", completed=" + completed + ", createdDate=" + createdDate + '}';
    }
    
    

}
