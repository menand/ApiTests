import models.LoginResponseErrorModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.*;


@DisplayName("Тестирование GET API для получения инфы о пользователе")
public class SingleUserGetTests  extends TestBase{

    @Test
    @DisplayName("Ошибка при получении инфы о юзере с id=4 без ключя")
    void getUser5InfoErrorTest() {
        LoginResponseErrorModel response =  step("Отправляем запрос", ()->
                given(loginRequestErrorSpec)
                        .when()
                        .get("4")
                        .then()
                            .spec(loginResponseErrorSpec)
                            .extract().as(LoginResponseErrorModel.class));

        step("Проверяем имя", ()->
                assertEquals("Missing API key.", response.getError()));
   }

    @Test
    @DisplayName("Ошибка при получении инфы о юзере с id=9999 (его нет)")
    void getUser5InfoError404Test() {
        step("Отправляем запрос", ()->
                given(loginRequestSuccessSpec)
                        .when()
                        .get("9999")
                        .then()
                        .statusCode(404));
    }

    @Test
    @DisplayName("Успешное получение данных о пользователи с id=5 с секретным ключём")
    void getUser5InfoSuccessTest() {
        LoginResponseModel response =  step("Отправляем запрос", ()->
             given(loginRequestSuccessSpec)
                    .when()
                    .get("5")
                    .then()
                     .spec(loginResponseSuccessSpec)
                     .extract().as(LoginResponseModel.class));

        step("Проверяем имя", ()->
            assertEquals("Charles", response.getData().getFirst_name()));

        step("Проверяем аватар", ()->
            assertEquals("https://reqres.in/img/faces/5-image.jpg",response.getData().getAvatar()));
    }
}
