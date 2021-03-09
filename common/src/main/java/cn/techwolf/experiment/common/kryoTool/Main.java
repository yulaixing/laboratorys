package cn.techwolf.experiment.common.kryoTool;

import java.util.Arrays;

class Msg {
    byte[] version_flag;

    short crc_code;

    byte[] msg_body;

    public byte[] getVersion_flag() {
        return version_flag;
    }

    public void setVersion_flag(byte[] version_flag) {
        this.version_flag = version_flag;
    }

    public short getCrc_code() {
        return crc_code;
    }

    public void setCrc_code(short crc_code) {
        this.crc_code = crc_code;
    }

    public byte[] getMsg_body() {
        return msg_body;
    }

    public void setMsg_body(byte[] msg_body) {
        this.msg_body = msg_body;
    }
}

public class Main {



    public static void main(String[] args) {

        Serializer ser = new kryoSerializer(Msg.class);

        for (int i = 0; i < 10; i++) {

            Msg msg = new Msg();
            msg.setVersion_flag(new byte[] { 1, 2, 3 });
            msg.setCrc_code((short) 1);
            msg.setMsg_body(new byte[] { 123, 123, 123, 43, 42, 1, 12, 45, 57, 98 });
            byte[] bytes = new byte[300];
            long start = System.nanoTime();
            ser.serialize(msg, bytes);
            System.err.println("序列化耗时：" + (System.nanoTime() - start));
            System.out.println(msg);
            System.out.println(Arrays.toString(bytes));

            Msg newmsg = null;
            start = System.nanoTime();
            newmsg = ser.deserialize(bytes);
            System.err.println("反序列化耗时：" + (System.nanoTime() - start));
            System.out.println(newmsg);
        }

    }
}
