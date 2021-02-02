package cn.techwolf.httpserver.parser;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

    private FullHttpRequest fullReq;

    /**
     * 构造一个解析器
     *
     * @param req
     */
    public RequestParser(FullHttpRequest req) {
        this.fullReq = req;
    }

    /**
     * 解析请求参数
     *
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     * @throws IOException
     */
    public Map<String, String> parse() throws IOException {
        HttpMethod method = fullReq.method();

        Map<String, String> parmMap = new HashMap<>();
        String uri = fullReq.uri();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(uri);
            Map<String, List<String>> uriAttributes = decoder.parameters();

            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                for (String attrVal : attr.getValue()) {
                    parmMap.put(attr.getKey(),attrVal);
                }
            }

            return parmMap;

        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullReq);
            decoder.offer(fullReq);

            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

            for (InterfaceHttpData parm : parmList) {

                Attribute data = (Attribute) parm;
                parmMap.put(data.getName(), data.getValue());
            }

//        } else {
//            // 不支持其它方法
//            throw new MethodNotSupportedException(""); // 这是个自定义的异常, 可删掉这一行
//        }

            return parmMap;
        }
        return null;
    }


    public static void main(String[] args) {

        Map<String, String> parmMap = new HashMap<>();

        QueryStringDecoder decoder = new QueryStringDecoder("/?a=1&b=2");

        Map<String, List<String>> uriAttributes = decoder.parameters();
        for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
            for (String attrVal : attr.getValue()) {
                System.out.println(attrVal);
//                responseContent.append("URI: " + attr.getKey() + '=' + attrVal + "\r\n");
            }
        }
        System.out.println(parmMap);
    }

}
