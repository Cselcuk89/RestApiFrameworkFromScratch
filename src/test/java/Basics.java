import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class Basics {
    public static void main(String[] args) {
        // add place --> update place with new address --> get place to validate if new address is present in response body
        // to do that, we need place_id from post request response payload
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(Payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);// for parsing the json body
        String placeId = jsonPath.getString("place_id");
        System.out.println("place id is : " + placeId);

        //update place
        // place id should come from add place api because we added it so take it and paste in put request body
        String newAddress = "Summer Walk, Africa";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //get place
        // value of place_id query param is actually coming from add place api so we can use placeId string below

        String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all()
                .assertThat().statusCode(200).extract().response().asString();
        JsonPath jsonPath1 = ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath1.getString("address");
        Assert.assertEquals(actualAddress,newAddress);







    }
}
