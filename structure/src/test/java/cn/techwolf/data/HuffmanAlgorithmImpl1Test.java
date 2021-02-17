package cn.techwolf.data;

import cn.techwolf.data.huffman.EncodeResult;
import cn.techwolf.data.huffman.HuffmanAlgorithmImpl1;
import org.junit.Test;

public class HuffmanAlgorithmImpl1Test {

    @Test
    public void testEncodeString() {
        HuffmanAlgorithmImpl1 huffmanImpl1 = new HuffmanAlgorithmImpl1();
        EncodeResult result = huffmanImpl1.encode("abcdda");
        System.out.println(result.getEncode());
    }

    @Test
    public void testDecode() {
        HuffmanAlgorithmImpl1 huffmanImpl1 = new HuffmanAlgorithmImpl1();
        EncodeResult result = huffmanImpl1.encode("abcdda");
        System.out.println(result.getEncode());
        String decode = huffmanImpl1.decode(result);
        System.out.println(decode);
    }

}
