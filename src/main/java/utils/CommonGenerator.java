package utils;

import models.user.cards.PostCardsRequest;
import models.user.register.PostRegisterRequest;

import static utils.CommonHelpers.generateEmail;
import static utils.CommonHelpers.generateRandomString;

public class CommonGenerator {

    public static PostRegisterRequest generateSimpleUserRequest() {
        String name = generateRandomString();
        return PostRegisterRequest.builder()
                .userName(name)
                .password(generateRandomString())
                .email(generateEmail(name))
                .build();
    }

    public static PostCardsRequest generateSimpleCardRequest(String userId) {
        return PostCardsRequest.builder()
                .longNum(generateRandomString())
                .expires(generateRandomString())
                .ccv(generateRandomString(3))
                .userId(userId)
                .build();
    }
}
