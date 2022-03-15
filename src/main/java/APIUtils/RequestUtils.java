package APIUtils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.entity.mime.ContentBody;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RequestUtils {

    public static String getRequest(String url){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpUriRequest httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new RuntimeException("Response error");
        } catch (ParseException e) {
            throw new RuntimeException("Entity error");
        }
    }

    public static String postRequest(String url, String path){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            File file = new File(path);
            ContentBody cbFile = new FileBody(file, ContentType.IMAGE_JPEG);
            HttpEntity mpEntity = MultipartEntityBuilder.create().addPart("photo", cbFile).build();
            httppost.setEntity(mpEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new RuntimeException("Response error");
        } catch (ParseException e) {
            throw new RuntimeException("Entity error");
        }
    }

    public static Image downloadImage(String url){
        Image image = null;
        try {
            image = ImageIO.read(new URL(url));
        }
        catch (IOException ignored) {
        }
        return image;
    }
}
