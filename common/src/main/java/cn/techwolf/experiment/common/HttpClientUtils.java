package cn.techwolf.experiment.common;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtils {

    //  sslKeyStorePath=pfx文件路径
    //            sslKeyStorePassword=pfx文件密码
    //    sslKeyStoreType=PKCS  
    //            #jdk中的cacerts库地址
    //            sslTrustStore=C:/Program Files/Java/jre /lib/security/cacerts
    //#cacerts库密码
    //            sslTrustStorePassword=changeit
    //#cacerts库默认类型
    //            sslTrustStoreType=JKS
//    后续更换证书操作
//            查看cacerts证书库中的所有证书
//    keytool -list -keystore cacerts
//    删除证书库中别名为testCer的证书
//    keytool -delete -alias testCer -keystore cacerts
//    再次导入证书
//    keytool -import -alias testCer -keystore cacerts -file C:\Users\admin\Desktop\testCer.cer
//


    //密钥
    static String keyStorePath;
    static String keyStorePassword;
    static String keyStoreType;
    //证书
    static String trustStorePath;
    static String trustStorePassword;
    static String trustStoreType;


    private static CloseableHttpClient client = HttpClients.createDefault();


    public static String sendToHttps(String reqMsg, String url, Map<String, String> headMap) {
        //初始化KeyManager
        KeyManagerFactory keyFactory = null;
        try {
            keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keystore = KeyStore.getInstance(keyStoreType);
            keystore.load(new FileInputStream(new File(keyStorePath)), null);
            keyFactory.init(keystore, keyStorePassword.toCharArray());

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        KeyManager[] keyManagers = keyFactory.getKeyManagers();
        //初始化Trust Manager
        System.out.println("keyFactory =" + keyFactory);
        TrustManagerFactory trustFactory = null;

        try {
            trustFactory = TrustManagerFactory.getInstance("SunX   ");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        KeyStore tsstore;
        try {
            tsstore = KeyStore.getInstance(trustStoreType);
            tsstore.load(new FileInputStream(new File(trustStorePath)), trustStorePassword.toCharArray());
            trustFactory.init(tsstore);
            System.out.println("tsstore =" + tsstore + "   ||  trustFactory = " + trustFactory);
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //zhengshu
        TrustManager[] trustManagers = trustFactory.getTrustManagers();
        System.out.println("trustManagers =" + trustManagers);

        //注册HtpClient
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, trustManagers, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("sslContext =" + sslContext);

        //设置规则限制
        SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv ", "TLSv . ", "TLSv . "}, null,
                new HttpsHostnameVerifier());
        //注册
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
        socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", ssf).build();
        //池化管理
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //创建httpClient
        CloseableHttpClient httpClient;
        httpClient = HttpClients.custom().setConnectionManager(connManager).build();

        //设置httpPost
        HttpPost httpPost = new HttpPost(url);
        if ((!headMap.isEmpty()) && (headMap.size() > 0)) {
            Set<String> keys = headMap.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = ObjectUtils.toString(i.next());
                if ("host".equals(key)) {
                    continue;
                } else {
//                    log.info("key=" + key + ",value=" + (String) headMap.get(key));
//                    httpPost.a Header (key, (String) headMap.get(key));
                }
            }
        }
        StringEntity reqEntity = new StringEntity(reqMsg, "UTF- ");

        Header[] types = httpPost.getHeaders("Content-Type");
        if ((types == null) || (types.length < 1)) {
            httpPost.addHeader("Content-Type", "application/json;charset=utf- ");
        }

        httpPost.setEntity(reqEntity);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            httpPost.abort();
//            return JsonUtils.setError("Fail to connect . response code = " + statusCode + ". error.");
            return "error";
        }

        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf- ");
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (Exception e) {
//            log.error("Change charset to utf-  error.");
//            return JsonUtils.setError("Change charset to utf-  error.");
            return "error";

        }
        return result;
    }


    /**
     * this is a method for execute a get or post;if you want to execute a get
     * method ,make the param list to be null;default timeout period is 6000ms.
     * if you want to add some yourself headers,the third param is for u;else
     * make it be null;
     *
     * @author Mercy
     */
    public static HttpResponse getResponse(String url, List<NameValuePair> list, Header[] headers) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(6000).setSocketTimeout(6000)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        // use the method setCookieSpec to make the header which named
        // set-cookie effect
        HttpResponse response = null;
        HttpUriRequest request = null;
        if (list == null) {
            HttpGet get = new HttpGet(url);
            get.setConfig(config);
            request = get;
        } else {
            HttpPost post = new HttpPost(url);
            post.setConfig(config);
            HttpEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(list, "utf-8");
                post.setEntity(entity);// 此处有个低级错误，谨记！
                request = post;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36");
        request.setHeader("Connection", "keep-alive");

        if (headers != null) {
            request.setHeaders(headers);
        }

        try {
            System.out.println("ready to link " + url);
            response = client.execute(request);
            System.out.println("status code is " + response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static void main(String[] args) throws Exception{

        //btany   https://www.zhongzimao4.xyz
//        HttpResponse response = getResponse("http://www.btant.com/search/**-first-asc-1", null, null);


        HttpResponse response = getResponse("http://127.0.0.1:8080?a=1&b=2&c=3", null, null);
        String s = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(s);







    }


}

