package Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIUtils {

    public static Response getRequest(String url){
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            String data = ConnectionUtils.getData(connection);
            return new Response(code, data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response postRequest(String url, Object object){
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            ConnectionUtils.writeDataObj(object, connection);
            String data = ConnectionUtils.getData(connection);
            int code = connection.getResponseCode();
            return new Response(code, data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
