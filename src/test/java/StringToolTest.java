import com.Akemi0Homura.RegVal;
import com.Akemi0Homura.StringTool;
import org.junit.Test;

/**
 * @author Akemi0Homura
 */
public class StringToolTest {
    @Test
    public void testNoNull() {
        System.out.println(StringTool.NoNull(null));
        System.out.println(StringTool.NoNull(""));
        System.out.println(StringTool.NoNull(" "));
        System.out.println(StringTool.NoNull("   "));
        System.out.println(StringTool.NoNull("\t"));
        System.out.println(StringTool.NoNull("\n"));
        System.out.println(StringTool.NoNull("\t\n"));
        System.out.println(StringTool.NoNull("\n \t"));

        System.out.println(StringTool.NoNull("a"));
    }

    @Test
    public void testRandomString1(){
        //主要测试是否有0开头的纯数字
       for(int i=1;i<=20;i++)System.out.println(StringTool.RandomString(100,1));
    }

    @Test
    public void testRandomString2(){
        //主要测试看有没有除了大小写字符串以外的东西
        for(int i=1;i<=20;i++)System.out.println(StringTool.RandomString(100,2));
    }

    @Test
    public void testRandomString3(){
        //主要看有没有空指针错误
        for(int i=1;i<=20;i++)System.out.println(StringTool.RandomString(100,3));
    }
}
