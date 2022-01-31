package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonHelpers {

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateRandomString(int stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength);
    }

    public static String generateEmail(String str){
        return str.concat("@test.com");
    }
}
