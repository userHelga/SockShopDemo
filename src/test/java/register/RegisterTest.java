package register;

import data.DataProviders;
import io.restassured.response.Response;
import models.user.register.PostRegisterRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.RegisterService;

import static org.assertj.core.api.Assertions.assertThat;

import static data.TestData.USER_ALREADY_EXISTS;
import static utils.StatusCodes.BAD_REQUEST;

import static utils.CommonGenerator.generateSimpleUserRequest;

public class RegisterTest {

    RegisterService registerService;

    @BeforeClass
    public void setup() {
        registerService = new RegisterService();
    }

    @Test
    public void shouldRegisterUserWithAllValidFields() {
        PostRegisterRequest request = generateSimpleUserRequest();
        assertThat(registerService.registerUserAndReturnId(request)).isNotNull();
    }

    @Test
    public void shouldNotRegisterAlreadyRegisteredUser() {
        PostRegisterRequest request = generateSimpleUserRequest();
        registerService.registerUserAndReturnId(request);

        Response response = registerService.registerUserAndReturnResponse(request);

        assertThat(response.statusLine()).contains(BAD_REQUEST.getName());
        assertThat(response.getBody().asString()).contains(USER_ALREADY_EXISTS);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "invalidRegisterRequest")
    public void shouldNotRegisterUserWithoutRequiredFields(PostRegisterRequest request, String errorMessage) {
        Response response = registerService.registerUserAndReturnResponse(request);

        assertThat(response.statusLine()).contains(BAD_REQUEST.getName());
        assertThat(response.getBody().asString()).contains(errorMessage);
    }
}