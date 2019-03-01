package encryption;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class KeyMap {
//    密钥
    static private Map<Byte, Byte> keys;

    static {
        keys = new HashMap<Byte, Byte>();
        keys.put((byte) 0b0000, (byte) 0b1000); //8
        keys.put((byte) 0b0001, (byte) 0b1010); //10
        keys.put((byte) 0b0010, (byte) 0b1100); //12
        keys.put((byte) 0b0011, (byte) 0b1001);//9
        keys.put((byte) 0b0100, (byte) 0b1101);//13
        keys.put((byte) 0b0101, (byte) 0b0000);//0
        keys.put((byte) 0b0110, (byte) 0b0001);//1
        keys.put((byte) 0b0111, (byte) 0b0010);//2
        keys.put((byte) 0b1000, (byte) 0b1111);//15
        keys.put((byte) 0b1001, (byte) 0b0100);//4
        keys.put((byte) 0b1010, (byte) 0b0011);//3
        keys.put((byte) 0b1011, (byte) 0b0111);//7
        keys.put((byte) 0b1100, (byte) 0b1110);//14
        keys.put((byte) 0b1101, (byte) 0b0101);//5
        keys.put((byte) 0b1110, (byte) 0b0110);//6
        keys.put((byte) 0b1111, (byte) 0b1011);//11
    }

    public static Byte get(Byte b) {
        return keys.get(b);
    }

    public static Byte reverGet(Byte b) {
        byte ret = (byte)-1;
        for (Byte x : keys.keySet()) {
            if (get(x).equals(b)) {
                ret = x;
            }
        }
        return ret;
    }
}
