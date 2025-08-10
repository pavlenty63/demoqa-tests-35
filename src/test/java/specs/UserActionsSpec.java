package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class UserActionsSpec {

  public static RequestSpecification requestSpec = with()
          .filter(withCustomTemplates())
          .log().all()
          .contentType(JSON)
          .header("x-api-key", "reqres-free-v1");

  public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
          .expectStatusCode(200)
          .log(ALL)
          .build();

  public static ResponseSpecification responseSpec204 = new ResponseSpecBuilder()
          .expectStatusCode(204)
          .log(ALL)
          .build();
}
