package card;

import io.restassured.response.Response;
import models.user.cards.PostCardsRequest;
import models.user.register.PostRegisterRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.RegisterService;
import utils.RestWrapper;

import static data.TestData.CARD_ALREADY_EXISTS;
import static data.TestData.CARD_NOT_FOUND;
import static data.TestData.CCV;
import static data.TestData.EXPIRES;
import static data.TestData.FIELD_MUST_NOT_BE_NULL;
import static data.TestData.LONG_NUM;
import static data.TestData.REQUEST_BODY_MUST_NOT_BE_EMPTY;
import static data.TestData.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.CommonGenerator.generateSimpleCardRequest;
import static utils.CommonGenerator.generateSimpleUserRequest;
import static utils.CommonHelpers.generateRandomString;
import static utils.StatusCodes.BAD_REQUEST;
import static utils.StatusCodes.NOT_FOUND;

public class CardTest {

    private static RestWrapper api;
    private PostRegisterRequest registerRequest;
    private String userId;

    @BeforeClass
    public void userSetup() {
        registerRequest = generateSimpleUserRequest();
        registerUser(registerRequest);
        api = RestWrapper.login(registerRequest.getUserName(), registerRequest.getPassword());
    }

    @DataProvider(name = "invalidCreateCardRequest")
    private Object[][] invalidCreateCardRequest() {
        return new Object[][]{
                {PostCardsRequest.builder()
                        .longNum(generateRandomString())
                        .expires(generateRandomString())
                        .ccv(generateRandomString(3))
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, USER_ID)},
                {PostCardsRequest.builder()
                        .longNum(generateRandomString())
                        .expires(generateRandomString())
                        .userId(userId)
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, CCV)},
                {PostCardsRequest.builder()
                        .longNum(generateRandomString())
                        .ccv(generateRandomString(3))
                        .userId(userId)
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, EXPIRES)},
                {PostCardsRequest.builder()
                        .expires(generateRandomString())
                        .ccv(generateRandomString(3))
                        .userId(userId)
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, LONG_NUM)},
                {PostCardsRequest.builder()
                        .build(), String.format(REQUEST_BODY_MUST_NOT_BE_EMPTY)
                }
        };
    }

    @Test
    public void shouldCreateNewCard() {
        String cardId = api.cardService.createCardAndReturnId(generateSimpleCardRequest(userId));
        assertThat(cardId).isNotNull();
    }

    @Test
    public void shouldNotCreateAlreadyExistedCard() {
        PostCardsRequest request = generateSimpleCardRequest(userId);
        api.cardService.createCardAndReturnId(request);

        Response response = api.cardService.createCardAndReturnResponse(request);
        assertThat(response.statusLine()).contains(BAD_REQUEST.getName());
        assertThat(response.getBody().asString()).contains(CARD_ALREADY_EXISTS);
    }

    @Test(dataProvider = "invalidCreateCardRequest")
    public void shouldNotCreateNewCardWithoutRequiredFields(PostCardsRequest request, String errorMessage) {
        Response response = api.cardService.createCardAndReturnResponse(request);

        assertThat(response.statusLine()).contains(BAD_REQUEST.getName());
        assertThat(response.getBody().asString()).contains(errorMessage);
    }

    @Test
    public void shouldDeleteCardById() {
        String cardId = api.cardService.createCardAndReturnId(generateSimpleCardRequest(userId));

        boolean statusResponse = api.cardService.deleteCard(cardId);
        assertThat(statusResponse).isTrue();
    }

    @Test
    public void shouldNonDeleteNonExistedCard() {
        String nonExistedCardId = generateRandomString();
        Response response = api.cardService.deleteCardAndReturnResponse(nonExistedCardId);

        assertThat(response.statusLine()).contains(NOT_FOUND.getName());
        assertThat(response.getBody().asString()).contains(String.format(CARD_NOT_FOUND, nonExistedCardId));
    }

    private void registerUser(PostRegisterRequest request) {
        RegisterService registerService = new RegisterService();
        userId = registerService.registerUserAndReturnId(request);
    }
}
