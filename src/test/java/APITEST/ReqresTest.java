package APITEST;

import APITEST.colors.Colorsdata;
import APITEST.registration.Register;
import APITEST.registration.SuccessReg;
import APITEST.registration.UnSuccessReg;
import APITEST.spec.Specifications;
import APITEST.users.UserData;
import APITEST.users.UserTime;
import APITEST.users.UserTimeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresTest {


    //Основные компоненты RestAssured:
    //given() — используется для установки параметров запроса, таких как заголовки, параметры, тело запроса и т.д.
    //when() — определяет выполнение HTTP-запроса (GET, POST, PUT, DELETE и т.д.).
    //then() — используется для проверки ответа, включая статус-код, заголовки и тело ответа.

    //Matchers: RestAssured использует Hamcrest matchers для проверки значений в ответах. Например, equalTo(), containsString(), hasItem() и другие.


    //Пример GET запроса

    /*  public  class RestAssuredExample{
          public static void main(String[] args) {
              RestAssured.baseURI="https://jsonplaceholder.typicode.com";//Основное отличие в том, что baseURI фокусируется на базовом URL, а URL — на расположении текущего документа.
              given().
                      when().
                         get("/posts/1").
                      then().
                      statusCode(200).//// Проверка, что статус-код равен 200
                      body("userId", equalTo(1)).// Проверка, что userId равен 1
                      body("tittle",notNullValue());// Проверка, что title не null
          }
      }*/
    private final static String url = "https://reqres.in";

    @Test
    @DisplayName("Проверка id в аватаре")
    public void checkAvatarAndIdtest() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification20OK());
        List<UserData> users = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        // users.forEach(x->assertTrue(x.getAvatar().contains(x.getId().toString())));

        // assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> id = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        for (int j = 0; j < avatars.size(); j++) {
            assertTrue(avatars.get(j).contains(id.get(j)));
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegTest() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification20OK());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assertions.assertNotNull(successReg.getToken());
        Assertions.assertNotNull(successReg.getId());

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    @DisplayName("Не успешная регистрация без пароля")
    public void notRegisterTest() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification400b());

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);

        Assertions.assertEquals("Missing password", unSuccessReg.getError());

    }

    @Test
    @DisplayName("Сортировка дат по возрастанию")

    public void Sorteddate() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecification20OK());
        List<Colorsdata> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", Colorsdata.class);
        List<Integer> years = colors.stream().map(Colorsdata::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(sortedYears, years);

        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest() {
        Specifications.installSpetification(Specifications.requestSpecification(url), Specifications.responseSpecificationUniq(204));
        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();

    }

    @Test
    @DisplayName("Проверка времени")
    @Disabled
    public void timeTest(){
        Specifications.installSpetification(Specifications.requestSpecification(url),Specifications.responseSpecification20OK());
        UserTime userTime=new UserTime("morpheus","zion resident");
        UserTimeResponse response=given()
                .body(userTime)
                .when()
                .put("api/users/2")
                .then().log().all().extract().as(UserTimeResponse.class);

        String regex="(.{5})$";
        String currentTime= Clock.systemUTC().instant().toString().replaceAll(regex,"");
        System.out.println(currentTime);
        Assertions.assertEquals(currentTime,response.getUpdatedAt().replaceAll(regex,""));
        System.out.println(response.getUpdatedAt().replaceAll(regex,""));

    }
}
