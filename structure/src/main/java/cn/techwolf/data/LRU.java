package cn.techwolf.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class LRU<K, V> implements Iterable<K> {

    private Node head;

    private Node tail;

    private HashMap<K, Node> map;

    private int maxSize;

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize * 4/3);
        head.next = tail;
        tail.pre = head;
    }


    private V get(K key) {

        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        remove(node);
        addFirst(node);
        return node.v;
    }

    private void addFirst(Node node) {

        Node next = head.next;

        head.next = node;

        node.pre = head;

        node.next = next;

        next.pre = node;


    }

    private void remove(Node node) {

        Node pre = node.pre;

        Node next = node.next;

        pre.next = next;

        next.pre = pre;

        node.pre = null;

        node.next = null;

    }

    public void put(K k, V v) {

        if (map.containsKey(k)) {
            Node node = map.get(k);
            remove(node);
        }

        Node node = new Node(k, v);

        map.put(k, node);

        addFirst(node);

        //
        if (map.size() > maxSize) {
            Node lastNode = removeLast();
            //?
            map.remove(node.k);
        }


    }

    private Node removeLast() {

        Node pre = tail.pre;

        remove(pre);

        return pre;


    }


    private class Node {

        Node pre;

        Node next;

        K k;

        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

//            private Node oo=head.next;

            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }

        };
    }


    public static void main(String[] args) throws Exception {

        String ip="127.0.0.1";
        int port=8080;
        Socket socket = new Socket(ip,port);


        socket.setSoTimeout(5000);
        InputStream inputStream = socket.getInputStream();

//        inputStream.read()

//        BufferedReader

//        new BufferedReader(new InputStreamReader())




    }

}
