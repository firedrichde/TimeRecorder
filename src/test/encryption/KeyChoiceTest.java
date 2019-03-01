package test.encryption;

import encryption.KeyChoice;
import encryption.KeyMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.Method;

/**
 * KeyChoice Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class KeyChoiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: encryption(String str)
     */
    @Test
    public void testEncryption() throws Exception {
//TODO: Test goes here...
        KeyChoice kc = new KeyChoice(2);
        byte[] x = kc.encryption("abcdefghijkmnlopqrstuvwxyz");
        int a = 1;
        System.out.println(new String(x));
    }

    /**
     * Method: encryption2()
     */
    @Test
    public void encryption2() throws Exception {
//TODO: Test goes here...
        String testStr = "HJ";
        KeyChoice kc = new KeyChoice(2);
        String envalue = kc.encryption2(testStr);
        System.out.println(envalue);
        String devalue = kc.decorption(envalue);
        Assert.assertEquals(devalue,testStr);

    }

    /**
     * Method: print(Object x)
     */
    @Test
    public void testPrint() throws Exception {
//TODO: Test goes here...



/* 
try { 
   Method method = KeyChoice.getClass().getMethod("print", Object.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
