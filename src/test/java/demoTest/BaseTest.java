import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected RequestSpecification req;

    @BeforeClass
    public void setup() {
        req = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com")
                .setContentType("application/x-www-form-urlencoded")
                .build();
        System.out.println("✅ تم إعداد BaseTest!");
    }


}
