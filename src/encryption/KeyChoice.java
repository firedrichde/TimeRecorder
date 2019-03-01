package encryption;

import java.util.ArrayList;
import java.util.Base64;
import java.nio.*;


//caesar-cipher k
public class KeyChoice {
    private int km;
    private ArrayList<Character> words;
    private ByteBuffer buffer;
    private ByteBuffer debuffer;
    //    private ShortBuffer sbuffer;
    private static final int BSIZE = 1024;

    public KeyChoice(int k) {
        km = k;
        this.buffer = ByteBuffer.allocate(BSIZE);
//        this.sbuffer = ByteBuffer.allocate(BSIZE).asShortBuffer();
        this.buffer.rewind();
        this.debuffer = ByteBuffer.allocate(BSIZE);
        this.debuffer.rewind();
    }

    private void print(Object x) {
        System.out.print(x);
        System.out.print(" ");
    }

    public byte[] encryption(String str) {
        byte[] x = str.getBytes();
        byte[] temp = new byte[1000];
        for (int i = 0; i < x.length; i++) {
            int t = Byte.toUnsignedInt(x[i]);
            if ((t + km) > 122) {
                temp[i] = (byte) (t - 26 + km);
            } else {
                temp[i] = (byte) (t + km);
            }
//            temp[i] = (byte)((t+km+122)%122);

            print(x[i]);
            print(temp[i]);
//            print((byte)(Byte.toUnsignedInt(temp[i])-km));
            System.out.println();
        }
        print(new String(x));
        return temp;

    }

    public String encryption2(String str) {
        this.handle(str);
        StringBuilder sb = new StringBuilder();
        this.buffer.rewind();
        Byte c;
        while ((c =  this.buffer.get()) != 0) {
            sb.append(c);
        }
        return sb.toString();

    }

    public String decorption(String msg){
        this.dehandle(msg);
        StringBuilder sb = new StringBuilder();
        this.debuffer.rewind();
        char x;
        while ((x=(char) this.debuffer.get())!=0){
            sb.append(x);
        }
        return sb.toString();
    }
    private void dehandle(String str){
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        bb.put(str.getBytes());
        bb.rewind();
        byte u;
        this.debuffer.rewind();
        while ((u=bb.get())!=0){
            byte highbits = (byte) ((u & (byte)0b11110000)>>> 4);
            byte lowbits = (byte) (u & (byte)0b00001111);
            byte enHighbits = KeyMap.reverGet(highbits);
            byte enlowbits = KeyMap.reverGet(lowbits);
            byte en8bits = (byte)((enHighbits << 4) | enlowbits);
            this.debuffer.put(en8bits);
        }

    }


    private void handle(String str) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        bb.put(str.getBytes());
        bb.rewind();
        byte u;
        while ((u=bb.get())!=0){
            byte highbits = (byte) ((u & (byte)0b11110000)>>> 4);
            byte lowbits = (byte) (u & (byte)0b00001111);
            byte enHighbits = KeyMap.get(highbits);
            byte enlowbits = KeyMap.get(lowbits);
            byte en8bits = (byte)((enHighbits << 4) | enlowbits);
            this.buffer.put(en8bits);
        }
    }

}
