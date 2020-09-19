package com.walmart.shopping.product;

import com.walmart.shopping.product.repository.MongoInit;
import com.walmart.shopping.product.repository.ProductRepository;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"majorVer = 1"})
@ActiveProfiles("dev")
class ProductControllerIntegrationTest extends BaseRestIntegrationTest {
    private static final String RESOURCE_PATH = "/product/filter/";

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setupMongoDb() {
        MongoInit.init(productRepository);
    }

    @Test
    void shouldReturnHttpStatus200WhenSearchFoundOneProduct() {
        Response response = requestSpecification.when()
            .get(RESOURCE_PATH+"181");

        Product[] products = response
            .then().assertThat()
            .statusCode(is(HttpStatus.OK.value()))
            .extract()
            .as(Product[].class);

        Assertions.assertThat(products.length).isEqualTo(1);

        Assertions.assertThat(products[0].getPrice()).isEqualTo(250);
        Assertions.assertThat(products[0].getId()).isEqualTo(181);
        Assertions.assertThat(products[0].getDescription()).isEqualTo("Bebida");
        Assertions.assertThat(products[0].getBrand()).isEqualTo("Coca");
        Assertions.assertThat(products[0].getImage()).isEqualTo("image");
        Assertions.assertThat(products[0].getPriceWithOutDiscount()).isEqualTo(500);
        Assertions.assertThat(products[0].isPriceHaveDiscount()).isTrue();
    }

    @Test
    void shouldReturnHttpStatus404WhenSearchNotFoundProductById() {
        Response response = requestSpecification.when()
                .get(RESOURCE_PATH+"200");

        response
            .then().assertThat()
            .statusCode(is(HttpStatus.NOT_FOUND.value()));

        Assertions.assertThat(response.getBody().asString())
                .isEqualTo("id product not found");
    }

    @Test
    void shouldReturnHttpStatus200WhenSearchFoundManyProducts() {
        Response response = requestSpecification.when()
                .get(RESOURCE_PATH+"Bebida");

        Product[] products = response
                .then().assertThat()
                .statusCode(is(HttpStatus.OK.value()))
                .extract()
                .as(Product[].class);

        Assertions.assertThat(products.length).isEqualTo(3);
    }

    @Test
    void shouldReturnHttpStatus200WhenSearchByStringNotFound() {
        Response response = requestSpecification.when()
                .get(RESOURCE_PATH+"vinos");

        Object[] emptyList = response
                .then().assertThat()
                .statusCode(is(HttpStatus.OK.value()))
                .extract()
                .as(Object[].class);

        Assertions.assertThat(emptyList).isEmpty();
    }

    @Test
    void shouldReturnHttpStatus400WhenSearchByStringIsLessMore3Character() {
        Response response = requestSpecification.when()
                .get(RESOURCE_PATH+"os");

        response
                .then().assertThat()
                .statusCode(is(HttpStatus.BAD_REQUEST.value()));

        Assertions.assertThat(response.getBody().asString())
                .isEqualTo("minimum size invalid search");
    }
}