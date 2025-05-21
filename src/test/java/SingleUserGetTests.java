import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Тестирование GET API для получения инфы о пользователе")
public class SingleUserGetTests  extends TestBase{

    @Test
    @DisplayName("Успешное получение данных о пользователе с id=2")
    void getUser2InfoSuccessTest() {
        get("/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.avatar", endsWith("2-image.jpg"));
    }

    @Test
    @DisplayName("Ошибка при получение данных о пользователи с id=5 без секретного ключа")
    void getUser5InfoErrorUnauthorizedTest() {
        given()
                .header("Connection", "close")  // Добавляем нужный заголовок
                .when()
                .get("/users/6")
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Успешное получение данных о пользователи с id=5 с секретным ключём")
    void getUser5InfoSuccessTest() {
        LoginResponseModel response =  step("Отправляем запрос", ()->
             given()
                    .filter(withCustomTemplates())
                    .header("x-api-key", "reqres-free-v1")
                    .log().uri()
                    .log().body()
                    .log().headers()
                    .when()
                    .get("/users/5")
                    .then()
                    .log().status()
                    .log().headers()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseModel.class));

        step("Проверяем имя", ()->
            assertEquals("Charles", response.getData().getFirst_name()));

        step("Проверяем аватар", ()->
            assertEquals("https://reqres.in/img/faces/5-image.jpg",response.getData().getAvatar()));

    }
}
