package cn.techwolf.experiment.common.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yl.xing
 * @create:2020-03-02
 * @describe
 **/
public class StreamUtils {

    public static void main(String[] args) {

//        User user = new User();
//
//        user.getDeafaultUserList();

        System.out.println(13&17);  //00001101 00010001

        System.out.println(0x0100);
    }


}


class User implements Serializable {

    private List<User> userList = null;


    public static class LoadUserList {


    }


    public List<User> getDeafaultUserList() {


        List<User> userList = new ArrayList<>();

        User user1 = new User(1, "abc", dateSort(2019, 11, 11));
        User user2 = new User(2, "acd", dateSort(2018, 11, 11));
        User user3 = new User(3, "aef", dateSort(2017, 11, 11));

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        this.userList = userList;

        return this.userList;

    }


    public User() {


    }

    public User(long id, String name, Date time) {

        this.id = id;

        this.name = name;

        this.time = time;

    }


    private long id;

    private String name;

    private Date time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public static Date dateSort(int year, int month, int day) {

        LocalDate localDate = LocalDate.of(2018, 11, 11);

        Instant instant = localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant();

        Date date = Date.from(instant);

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));

        return date;
    }
}
