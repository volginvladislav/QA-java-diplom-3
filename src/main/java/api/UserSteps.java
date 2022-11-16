package api;

import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserSteps extends RestSteps{

    public ValidatableResponse createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .when()
                .post(CREATE_USER)
                .then()
                .assertThat()
                .body("accessToken", notNullValue());
    }

    public ValidatableResponse authorizationUser(String email, String password) {
        User user = new User(email, password);
        return given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .post(LOGIN_USER)
                .then()
                .assertThat()
                .body("accessToken", notNullValue());
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .spec(getDefaultRequestSpec())
                .delete(DELETE_USER)
                .then();
    }
    public Response authorizationUserWithInvalidPassword(String email, String password) {
        User user = new User(email, password);
        return given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .post(LOGIN_USER);
    }
}
