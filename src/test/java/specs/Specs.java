package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {
    public static RequestSpecification requestSpecSuccess = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .contentType(ContentType.JSON)
            .log().all()
            .basePath("/api/users/");

    public static RequestSpecification requestSpecNoContentType = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .log().all()
            .basePath("/api/users/");

    public static RequestSpecification requestSpecNoAPIKey = with()
            .filter(withCustomTemplates())
            .log().all()
            .basePath("/api/users/");

    public static ResponseSpecification responseWithStatus(int statusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(statusCode)
                .build();
    }
}
