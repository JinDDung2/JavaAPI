import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Date;

public class Project01_Map {

    // 위도(x), 경도(y), 도로명 주소(address)를 통해 지도 위치를 알려주는 메서드
    private static void mapService(String x, String y, String address) {
        String staticUrl = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster=";
        try {
            String pos = URLEncoder.encode(x + " " + y,"UTF-8");
            String url = staticUrl;
            url += "center=" + x + "," + y;
            url += "&level=16&w=700&h=500"; // level 그림크기, w 가로, h 세로
            url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(address, "UTF-8");
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "bk77hth99m");
            con.setRequestProperty("X-NCP-APIGW-API-KEY", "8oaciX9VTyECadN4GJuziNLxdFoxjPyibhVqIkqz");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) {
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤이릉 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(tempname+".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
            }else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        // 도로명 주소를 입력 --> 위도, 지번번호, 경도, 위도
        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String clientId="bk77hth99m";
        String clientSecret="8oaciX9VTyECadN4GJuziNLxdFoxjPyibhVqIkqz";
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("주소를 입력하세요 : ");
            String address = io.readLine();
            String addr = URLEncoder.encode(address, "UTF-8");
            String reqURL = apiURL+addr;

            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            connection.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            BufferedReader br;

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            }else{
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String line;
            StringBuffer response = new StringBuffer(); //JSON

            String x = "";  // 경도
            String y = "";  // 위도
            String z = "";  // 주소
            while ((line=br.readLine())!=null) {
                response.append(line);
            }
            br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println("object = " + object.toString(2));

            JSONArray arr = object.getJSONArray("addresses");
            for (int i=0;i<arr.length();i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                System.out.println("address = " + temp.get("roadAddress"));
                System.out.println("jibunAddress = " + temp.get("jibunAddress"));
                System.out.println("경도 = " + temp.get("x"));
                System.out.println("위도 = " + temp.get("y"));
                x = (String) temp.get("x");
                y = (String) temp.get("y");
                z = (String) temp.get("roadAddress");
            }
            mapService(x, y, z);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
