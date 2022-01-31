package services;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.user.cards.PostCardsRequest;
import models.user.cards.PostCardsResponse;

import static io.restassured.RestAssured.given;
import static utils.StatusCodes.OK;

public class CardService extends RestService {

    private static final String CARD_BASE_PATH = "/cards";
    private static final String CARD_ID = "/{id}";

    @Override
    protected String getBasePath() {
        return CARD_BASE_PATH;
    }

    public CardService(Cookies cookies) {
        super(cookies);
    }

    public PostCardsResponse createCard(PostCardsRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(CARD_BASE_PATH)
                .body(requestBody)
                .when().post()
                .then()
                .statusCode(OK.getCode()).extract().as(PostCardsResponse.class);
    }

    public String createCardAndReturnId(PostCardsRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(CARD_BASE_PATH)
                .body(requestBody)
                .when().post()
                .then()
                .statusCode(OK.getCode()).extract().jsonPath().get("id")
                .toString();
    }

    public Response createCardAndReturnResponse(PostCardsRequest requestBody) {
        return given().spec(REQ_SPEC)
                .basePath(CARD_BASE_PATH)
                .body(requestBody)
                .when().post();
    }

    public boolean deleteCard(String cardId) {
        String strResponse = given().spec(REQ_SPEC)
                .basePath(CARD_BASE_PATH.concat(CARD_ID))
                .pathParam("id", cardId)
                .when().delete()
                .then()
                .statusCode(OK.getCode())
                .extract().asString();

        return Boolean.parseBoolean(strResponse.split(":")[1].substring(0, 4)); // try to get value :)
    }

    public Response deleteCardAndReturnResponse(String cardId) {
        return given().spec(REQ_SPEC)
                .basePath(CARD_BASE_PATH.concat(CARD_ID))
                .pathParam("id", cardId)
                .when().delete();
    }
}
