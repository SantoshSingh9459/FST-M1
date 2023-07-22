package Project;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
public class consumerTest {

    // Create Map for the headers
    Map<String, String> headers = new HashMap<String, String>();
    // Set resource URI
    String createUser = "/api/users";

    // Create Pact contract
    @Pact( consumer = "UserConsumer", provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws ParseException {

        // Add headers
        headers.put("Content-Type", "application/json");
        //headers.put("Accept", "application/json");

        // Create request and response object
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id", 345)
                .stringType("firstName", "Santosh")
                .stringType("lastName", "Singh")
                .stringType("email", "santosh@example.com");

         // Create rules for request and response
        return builder.given("A request to create a user")
                .uponReceiving("A request to create a user")
                .path(createUser)
                .method("POST")
                .headers(headers)
                .body(requestResponseBody)
                .willRespondWith()
                .status(201)
                .body(requestResponseBody)
                .toPact();

    }

    @Test
    @PactTestFor(providerName = "UserProvider", port = "8080")
    public void postRequestTest() {

        // Mock url
        RestAssured.baseURI = "http://localhost:8080";
       // Create request specification
        RequestSpecification rq = RestAssured.given().headers(headers).when();
        // Create request body
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("id", 345);
        reqBody.put("firstName", "Santosh");
        reqBody.put("lastName", "Singh");
        reqBody.put("email", "santosh@example.com");

        // Send POST request
        Response response = rq.body(reqBody).post(createUser);
        // Assertion
        assert (response.getStatusCode() == 201);


    }

}
