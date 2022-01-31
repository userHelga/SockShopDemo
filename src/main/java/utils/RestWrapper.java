package utils;

import io.restassured.http.Cookies;
import services.CardService;
import services.RegisterService;

import static services.LoginService.loginAs;

public class RestWrapper {

    private Cookies cookies;

    public RegisterService registerService;
    public CardService cardService;

    private RestWrapper(Cookies cookies) {
        this.cookies = cookies;
        this.registerService = new RegisterService();
        this.cardService = new CardService(cookies);
    }

    public static RestWrapper login(String login, String password) {
        Cookies cookies = loginAs(login, password);

        return new RestWrapper(cookies);
    }
}
