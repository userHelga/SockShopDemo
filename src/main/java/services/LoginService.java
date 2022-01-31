package services;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import static io.restassured.RestAssured.given;
import static services.RestService.BASE_URL;

public class LoginService {

    private static final String LOGIN_BASE_PATH = "/login";

    public static Cookies loginAs(String login, String password) {
        return given()
                .auth()
                .preemptive()
                .basic(login, password)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath(LOGIN_BASE_PATH)
                .when().get()
                .getDetailedCookies();
    }
}