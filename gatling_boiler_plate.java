import java.util.HashMap;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class ComputerDatabaseSimulation extends Simulation {

    // Define auth0Headers como um campo da classe
    public final Map<String, String> auth0Headers = new HashMap<>();
    public String access_token = "your token here";

    // Construtor para inicializar os cabeçalhos
    {auth0Headers.put("Authorization", "Bearer " + access_token);}

    HttpProtocolBuilder httpProtocol = http.baseUrl("Base_Url")
            .acceptHeader("*/*")
            .contentTypeHeader("application/json");

    ScenarioBuilder myFirstScenario = scenario("My First Scenario")
            .exec(http("Request 1")
                    .get("endpoint path")
                    .headers(auth0Headers)
                    .check(status().is(200)));

    {
        setUp(
                myFirstScenario.injectOpen(constantUsersPerSec(50).during(60))
        ).protocols(httpProtocol);
    }
}
