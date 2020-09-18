package com.walmart.shopping.product;

import com.walmart.shopping.Application;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes=Application.class)
class BaseRestIntegrationTest {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @LocalServerPort
    private int port;

    RequestSpecification requestSpecification;
    private static final String USER_AGENT = "Spring Testing";

    @BeforeEach
    public void parentSetUp() {
        final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setAccept(ContentType.JSON);
        requestSpecBuilder.setPort(port);
        requestSpecBuilder.setBasePath(contextPath);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = given(requestSpecBuilder.build())
                        .header(new Header("User-Agent", USER_AGENT));
    }
}