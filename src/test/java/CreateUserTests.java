import models.CreateUserBodyModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpecs.*;

@DisplayName("Тестирование POST API для создания пользователей")
public class CreateUserTests extends TestBase {
    @Test
    @DisplayName("Успешное создание пользователя")
    void createUserSuccessTest() {
        CreateUserBodyModel createUserBody = new CreateUserBodyModel();
        createUserBody.setName("Andrey");
        createUserBody.setJob("AQA");

        CreateUserResponseModel response =  step("Отправляем запрос", ()->
                given(createUserSuccessSpec)
                        .body(createUserBody)
                        .when()
                        .post("")
                        .then()
                        .spec(createUserSuccessResponseSpec)
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
                given(createUserErrorSpec)
                        .when()
                        .post("")
                        .then()
                        .spec(createUserErrorResponseSpec));
    }

}
