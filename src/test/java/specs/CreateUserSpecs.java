package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class CreateUserSpecs {
    public static RequestSpecification createUserSuccessSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .contentType(ContentType.JSON)
            .log().all()
            .basePath("/api/users/");

    public static RequestSpecification createUserErrorSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .log().all()
            .basePath("/api/users/");

    public static ResponseSpecification createUserSuccessResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification createUserErrorResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(415)
            .log(LogDetail.ALL)
            .build();
}
