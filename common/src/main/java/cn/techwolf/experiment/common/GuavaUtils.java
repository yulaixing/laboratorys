package cn.techwolf.experiment.common;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yl.xing
 * @create:2020-07-21
 * @describe
 **/
public class GuavaUtils {

    final static String DELIMITER=",";


    public static void listToElementStr(List<String> list){


        String join = Joiner.on(DELIMITER).skipNulls().join(list);

        System.out.println(join);

    }

    public static void elementStrToList(String str){


        List<String> list = Splitter.on(DELIMITER).splitToList(str);

        System.out.println(list);



    }



    public static void withMapTest() {
        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1, "哈哈");
        maps.put(2, "压压");
        String result = Joiner.on(DELIMITER).withKeyValueSeparator(":").join(maps);
        System.out.println(result);
    }

    public static void main(String[] args) {

        List<String> list= Lists.newArrayList("1","2","3","4","5","6");

        listToElementStr(list);

        System.out.println(8659966463l / 50000000L);


        elementStrToList("1,2,3,7,8,9");
    }





}
