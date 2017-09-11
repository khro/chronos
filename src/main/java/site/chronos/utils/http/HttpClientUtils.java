package site.chronos.utils.http;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

/**
 * Created by XiaoWei on 2017/9/5.
 */
public class HttpClientUtils  {

//    @Autowired
//    CloseableHttpClient httpsClientUtils;
//
//    public void get(){
//        try(CloseableHttpResponse execute = httpsClientUtils.execute(new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&access_token=ACCESS_TOKEN&openid=OPENID"))) {
//            HttpEntity entity = execute.getEntity();
//            InputStream content = entity.getContent();
//            String s = IOUtils.toString(content);
//            System.out.println(s);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        String body = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&access_token=ACCESS_TOKEN&openid=OPENID", String.class).getBody();
        HashMap hashMap = new Gson().fromJson(body, HashMap.class);
        System.out.println(body);
        System.out.println(hashMap.get("errcode"));

        try(CloseableHttpResponse execute = HttpClients.createDefault().execute(new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&access_token=ACCESS_TOKEN&openid=OPENID"))) {
            InputStream content  = execute.getEntity().getContent();
            String s = IOUtils.toString(content);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
