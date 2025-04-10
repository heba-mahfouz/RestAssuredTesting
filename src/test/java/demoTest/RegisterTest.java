package demoTest;

import Pojo.LoginResponse;
import Pojo.RegisterRequest;
import Pojo.RegistrationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EcommerceTest extends BaseTest {
    
    @Test
    public void testUserRegistration() throws JsonProcessingException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("heba mahfouz");
        registerRequest.setEmail("hebamahfouz183@gmail.com");
        registerRequest.setPassword("152531997");
        registerRequest.setTitle("Mrs");
        registerRequest.setFirstname("heba");
        registerRequest.setLastname("mahfouz");
        registerRequest.setBirth_date("5");
        registerRequest.setBirth_month("3");
        registerRequest.setBirth_year("1997");
        registerRequest.setAddress1("helmsman");
        registerRequest.setAddress2("Radek helmsman");
        registerRequest.setCompany("iti");
        registerRequest.setCity("helwan");
        registerRequest.setState("cairo");
        registerRequest.setZipcode("12345");
        registerRequest.setCountry("egypt");
        registerRequest.setMobile_number("01025237287");

        Response response = given().log().all().spec(req)
                .formParam("name", registerRequest.getName())
                .formParam("email", registerRequest.getEmail())
                .formParam("password", registerRequest.getPassword())
                .formParam("title", registerRequest.getTitle())
                .formParam("birth_date", registerRequest.getBirth_date())
                .formParam("birth_month", registerRequest.getBirth_month())
                .formParam("birth_year", registerRequest.getBirth_year())
                .formParam("firstname", registerRequest.getFirstname())
                .formParam("lastname", registerRequest.getLastname())
                .formParam("company", registerRequest.getCompany())
                .formParam("address1", registerRequest.getAddress1())
                .formParam("address2", registerRequest.getAddress2())
                .formParam("country", registerRequest.getCountry())
                .formParam("zipcode", registerRequest.getZipcode())
                .formParam("state", registerRequest.getState())
                .formParam("city", registerRequest.getCity())
                .formParam("mobile_number", registerRequest.getMobile_number())
                .when()
                .post("/api/createAccount");

        String responseBody = response.getBody().asString();
        System.out.println("Raw Response: " + responseBody);


        ObjectMapper objectMapper = new ObjectMapper();
        RegistrationResponse registerResponse = objectMapper.readValue(responseBody, RegistrationResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(registerResponse.getMessage(), "Email already exists!");
        Assert.assertEquals(registerResponse.getResponseCode(), 400);


    }
}
