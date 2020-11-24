package cn.techwolf.experiment.common;

/**
 * 红黑树
 * 思想源于：https://www.cnblogs.com/nananana/p/10434549.html
 */

import jdk.nashorn.internal.ir.CallNode;

import java.util.Random;

/**
 * @author yl.xing
 * @create:2020-07-29
 * @describe
 **/
public class RBTree {

    private RBTree.Node root = null;

    private enum Color {RED, BLACK}

    private enum Child {LEFT, RIGHT}

    private class Node {
        private Integer key;    //key
        private Object data;    //value
        private Node leftChild;   //左子节点
        private Node rightChild;  //右子节点
        private Node parent;  //父节点
        private Color color;   //红黑标示

        private Node() {
        }

        Node(Object key, Object data, Color color) {
            this.key = (Integer) key;
            this.data = data;
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        boolean isRed() {
            if (this.color.equals(Color.RED))
                return true;
            return false;
        }
    }

    /**
     * 插入数据
     *
     * @param value 插入数据
     * @return 数据重复返回false
     */
    boolean insertNode(Integer key, Object value) {
        return insertNode(root, key, value, null, Child.LEFT);
    }

    private boolean insertNode(Node node, Integer key, Object value, Node preNode, Child child) {
        if (node == null) {
            node = new Node(key, value, Color.RED);
            if (preNode == null) {  //父节点为空，将node设为根节点
                root = node;
            } else {
                if (child.equals(Child.LEFT)) {
                    preNode.leftChild = node;
                } else {
                    preNode.rightChild = node;
                }
                node.parent = preNode;
            }

            //通过RB_INSERT_FIXUP对红黑树的结点进行颜色修改以及旋转，让树仍然是一颗红黑树
            RB_INSERT_FIXUP(node);
            return true;
        } else {
            if (key.compareTo(node.key) == 0) {
                //root = node;
                return false;
            } else if (key.compareTo(node.key) < 0) {
                if (!insertNode(node.leftChild, key, value, node, Child.LEFT)) {
                    return false;
                }
            } else {
                if (!insertNode(node.rightChild, key, value, node, Child.RIGHT)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * @param node 插入节点
     */
    private void RB_INSERT_FIXUP(Node node) {
        Node pNode = node.parent;
        if (node == root) { //插入结点为跟节点，直接改变颜色
            node.setColor(Color.BLACK);
            return;
        }
        if (node == null || pNode.color.equals(Color.BLACK)) {    //插入结点的父结点为黑结点,由于插入的结点是红色的，并不会影响红黑树的平衡，直接插入即可，无需做自平衡。
            return;
        } else {    //情景4：插入结点的父结点为红结点
            Node graNode = node.parent.parent;
            if (pNode == graNode.leftChild) {  //父节点是祖父节点的左子节点
                if (graNode.rightChild != null && graNode.rightChild.isRed()) { //情景4.1：叔叔结点存在并且为红结点
                    /*将P和S设置为黑色（当前插入结点I）将gra设置为红色 把gra设置为当前插入结点*/
                    pNode.setColor(Color.BLACK);
                    graNode.rightChild.setColor(Color.BLACK);
                    graNode.setColor(Color.RED);
                    RB_INSERT_FIXUP(graNode);
                } else { //情景4.2：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的左子结点
                    if (node == pNode.leftChild) {//情景4.2.1 插入结点是其父结点的左子结点
                        /*将P设为黑色 将gra设为红色 对gra进行右旋*/
                        pNode.setColor(Color.BLACK);
                        graNode.setColor(Color.RED);
                        RRotate(graNode);
                    } else {    //情景4.2.2 插入结点是其父结点的右子结点
                        /*对P进行左旋 把P设置为插入结点，得到情景4.2.1 进行情景4.2.1的处理*/
                        LRotate(pNode);
                        RB_INSERT_FIXUP(pNode);
                    }

                }
            } else { //4.3 父节点是祖父节点的右子节点
                if (graNode.leftChild != null && graNode.leftChild.isRed()) { //情景4.3：叔叔结点存在并且为红结点+
                    /*将P和S设置为黑色（当前插入结点I）将gra设置为红色 把gra设置为当前插入结点*/
                    pNode.setColor(Color.BLACK);
                    graNode.leftChild.setColor(Color.BLACK);
                    graNode.setColor(Color.RED);
                    RB_INSERT_FIXUP(graNode);
                } else { //情景4.3.1：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的左子结点
                    if (node == pNode.rightChild) {//情景4.3.1：插入结点是其父结点的右子结点
                        /*将P设为黑色 将gra设为红色 对PP进行左旋*/
                        pNode.setColor(Color.BLACK);
                        graNode.setColor(Color.RED);
                        LRotate(graNode);
                    } else {    //情景4.3.2 插入结点是其父结点的右子结点
                        /*对P进行右旋 把P设置为插入结点，得到情景4.3.1 进行情景4.3.1的处理*/
                        RRotate(pNode);
                        RB_INSERT_FIXUP(pNode);
                    }
                }
            }
        }
    }

    /**
     * 右旋
     *
     * @param T
     */
    private void RRotate(Node T) {
        Node lc = T.leftChild;
        T.leftChild = lc.rightChild;
        if (T.leftChild != null) {
            T.leftChild.parent = T;
        }
        lc.rightChild = T;
        returnPNode(T, lc);
    }

    private Node returnPNode(Node T, Node node) {
        if (T == root) {
            root = node;
        } else if (T.parent.leftChild == T) {
            T.parent.leftChild = node;
        } else {
            T.parent.rightChild = node;
        }
        node.parent = T.parent;
        T.parent = node;
        return node;
    }

    /**
     * 左旋
     *
     * @param T
     */
    private void LRotate(Node T) {
        Node rc = T.rightChild;
        T.rightChild = rc.leftChild;
        if (T.rightChild != null) {

            T.rightChild.parent = T;
        }
        rc.leftChild = T;
        returnPNode(T, rc);
    }

    /**
     * 中序
     */
    public void ldrTraversal() {
        ldrTraversal(root);
    }

    /**
     * 中序
     */
    private void ldrTraversal(Node node) {
        if (node != null) {
            ldrTraversal(node.leftChild);
            System.out.print(node.key + ":" + node.color + ";");
            //System.out.print("key:" + node.key + "-value" + node.data + ":" + node.color + ";");
            ldrTraversal(node.rightChild);
        }

    }

    /**
     * 先序
     */
    public void dlrTraversal() {
        dlrTraversal(root);
    }

    /**
     * 先序
     */
    private void dlrTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + ":" + node.color + ";");
            dlrTraversal(node.leftChild);
            dlrTraversal(node.rightChild);
        }

    }

    /**
     * 后序
     */
    public void lrdTraversal() {
        lrdTraversal(root);
    }

    /**
     * 后序
     */
    private void lrdTraversal(Node node) {
        if (node != null) {
            lrdTraversal(node.leftChild);
            lrdTraversal(node.rightChild);
            System.out.print("key:" + node.key + "-value" + node.data + ":" + node.color + ";");
        }

    }

    /**
     * 搜索
     *
     * @param key 传入key
     * @return 返回value
     */
    public Object search(Integer key) {
        if (this.root != null) {
            return searchNode(key, root).data;
        }
        return null;
    }

    /**
     * @param key 删除key对应的node
     */
    public boolean removen(Integer key) {
        if (this.root != null) {
            Node node = searchNode(key, root);
            if (node == null) {
                return false;
            }
            removenNode(node);
            return true;
        }
        return false;
    }

    /**
     * @param node 删除的节点
     */
    private void removenNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.leftChild == null && node.rightChild == null) {    //情景1：若删除结点无子结点，直接删除。
            changeNode(node, null);
        } else if (node.leftChild != null && node.rightChild != null) { //情景3：若删除结点有两个子结点，用后继结点（大于删除结点的最小结点）替换删除结点。
            Node rNode = node.rightChild;
            while (rNode.leftChild != null) {   //找到后继结点
                rNode = rNode.leftChild;
            }
            //  交换位子
            /*if (rNode == node.rightChild) {
                node.rightChild = null;
                rNode.leftChild = node.leftChild;
            } else {
                if (rNode.rightChild != null) {    //后继节点如果有右节点
                    rNode.parent.leftChild = rNode.rightChild;
                    rNode.rightChild.parent = rNode.parent;
                }
                rNode.leftChild = node.leftChild;
                rNode.rightChild = node.rightChild;
            }*/
            changeNode(node, rNode);    //用后继节点替换要删除节点
        } else { //情景2：若删除结点只有一个子结点，用子结点替换删除结点。
            if (node.leftChild != null) {
                changeNode(node, node.leftChild);
            } else {
                changeNode(node, node.rightChild);
            }
        }
    }

    /**
     * 两节点位置交换
     * 交换后删除替换节点fixupNode
     *
     * @param delNode   要删除节点
     * @param fixupNode 替换节点
     */
    private void changeNode(Node delNode, Node fixupNode) {
        RB_DELETE_FIXUP(fixupNode);

        if (fixupNode == null) {
            if (delNode.parent.leftChild == delNode) {
                delNode.parent.leftChild = null;
            } else {
                delNode.parent.rightChild = null;
            }
            return;
        }

        Object data = delNode.data;
        Color color = delNode.color;
        Integer key = delNode.key;
        if (delNode == root) {  // 交换时如果删除节点是根节点，颜色直接改成黑色
            delNode.setColor(Color.BLACK);
        } else {
            delNode.color = fixupNode.color;
        }
        delNode.key = fixupNode.key;
        delNode.data = fixupNode.data;
        fixupNode.key = key;
        fixupNode.data = data;
        fixupNode.color = color;

        removenNode(fixupNode);
    }

    public Node searchNode(Integer key, Node node) {
        if (node == null)
            return null;
        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            if (node.leftChild != null) {
                return searchNode(key, node.leftChild);
            }
            return null;
        } else {
            if (node.rightChild != null) {
                return searchNode(key, node.rightChild);
            }
            return null;
        }
    }

    private void RB_DELETE_FIXUP(Node fixupNode) {
        if (fixupNode == null || fixupNode.isRed()) {    //情景1：替换结点是红色结点
            /*颜色变为删除结点的颜色*/
            return;
        } else {  //情景2：替换结点是黑结点
            Node bNode = fixupNode.parent.rightChild;
            if (fixupNode == fixupNode.parent.leftChild) { //情景2.1：替换结点是其父结点的左子结点
                //情景2.1.1：替换结点的兄弟结点是红结点
                if (bNode.isRed()) {
                    bNode.setColor(Color.BLACK);
                    fixupNode.parent.setColor(Color.RED);
                    RRotate(fixupNode.parent);
                    RB_DELETE_FIXUP(fixupNode);
                } else {  //情景2.1.2： 替换结点的兄弟结点是黑结点
                    //情景2.1.2.1：替换结点的兄弟结点的右子结点是红结点，左子结点任意颜色
                    if (bNode.rightChild != null && bNode.rightChild.isRed()) {
                        /*将S的颜色设为P的颜色 将P设为黑色 将SR设为黑色 对P进行左旋*/
                        bNode.color = fixupNode.parent.color;
                        fixupNode.parent.setColor(Color.BLACK);
                        bNode.rightChild.setColor(Color.RED);
                        LRotate(fixupNode.parent);
                    } else if (bNode.leftChild != null && bNode.leftChild.isRed()) {
                        //情景2.1.2.2：替换结点的兄弟结点的右子结点为黑结点，左子结点为红结点
                        /*将S设为红色 将SL设为黑色 对S进行右旋，得到情景2.1.2.1 进行情景2.1.2.1的处理*/
                        bNode.setColor(Color.RED);
                        bNode.leftChild.setColor(Color.BLACK);
                        RRotate(bNode);
                        RB_DELETE_FIXUP(fixupNode);
                    } else {//删除情景2.1.2.3： 替换结点的兄弟结点的子结点都为黑结点
                        /*将S设为红色 把P作为新的替换结点 重新进行删除结点情景处理*/
                        bNode.setColor(Color.RED);
                        RB_DELETE_FIXUP(fixupNode.parent);
                    }

                }
            } else {
                //删除情景2.2： 替换结点是其父结点的右子结点
                //删除情景2.2.1： 替换结点的兄弟结点是红结点
                if (bNode.isRed()) {
                    /*将S设为黑色 将P设为红色 对P进行右旋，得到情景2.2.2.3 进行情景2.2.2.3的处理*/
                    bNode.setColor(Color.BLACK);
                    fixupNode.parent.setColor(Color.RED);
                    LRotate(fixupNode.parent);
                    RB_DELETE_FIXUP(fixupNode);
                } else { //删除情景2.2.2： 替换结点的兄弟结点是黑结点
                    //删除情景2.2.2.1： 替换结点的兄弟结点的左子结点是红结点，右子结点任意颜色
                    if (bNode.leftChild != null && bNode.leftChild.isRed()) {
                        /*将S的颜色设为P的颜色 将P设为黑色 将SL设为黑色 对P进行右旋*/
                        bNode.color = fixupNode.parent.color;
                        fixupNode.parent.setColor(Color.BLACK);
                        bNode.leftChild.setColor(Color.BLACK);
                        RRotate(fixupNode.parent);
                    } else if (bNode.rightChild != null && bNode.rightChild.isRed()) {//删除情景2.2.2.2： 替换结点的兄弟结点的左子结点为黑结点，右子结点为红结点
                        /*将S设为红色 将SR设为黑色 对S进行左旋，得到情景2.2.2.1 进行情景2.2.2.1的处理*/
                        bNode.setColor(Color.RED);
                        bNode.rightChild.setColor(Color.BLACK);
                        LRotate(bNode);
                        RB_DELETE_FIXUP(fixupNode);
                    } else {//删除情景2.2.2.3：替换结点的兄弟结点的子结点都为黑结点
                        /*将S设为红色 把P作为新的替换结点 重新进行删除结点情景处理*/
                        bNode.setColor(Color.RED);
                        RB_DELETE_FIXUP(fixupNode.parent);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //int[] data={8,6,4};
        //int[] data={8,6,9,5,7,3};
        //int[] data={8,6,7};
        //int[] data={8,5,9,4,6,7};
        //int[] data={8,5,9,4,7,6};
        //int[] data = {8, 5, 9, 7, 6};
        //Object[] data = new Object[100];
        //Object[] data = {2, 4, 15, 11, 19, 3, "F", "G", "B", "A", "D", "C", "E", new Person("小王", 22)};
        /*for (int i = 0; i < 100; i++) {
            Random r = new Random();
            int n = r.nextInt(100);
            data[i] = "数据" + n;
            //System.out.println(n);
        }*/
        RBTree rbt = new RBTree();
        int[] data = {2, 4, 15, 11, 19, 3, 12, 14, 16, 9, 13, 17, 7, 8, 5, 1, 18, 6};
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 9) {
                System.out.println();
            }
            System.out.println(data[i]);
            rbt.insertNode(data[i], data[i]);
            rbt.dlrTraversal();
            System.out.println("\n" + rbt.root.data);
        }
        rbt.removen(6);
        /*for (int i = 0; i < data.length; i++) {
            rbt.insertNode(String.valueOf(i), data[i]);
        }*/
        rbt.ldrTraversal();
        System.out.println("\n" + rbt.root.data);
        rbt.dlrTraversal();
        //System.out.println("\n" + rbt.search("0"));
    }
}
