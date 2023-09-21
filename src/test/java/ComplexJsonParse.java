import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.CoursePrice());

         // 1. Print No of courses returned by API
        int count = jsonPath.getInt("courses.size()");

        System.out.println(jsonPath.getInt("courses.size()"));

        // 2.Print Purchase Amount
        System.out.println(jsonPath.getString("dashboard.purchaseAmount"));

        // 3. Print Title of the first course
        System.out.println(jsonPath.getString("courses[0].title"));

        //4. Print All course titles and their respective Prices
        for (int i = 0;i < count;i++){
            System.out.println(jsonPath.getString("courses[" + i + "].title"));
            System.out.println(jsonPath.getInt("courses[" + i + "].price"));
        }

       // 5. Print no of copies sold by RPA Course
        System.out.println("number of copies sold by rpa course is : " + jsonPath.getInt("courses[2].copies"));

        //6. Verify if Sum of all Course prices matches with Purchase Amount



    }
}
