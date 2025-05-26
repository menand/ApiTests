import models.CreateUserBodyModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static specs.Specs.*;

@DisplayName("Тестирование POST API для создания пользователей")
public class CreateUserTests extends TestBase {
    @Test
    @DisplayName("Успешное создание пользователя")
    void createUserSuccessTest() {
        CreateUserBodyModel createUserBody = new CreateUserBodyModel("Andrey","AQA");

        CreateUserResponseModel response =  step("Отправляем запрос", ()->
                given(requestSpecSuccess)
                        .body(createUserBody)
                        .when()
                        .post("")
                        .then()
                        .spec(responseWithStatus(201))
                        .extract().as(CreateUserResponseModel.class));

        step("Проверяем имя", ()->
                assertEquals(createUserBody.getName(), response.getName()));

        step("Проверяем роль", ()->
            assertEquals(createUserBody.getJob(), response.getJob()));

    }

    @Test
    @DisplayName("Ошибка при создании пользователя - не указан content-type")
    void createUserWithoutBodyUnSuccessTest() {
        step("Отправляем запрос, проверяем что в ответе 415 ошибка", ()->
                given(requestSpecNoContentType)
                        .when()
                        .post("")
                        .then()
                        .spec(responseWithStatus(415)));
    }

}
