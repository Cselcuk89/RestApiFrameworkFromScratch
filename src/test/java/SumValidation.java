import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses(){
        int sum = 0;
        JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
        int count = jsonPath.getInt("courses.size()");
        for (int i = 0;i < count;i++){
            int totalAmount = jsonPath.getInt("courses[" + i + "].price")
                    * jsonPath.getInt("courses["+i+"].copies");
            System.out.println("total amount is : " + totalAmount);
            sum = sum + totalAmount;
        }
        System.out.println(sum);
        Assert.assertEquals(sum,jsonPath.getInt("dashboard.purchaseAmount"));
    }
}
