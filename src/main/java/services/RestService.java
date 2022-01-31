package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import utils.GlobalProperties;

public abstract class RestService {
    private static GlobalProperties properties = GlobalProperties.getInstance();
    public static final String BASE_URL = properties.get("baseUrl");

    protected Cookies cookies;
    protected static RequestSpecification REQ_SPEC;

    protected abstract String getBasePath();

    public RestService(Cookies cookies) {
        this.cookies = cookies;

        REQ_SPEC = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
    }

    public RestService() {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
    }
}
