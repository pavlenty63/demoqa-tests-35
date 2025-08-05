package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class LoginSpec {
  public static RequestSpecification loginRequestSpec = with()
          .filter(withCustomTemplates())
          .log().body()
          .log().headers()
          .contentType(JSON)
          .basePath("/api/login")
          .header("x-api-key", "reqres-free-v1");

  public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(200)
          .log(STATUS)
          .log(BODY)
          .build();
}
