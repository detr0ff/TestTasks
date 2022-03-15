package Utils;

import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;

public class ConnectionUtils {
    public static String getData(HttpURLConnection connection) {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeDataObj(Object object, HttpURLConnection connection){
        try {
            OutputStream os = connection.getOutputStream();
            Gson gson = new Gson();
            String jsonString = gson.toJson(object);
            System.out.println(jsonString);
            byte[] input = jsonString.getBytes();
            os.write(input, 0, input.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

