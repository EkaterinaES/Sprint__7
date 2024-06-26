package orders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class ScooterServiceOrderImpl implements ScooterServiceOrder {
    private static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    private static final String BATH_PATH = "https://qa-scooter.praktikum-services.ru/";
    private static final String CANCEL_ORDER_ENDPOINT = "/api/v1/orders/cancel/";
    private static final String RETURN_LIST_OF_ORDERS_ENDPOINT = "/api/v1/orders";

    private static final String ACCEPT_ORDER_ENDPOINT = "/api/v1/orders/accept/{id}";
    private static final String RECEIVE_ORDER_BY_TRACK = "/api/v1/orders/track";
    public static RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .log(LogDetail.ALL)
                    .addHeader("Content-type", "application/json")
                    .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                    .build();

    public ScooterServiceOrderImpl(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Override
    public Response createTheOrder(OrderDetails orderDetails) {
        return given()
//                .log().all()
//                .header("Content-Type", "application/json")
//                .baseUri(BATH_PATH)
                .spec(requestSpecification)
                .body(orderDetails)
                .when()
                .post(CREATE_ORDER_ENDPOINT);
    }

    @Override
    public Response cancelOrder(int trackNumber) {
        return given()
//                .log().all()
//                .header("Content-Type", "application/json")
//                .baseUri(BATH_PATH)
                .spec(requestSpecification)
                .queryParam("track", trackNumber)
                .put(CANCEL_ORDER_ENDPOINT);
    }

    @Override
    public Response returnTheListOfOrders() {
        return given()
//                .log().all()
//                .header("Content-Type", "application/json")
//                .baseUri(BATH_PATH)
                .spec(requestSpecification)
                .get(RETURN_LIST_OF_ORDERS_ENDPOINT);
    }

    @Override
    public Response acceptOrder(int id, int courierId) {
        return given()
//                .log().all()
//                .header("Content-Type", "application/json")
//                .baseUri(BATH_PATH)
                .spec(requestSpecification)
                .queryParam("courierId", courierId)
                .pathParam("id", id)
                //.put(ACCEPT_ORDER_ENDPOINT, id);
                .put(ACCEPT_ORDER_ENDPOINT);
    }

    @Override
    public DetailsOfThtCreatedOrder receiveOrderByNumber(int track) {
        DetailsOfThtCreatedOrder object;
        return   object = given()
                .spec(requestSpecification)
                .queryParam("t", track)
                .get(RECEIVE_ORDER_BY_TRACK)
                .body().as(DetailsOfThtCreatedOrder.class);
//        given()
//                .spec(requestSpecification)
//                .queryParam("t", track)
//                .get(RECEIVE_ORDER_BY_TRACK)
//                .body().as(DetailsOfThtCreatedOrder.class);
    }

}
