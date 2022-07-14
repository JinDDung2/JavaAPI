import org.json.JSONArray;
import org.json.JSONObject;

public class Project01_JSON2 {
    public static void main(String[] args) {
        //JSON-java(org.json)

        JSONArray students = new JSONArray();
        JSONObject student = new JSONObject();
        student.put("name", "김모씨");
        student.put("phoneNumber", "010-1111-1111");
        student.put("address", "서울특별시");
        System.out.println("student = " + student);
        // {"phoneNumber":"010-1111-1111","address":"서울특별시","name":"김모씨"}
        students.put(student);

        student = new JSONObject();
        student.put("name", "나모씨");
        student.put("phoneNumber", "010-2222-2222");
        student.put("address", "인천광역시");
        students.put(student);

        JSONObject object = new JSONObject();
        object.put("students", students);
        System.out.println("object = " + object.toString(2));

    }
}
