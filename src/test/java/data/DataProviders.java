package data;

import models.user.register.PostRegisterRequest;
import org.testng.annotations.DataProvider;

import static data.TestData.EMAIL;
import static data.TestData.FIELD_MUST_NOT_BE_NULL;
import static data.TestData.PASSWORD;
import static data.TestData.REQUEST_BODY_MUST_NOT_BE_EMPTY;
import static data.TestData.USERNAME;
import static utils.CommonHelpers.generateRandomString;

public class DataProviders {

    @DataProvider(name = "invalidRegisterRequest")
    public static Object[][] invalidRegisterRequest() {
        return new Object[][]{
                {PostRegisterRequest.builder()
                        .userName(generateRandomString())
                        .password(generateRandomString())
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, USERNAME)},
                {PostRegisterRequest.builder()
                        .userName(generateRandomString())
                        .email(generateRandomString())
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, PASSWORD)},
                {PostRegisterRequest.builder()
                        .password(generateRandomString())
                        .email(generateRandomString())
                        .build(), String.format(FIELD_MUST_NOT_BE_NULL, EMAIL)},
                {PostRegisterRequest.builder()
                        .build(), String.format(REQUEST_BODY_MUST_NOT_BE_EMPTY)
                }
        };
    }
}
