import Pojo.LoginResponse;
import Pojo.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {
    private RegisterRequest registerRequest;

    @BeforeClass
    public void setupData() {
        System.out.println("🔄 إعداد بيانات الاختبار...");
        registerRequest = new RegisterRequest();
        registerRequest.setEmail("hebamahfouz183@gmail.com");
        registerRequest.setPassword("152531997");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("⏳ بدء اختبار جديد...");
    }

    @Test(priority = 1, description = "اختبار تسجيل الدخول")
    public void testLogin() throws Exception {
        System.out.println("🚀 تنفيذ testLogin...");

        Response response = given()
                .spec(req)
                .formParam("email", registerRequest.getEmail())
                .formParam("password", registerRequest.getPassword())
                .when()
                .post("/api/verifyLogin");

        String responseBody = response.getBody().asString();
        System.out.println("Raw Response: " + responseBody);

        // تحويل الاستجابة إلى كائن Java
        ObjectMapper objectMapper = new ObjectMapper();
        LoginResponse loginResponse = objectMapper.readValue(responseBody, LoginResponse.class);

        // التأكد من صحة البيانات
        Assert.assertEquals(response.getStatusCode(), 200, "❌ Status Code غير متوقع!");
        Assert.assertEquals(loginResponse.getResponseCode(), 200, "❌ Response Code غير متوقع!");
        Assert.assertEquals(loginResponse.getMessage(), "User exists!", "❌ رسالة الاستجابة غير متوقعة!");


    }


}
