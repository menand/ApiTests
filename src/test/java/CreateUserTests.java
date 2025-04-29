import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("Тестирование POST API для создания пользователей")
public class CreateUserTests {
    @Test
    @DisplayName("Успешное создание пользователя")
    void createUserSuccessTest() {
        String bodyJSON = "{ \"name\": \"Andrey\", \"job\": \"AQA\" }";
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)// Добавляем нужный заголовок
                .body(bodyJSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Andrey"))
                .body("id", not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Ошибка при создании пользователя - не указан content-type")
    void createUserWithoutBodyUnSuccessTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(415);
    }

}
