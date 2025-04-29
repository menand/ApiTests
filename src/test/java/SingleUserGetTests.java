import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;


@DisplayName("Тестирование GET API для получения инфы о пользователе")
public class SingleUserGetTests {

    @Test
    @DisplayName("Успешное получение данных о пользователи с id=2")
    void getUser2InfoSuccess() {
        get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.avatar", endsWith("2-image.jpg"));
    }

    @Test
    @DisplayName("Ошибка при получение данных о пользователи с id=5 без секретного ключа")
    void getUser5InfoErrorUnauthorized() {
        given()
                .header("Connection", "close")  // Добавляем нужный заголовок
                .when()
                .get("https://reqres.in/api/users/6")
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Успешное получение данных о пользователи с id=5 с секретным ключём")
    void getUser5InfoSuccess() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .header("Connection", "close")// Добавляем нужный заголовок
                .when()
                .get("https://reqres.in/api/users/5")
                .then()
                .statusCode(200)
                .body("data.first_name", is("Charles"))
                .body("data.avatar", endsWith("5-image.jpg"));
    }
}
