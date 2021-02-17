package cn.techwolf.data.huffman;

public interface HuffmanAlgorithm {
    /**
     * 编码字符串。
     *
     * @param str 指定的需要编码的字符串
     * @return 编码结果
     */
    EncodeResult encode(String str);

    /**
     * 根据编码结果返回原来的字符串。
     *
     * @return 解码出来的字符串。
     */
    String decode(EncodeResult encodeResult);

}
