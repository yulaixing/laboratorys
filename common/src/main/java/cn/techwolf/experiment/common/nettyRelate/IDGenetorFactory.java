package cn.techwolf.experiment.common.nettyRelate;

import java.nio.CharBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yl.xing
 * @create:2020-01-16
 * @describe
 **/
public final class IDGenetorFactory {


    public static final class IDGenetor {

        private final AtomicInteger index = new AtomicInteger();


        public int getID() {
            return index.incrementAndGet();
        }


    }

    public static void main(String[] args) {

//        IDGenetor idGenetor = new IDGenetor();
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(idGenetor.getID());
//
//        }


        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put('你');
        buffer.put('好');
//  经过标记后，会持续记住此位置
        buffer.position(1).mark();
        while(buffer.hasRemaining()) {
            System.out.println(buffer.get());
            //  mark将会跳转到上次标记的位置
            buffer.reset();
        }


    }


}
