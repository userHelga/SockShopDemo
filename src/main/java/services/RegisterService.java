package services;

import io.restassured.response.Response;
import models.user.register.PostRegisterRequest;
import models.user.register.PostRegisterResponse;

import static io.restassured.RestAssured.given;
import static utils.StatusCodes.OK;

public class RegisterService extends RestService {

    private static final String REGISTER_BASE_PATH = "/register";

    @Override
    protected String getBasePath() {
        return REGISTER_BASE_PATH;
    }

    public RegisterService() {
        super();
    }

    public PostRegisterResponse registerUser(PostRegisterRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(REGISTER_BASE_PATH)
                .body(requestBody)
                .when().post()
                .then()
                .statusCode(OK.getCode()).extract().as(PostRegisterResponse.class);
    }

    public String registerUserAndReturnId(PostRegisterRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(REGISTER_BASE_PATH)
                .body(requestBody)
                .when().post()
                .then()
                .statusCode(OK.getCode()).extract().jsonPath().get("id")
                .toString();
    }

    public Response registerUserAndReturnResponse(PostRegisterRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(REGISTER_BASE_PATH)
                .body(requestBody)
                .when().post();
    }
}
