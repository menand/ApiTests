package models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserBodyModel {
    private String name;
    private String job;
}
