package createuser;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String url = "https://stellarburgers.nomoreparties.site";
    private static final String hand_create_user = "/api/auth/register";
    private static final String hand_login_user = "/api/auth/login";
    private static final String hand_delete_user = "/api/auth/user";

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(url + hand_create_user);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(url + hand_login_user);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(url + hand_delete_user);
    }

}
