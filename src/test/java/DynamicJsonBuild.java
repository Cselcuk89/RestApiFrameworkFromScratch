import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
public class DynamicJsonBuild {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.AddBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat()
                .statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = ReusableMethods.rawToJson(response);
        String id = jsonPath.getString("ID");
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]  {{"ojfjty","9363"},{"cwjetee","4253"}, {"okjmfet","533"} };
    }
}
