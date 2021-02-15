package cn.techwolf.data;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * 构造链表
 */
public class LinkListData {

    private Node sentryNode;

    class Node {
        private String nodeName;

        private Node next;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

// 链表的边界问题，哨兵节点，反转列表

    public void reverse() {

        if (sentryNode == null || sentryNode.next == null) {
            return;
        }

        Node head = sentryNode;
        Node first = sentryNode.next;
        Node second = sentryNode.next.next;
        head.next = null;
        if (second == null) {
            first.next = head;
        }
        while (second != null) {

            Node tempNode = second.next;

            first.next = head;

            second.next = first;


            head = first;

            first = second;

            second = tempNode;

        }
        sentryNode = first;

    }


    public void add(String name) {

        Node node = new Node();
        node.setNodeName(name);

        if (sentryNode == null) {
            sentryNode = node;
            return;
        }


        Node startNode = sentryNode;

        while (startNode.next != null) {
            startNode = startNode.next;
        }
        startNode.next = node;
    }


    @Override
    public String toString() {

        Node startNode = sentryNode;

        StringBuilder stringBuilder = new StringBuilder();

        while (startNode.next != null) {
            stringBuilder.append(startNode.getNodeName());
            stringBuilder.append(" ");
            startNode = startNode.next;
        }
        stringBuilder.append(startNode.getNodeName());

        return stringBuilder.toString();
    }


    public static void main1(String[] args) {


        LinkListData linkListData = new LinkListData();

        linkListData.add("wo");
        linkListData.add("ai");
        linkListData.add("ni");

        System.out.println(linkListData.toString());

        linkListData.reverse();

        System.out.println(linkListData.toString());

        LinkedList<Integer> link = new LinkedList<>();

        BigInteger bigInteger = new BigInteger(String.valueOf(8));

        String binary = bigInteger.toString(2);

        System.out.println(binary);

    }


    public static void secret(int i) {

        //随机密钥
        int random = new Random().nextInt(100);

        //结果
        int result = (i + random) % 100;

        System.out.println("result=" + result);

        //偏移量
        int offset = (i + random) / 100;

        System.out.println("offset=" + offset);

        //解密
        i = result + offset * 100 - random;

        System.out.println(i);
    }


    public static void main(String[] args) {

    }


}
