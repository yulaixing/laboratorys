package cn.techwolf.experiment.common;

import java.io.Serializable;


class MainProcess{

    static class MyRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("优雅退出");
        }
    }


/*程序入口*/
    public static void main(String[] args) {


        BankAcccount bankAcccount = new BankAcccount("王坤",9999.99,"262728129129",Grade.DIAMOND);

        /*存款*/
        bankAcccount.plusAmount(400);

        /*取款*/
        bankAcccount.minusAmount(100);

        /**/
        double amount = bankAcccount.getAmount();

        System.out.println("帐户余额="+amount);

        bankAcccount.setName("王坤的帐户");

        Runtime.getRuntime().addShutdownHook(new Thread(new MyRunable()));

    }


}

public class BankAcccount implements Serializable {

    private String name;

    private double amount;

    private String bankCardNo;

    private Grade grade;

    public BankAcccount() {
    }

    public BankAcccount(String name,double amount,String bankCardNo,Grade grade) {
        this.name=name;
        this.amount=amount;
        this.bankCardNo=bankCardNo;
        this.grade=grade;

        System.out.println("数据初始化 账户名="+name+" 帐户总额="+amount+ " 银行卡号="+bankCardNo+ " 银行卡等级="+grade.getName());

    }

    /*查看余额*/
    public double getAmount() {
        return amount;
    }

    /*存款*/
    public void plusAmount(double enterMoney) {
        System.out.println("存入="+enterMoney);
        this.amount +=enterMoney;
    }

    /*取款*/
    public void minusAmount(double outMoney) {
        System.out.println("取出="+outMoney);
        this.amount -=outMoney;
    }

    //获取账户名称
    public String getName() {
        return name;
    }

    //设置账户名称
    public void setName(String name) {
        this.name = name;
    }
}

enum Grade {

    SLIVER(1,"银卡"),
    GOLD(2,"金卡"),
    DIAMOND(3,"白金卡");

    private Grade(int type, String name) {
        this.type = type;
        this.name = name;
    }

    int type;

    String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
