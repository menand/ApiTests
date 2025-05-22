package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class LoginSpecs {
    public static RequestSpecification loginRequestSuccessSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .log().all()
            .basePath("/api/users/");

    public static RequestSpecification loginRequestErrorSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .basePath("/api/users/");

    public static ResponseSpecification loginResponseSuccessSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification loginResponseErrorSpec = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .log(LogDetail.ALL)
            .build();
}
