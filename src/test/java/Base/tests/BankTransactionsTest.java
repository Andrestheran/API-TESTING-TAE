package Base.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Test
public class BankTransactionsTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getTest(){
        String endpoint = "https://6220fc62afd560ea69a3f9f5.mockapi.io/api/testing/BankTransactions";

        Response response = given().when().get(endpoint);
        response.then().extract().response();
        response.prettyPrint();
        System.out.println(response.jsonPath().getString("name"));
    }

    @Test
    public void postTest(){
        String endPoint = "https://6220fc62afd560ea69a3f9f5.mockapi.io/api/testing/BankTransactions";

        Response response;

        Map<String, Object> user = new HashMap<String, Object>();
        user.put("name", "Andres");
        user.put("lastName", "Theran");
        user.put("accountNumber", "1020820700");
        user.put("id", "365");
        user.put("transactionType", "deposit");
        user.put("email", "Andrestheran@outlook.com");
        user.put("active", false);
        user.put("country", "Colombia");
        user.put("telephone", "350-339-6888");

        response = given().contentType("application/json").body(user).when().post(endPoint);

        response.prettyPrint();
        response.then().assertThat().statusCode(201);

        System.out.println(response.statusCode());
    }

    @Test
    public void updateTest(){
        String endpoint = "https://6220fc62afd560ea69a3f9f5.mockapi.io/api/testing/BankTransactions";

        Response response;

        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "Cristiano");
        user.put("lastName", "Ronaldo");
        user.put("telephone", "7-777-777");
        user.put("id", "CR7");

        response = given().contentType("application/json").body(user).when().put(endpoint);

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteTest(){

        String endpoint = "https://6220fc62afd560ea69a3f9f5.mockapi.io/api/testing/BankTransactions";

        Response response = given().when().delete(endpoint);

        response.then().extract().statusCode();

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
}
