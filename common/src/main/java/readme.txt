 //  sslKeyStorePath=pfx文件路径
    //            sslKeyStorePassword=pfx文件密码
    //    sslKeyStoreType=PKCS12
    //            #jdk中的cacerts库地址
    //            sslTrustStore=C:/Program Files/Java/jre7/lib/security/cacerts
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