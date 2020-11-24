package cn.techwolf.experiment.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 英文单词常用工具
 */
public class WordUtils {

    Map<Integer, String> map = new HashMap();


    public void initWordStore() {

        String word1 = "word1";
        String word2 = "word2";
        String word3 = "word3";
        String word4 = "word4";

        map.put(word1.hashCode(), word1);
        map.put(word2.hashCode(), word2);
        map.put(word3.hashCode(), word3);
        map.put(word4.hashCode(), word4);

    }

    public boolean isExistWord(String word) {

        int i = word.hashCode();

        boolean b = map.containsKey(i);

        return b;

    }


    public static void main(String[] args) {

        WordUtils wordUtils = new WordUtils();

        /*bean初始化*/
        wordUtils.initWordStore();

        String str = "abc";

        boolean existWord = wordUtils.isExistWord(str);

        System.out.println("existWord=" + existWord);

        String str1 = "word1";

        boolean existWord1 = wordUtils.isExistWord(str1);

        System.out.println("existWord1=" + existWord1);
    }


}
