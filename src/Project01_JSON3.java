import com.google.gson.stream.JsonToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Project01_JSON3 {
    public static void main(String[] args) {
        String src = "info.json";
        // IO --> stream
        InputStream is = Project01_JSON.class.getResourceAsStream(src);

        if(is==null) {
            throw new NullPointerException("Cannot find the resource file");
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        JSONArray students = object.getJSONArray("students");
        for(int i=0;i< students.length();i++) {
            JSONObject stu = (JSONObject)students.get(i);
            System.out.print(stu.get("name") + "\t");
            System.out.print(stu.get("address") + "\t");
            System.out.println(stu.get("phoneNumber"));
        }
    }
}
