package cn.techwolf.httpserver.ssl;

import io.netty.buffer.ByteBufUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLContextFactory {

    public static SSLContext getSslContext() throws Exception {
        char[] passArray = "111111".toCharArray();
        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        KeyStore ks = KeyStore.getInstance("JKS");
        //加载keytool 生成的文件
        FileInputStream inputStream = new FileInputStream("/Users/admin/sslKey/local.jks");
        ks.load(inputStream, passArray);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, passArray);
        sslContext.init(kmf.getKeyManagers(), null, null);
        inputStream.close();
        return sslContext;

    }

    public static void main(String[] args) {
        String code = "474554202f6f6b20485454502f312e310d0a486f73743a206c6f63616c686f73743a383038300d0a436f6e6e656374696f6e3a206b6565702d616c6976650d0a43616368652d436f6e74726f6c3a206d61782d6167653d300d0a557067726164652d496e7365637572652d52657175657374733a20310d0a557365722d4167656e743a204d6f7a696c6c612f352e3020284d6163696e746f73683b20496e74656c204d6163204f5320582031305f31345f3529204170706c655765624b69742f3533372e333620284b48544d4c2c206c696b65204765636b6f29204368726f6d652f37372e302e333836352e3930205361666172692f3533372e33360d0a5365632d46657463682d4d6f64653a206e617669676174650d0a4163636570743a20746578742f68746d6c2c6170706c69636174696f6e2f7868746d6c2b786d6c2c6170706c69636174696f6e2f786d6c3b713d302e392c696d6167652f776562702c696d6167652f61706e672c2a2f2a3b713d302e382c6170706c69636174696f6e2f7369676e65642d65786368616e67653b763d62330d0a5365632d46657463682d536974653a2063726f73732d736974650d0a4163636570742d456e636f64696e673a20677a69702c206465666c6174652c2062720d0a4163636570742d4c616e67756167653a207a682d434e2c7a683b713d302e390d0a0d0a";
        byte[] bytes = ByteBufUtil.decodeHexDump(code);
        System.out.println(new String(bytes));
    }
}
