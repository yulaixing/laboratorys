package cn.techwolf.experiment.common.utils;

/**
 * @author yl.xing
 * @create:2020-07-20
 * @describe
 **/
public class BTreeUtils {

    private Btree hTreeNode;

    class Btree {

        public Btree(String data, Btree lTree, Btree rTree) {
            this.data = data;
            this.lTree = lTree;
            this.rTree = rTree;
        }

        private String data;

        private Btree lTree;

        private Btree rTree;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Btree getlTree() {
            return lTree;
        }

        public void setlTree(Btree lTree) {
            this.lTree = lTree;
        }

        public Btree getrTree() {
            return rTree;
        }

        public void setrTree(Btree rTree) {
            this.rTree = rTree;
        }
    }


    public void initTree() {

        Btree btree = new Btree("1", null, null);
        this.hTreeNode = btree;

        Btree b1 = new Btree("2", null, null);
        Btree b2 = new Btree("3", null, null);
        Btree b3 = new Btree("4", null, null);
        Btree b4 = new Btree("5", null, null);
        Btree b5 = new Btree("6", null, null);
        Btree b6 = new Btree("7", null, null);

        btree.setlTree(b1);

        btree.setrTree(b2);

        b1.setlTree(b3);

        b1.setrTree(b4);

        b2.setlTree(b5);

        b2.setrTree(b6);

    }


    public void traversalStart(){

        traversal(hTreeNode);
    }


    /**
     *
     */
    public void traversal(Btree headNode){

        if(headNode==null){
            return ;
        }
        System.out.println(headNode.getData());

        if(headNode.getlTree()!=null){

            traversal(headNode.getlTree());
        }

        if(headNode.getrTree()!=null){

            traversal(headNode.getrTree());
        }

    }


    public static void main(String[] args) {

        BTreeUtils bTreeUtils = new BTreeUtils();

        bTreeUtils.initTree();

        bTreeUtils.traversalStart();

    }





}
