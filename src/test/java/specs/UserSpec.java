package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class UserSpec {

  public static RequestSpecification userRequestWithIdSpec = with()
          .filter(withCustomTemplates())
          .log().body()
          .log().headers()
          .contentType(JSON)
          .basePath("/api/users/{id}")
          .header("x-api-key", "reqres-free-v1");

  public static ResponseSpecification userResponseWithContentSpec = new ResponseSpecBuilder()
          .expectStatusCode(200)
          .log(STATUS)
          .log(BODY)
          .build();

  public static ResponseSpecification userResponseNoContentSpec = new ResponseSpecBuilder()
          .expectStatusCode(204)
          .log(STATUS)
          .log(BODY)
          .build();
}
