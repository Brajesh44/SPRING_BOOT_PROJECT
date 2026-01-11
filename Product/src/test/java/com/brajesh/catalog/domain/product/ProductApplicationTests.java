package com.brajesh.catalog.domain.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;


import java.math.BigDecimal;

import static com.brajesh.catalog.domain.product.constant.UriConstants.API_CREATE_PRODUCT;
import static com.brajesh.catalog.domain.product.constant.UriConstants.API_PRODUCT;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests {
    @LocalServerPort
	private  Integer port;
    @BeforeEach
	void setup(){
        RestAssured.baseURI= "http://localhost";
		RestAssured.port=port;
	}
    @Test
    void shouldCreateProduct() {
        String requestBody = """
                 {
                    "productId": "B0C7XQ12AB",
                    "title": "Wireless Bluetooth Headphones",
                    "brand": "SoundMax",
                    "manufacturer": "SoundMax Electronics Pvt Ltd",
                    "price": 129.99
                  }
                """;
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(API_PRODUCT+API_CREATE_PRODUCT)
                .then()
                .statusCode(201)
                .body("productId", Matchers.notNullValue())
                .body("title",Matchers.equalTo("Wireless Bluetooth Headphones"))
                .body("brand",Matchers.equalTo("SoundMax"))
                .body("manufacturer",Matchers.equalTo("SoundMax Electronics Pvt Ltd"))
                .body("price",Matchers.equalTo(129.99F));
    }

}
