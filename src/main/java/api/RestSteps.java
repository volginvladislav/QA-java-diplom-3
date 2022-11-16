package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestSteps {

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_USER = "api/auth/register";
    public static final String LOGIN_USER = "api/auth/login";
    public static final String DELETE_USER = "api/auth/user";

    public RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
}
