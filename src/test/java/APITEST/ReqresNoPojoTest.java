package APITEST;

import APITEST.spec.Specifications;
import dev.failsafe.internal.util.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresNoPojoTest {
    private final static String url = "https://reqres.in";

    @Test
    public void chekAvatars() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification20OK());
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
        Assertions.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));
    }

    @Test
    public void successRegNoPojo() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification20OK());
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assertions.assertEquals(4, id);
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", token);
    }

    @Test
    @DisplayName("Проверка неуспешной регистрации без POJO")
    public void unseccessUserRegNoPojo() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification400b());
        Map<String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");
        user.put("password", null);
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assertions.assertEquals("Missing password", error);
    }
}
