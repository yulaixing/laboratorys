package cn.techwolf.experiment.common.javaBase;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamMapTool {


    public static void main(String[] args) {

        List<Test> list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            int id = i % 3;
            Test test = new Test();
            test.setId(id);
            test.setName("王小二" + i);
            list.add(test);
        }

        int sum = list.stream().filter(e -> e.getId() > 100).mapToInt(e -> e.getId()).sum();

        System.out.println(sum);


        Map<Integer, Test> map = list.stream().collect(Collectors.toMap(e -> e.getId(), Function.identity(), (f1, f2) -> f1.addOtherTest(f2)));

        System.out.println(JSON.toJSONString(map));


    }


}


class Test {

    private int id;

    private String name;

    public List<Test> list = new ArrayList<>();

    public Test addOtherTest(Test test) {

        list.add(test);
        return this;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * arrayList
 *
 * @param <T>
 */
class Collect<T> {

    private int index = 0;

    private Object[] arrays = new Object[20];

    public void addItem(T t) {
        arrays[index++] = t;
    }

}
