package cn.techwolf.experiment.common.javaBase;

import java.util.*;
import java.util.stream.Collectors;

public class StreamBaseTool {

    public static void main(String[] args) {

        Student a = new Student("a", 20);
        Student b = new Student("b", 18);
        Student c = new Student("c", 22);
        Student d = new Student("d", 19);
        Student e = new Student("e", 18);
        List<Student> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.forEach(System.out::println);

        List<String> nameList = list.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        List<Student> studentList = list.stream()
                .filter(s -> s.getAge() > 19)
                .collect(Collectors.toList());

        List<Integer> ageDistinctList = list.stream()
                .map(Student::getAge)
                .distinct()
                .collect(Collectors.toList());

        boolean flag = list.stream()
                .map(Student::getAge)
                .allMatch(i -> i > 18);

        boolean flag2 = list.stream()
                .map(Student::getAge)
                .anyMatch(i -> i > 21);

        boolean flag3 = list.stream()
                .map(Student::getAge)
                .noneMatch(i -> i > 22);

        list.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .forEach(System.out::println);

        Collections.sort(list,Comparator.comparing(abc -> abc.getAge(),(f1,f2)->f1.compareTo(f2)));


        list.stream()
                .sorted(Comparator.comparingInt(Student::getAge).reversed())
                .forEach(System.out::println);

        //如果先按照年龄排序，再按照姓名进行排序，则可以写成：
        list.stream()
                .sorted(Comparator.comparingInt(Student::getAge).thenComparing(Student::getName))
                .forEach(System.out::println);

        //需求：当前页是第2页，每页显示3条
        list.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .skip((2 - 1) * 3) //(当前页-1)*每页显示条数
                .limit(3)          //每页显示条数
                .forEach(System.out::println);

        //需求：照年龄进行分组
        Map<Integer, List<Student>> group = list.stream().collect(Collectors.groupingBy(Student::getAge));
        group.forEach((k, v) -> System.out.println("k:" + k + ",v:" + v.toString()));

        //需求：输出学生姓名用逗号隔开的字符串
        String str = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(str);

//        需求1：求学生年龄的总和
        int sum = list.stream().mapToInt(Student::getAge).sum();
        System.out.println(sum);


        //需求2：求学生年龄的平均数
        double average = list.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
        System.out.println(average);

        int max = list.stream()
                .mapToInt(Student::getAge).max().orElse(1);

        int min = list.stream()
                .mapToInt(Student::getAge).min().orElse(1);

    }


}

class Student {

    public Student(String name,int age){
        this.age=age;
        this.name=name;
    }

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}


