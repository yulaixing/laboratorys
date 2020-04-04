package cn.techwolf.data;

import java.util.ArrayList;

/**
 *  临近矩阵
 */
public class AMWGraph {

        //存储点的列表
        private ArrayList vertexList;
        //临接矩阵，存储边
        private int[][] edges;
        //边的数目
        private int numOfEdges;

    public AMWGraph(int n) {
        //初始化矩阵，一维数组，和边的数目
        edges=new int[n][n];
        vertexList=new ArrayList(n);
        numOfEdges=0;
    }

    public ArrayList getVertexList() {
        return vertexList;
    }

    public void setVertexList(ArrayList vertexList) {
        this.vertexList = vertexList;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public void setNumOfEdges(int numOfEdges) {
        this.numOfEdges = numOfEdges;
    }


    public static void main(String[] args) {


        synchronized (Object.class){

            try {
                Object.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
