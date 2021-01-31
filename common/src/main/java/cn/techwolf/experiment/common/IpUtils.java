package cn.techwolf.experiment.common;

public class IpUtils {

//24 16 8

    public static long ipToLong(String strIp) {
        String[]ip = strIp.split("\\.");
        //24+8，16+8，8+8，1
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    public static void getIPAddress(long l){


        System.out.println(l>>>24);

        System.out.println((l&0x00FFFFFF)>>>16);

        System.out.println((l&0x0000FFFF)>>>8);

        System.out.println((l&0x000000FF));

    }


    public static void main(String[] args) {

        System.out.println(ipToLong("219.239.110.138"));

        getIPAddress(ipToLong("219.239.110.138"));
    }

}
