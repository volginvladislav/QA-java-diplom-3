package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserSteps extends RestSteps{

    public ValidatableResponse createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .assertThat()
                .body("accessToken", notNullValue());
    }

    public ValidatableResponse authorizationUser(String email, String password) {
        User user = new User(email, password);
        return given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .post("/api/auth/login")
                .then()
                .assertThat()
                .body("accessToken", notNullValue());
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .spec(getDefaultRequestSpec())
                .delete("api/auth/user")
                .then();
    }
}
