package createuser;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String URL = "https://stellarburgers.nomoreparties.site";
    private static final String CREATE_USER = "/api/auth/register";
    private static final String LOGIN_USER = "/api/auth/login";
    private static final String DELETE_USER = "/api/auth/user";

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(URL + CREATE_USER);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(URL + LOGIN_USER);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(URL + DELETE_USER);
    }

}
