package cn.techwolf.experiment.common.utils;

import java.util.HashMap;

/**
 * @author yl.xing
 * @create:2020-06-15
 * @describe
 **/
public class LruBaseHashTable<K,V> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;


    /**
     * 头结点
     */
    private DoubleNode<K,V> headNode;


    /**
     * 尾结点
     */
    private DoubleNode<K,V> tailNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;


    /**
     * 散列表存储key以及对应链表
     */
    private HashMap<K,DoubleNode<K,V>> table;


    static class DoubleNode<K,V>{
        private K key;

        private V value;

        private DoubleNode<K,V> prev;

        private DoubleNode<K,V> next;

        DoubleNode(){}

        DoubleNode(K key,V value){
            this.key=key;
            this.value=value;
        }

    }

    /**
     * 初始化散列表
     * @param capacity
     */
    public LruBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        headNode = new DoubleNode<>();
        tailNode = new DoubleNode<>();

        headNode.next = tailNode;
        tailNode.prev = headNode;

        table = new HashMap<>();
    }

    public LruBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * 新增节点
     * @param key
     * @param value
     */
    public void add(K key,V value){
        DoubleNode<K,V> node = table.get(key);
        if(node==null){
            DoubleNode<K,V> newNode=new DoubleNode<>(key,value);
            table.put(key,newNode);
            //将新结点插入到链表头部
            addNodeToHead(newNode);

            //如果链表容量已超
            if(++length >capacity){
                //从散列表删除尾结点元素
                DoubleNode<K,V> tail = popTail();
                table.remove(tail.key);
                length--;
            }

        }else{
            //添加的节点存在,则覆盖此节点值
            node.value = value;
            //然后将存在的节点移动到链表头部
            moveToHead(node);
        }

    }


    /**
     * 将节点移动到头部
     *
     * @param node
     */
    private void moveToHead(DoubleNode<K, V> node) {
        removeNode(node);
        addNodeToHead(node);
    }



    /**
     * 弹出尾部数据节点
     */
    private DoubleNode<K, V> popTail() {
        DoubleNode<K, V> node = tailNode.prev;
        removeNode(node);
        return node;
    }

    /**
     * 移除节点
     *
     * @param node
     */
    private void removeNode(DoubleNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    /**
     * 将新节点添加到链表头部
     * @param newNode
     */
    public void addNodeToHead(DoubleNode<K,V> newNode){

        newNode.next = headNode.next;
        newNode.prev=headNode;

        headNode.next.prev=newNode;
        headNode.next = newNode;
    }


    /**
     * 从散列表获取节点值
     * @param key
     * @return
     */
    public V get(K key){
        DoubleNode<K,V> node = table.get(key);
        if (node == null) {
            return null;
        }
        //节点存在移动到头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 从散列移除节点数据
     *
     * @param key
     */
    public void remove(K key) {
        DoubleNode<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        //节点存在则移除此节点
        removeNode(node);
        length--;
    }

    private void printAll() {
        DoubleNode<K, V> node = headNode.next;
        while (node.next != null) {
            System.out.print(node.value + ",");
            node = node.next;
        }
        System.out.println();
    }

}
