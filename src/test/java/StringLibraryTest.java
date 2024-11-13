import com.Akemi0Homura.StringLibrary;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Akemi0Homura
 */
public class StringLibraryTest {
    /**
     * 需要手动校错
     */
    @Test
    public void testNoNull() {
        List<String> list=new ArrayList<>();
        list.add(null);
        list.add("");
        list.add(" ");
        list.add("\t");
        list.add("\n");

        list.add("abc");//返回false，上面均为true

        for (String s : list) {
            if(!StringLibrary.NoNull(s)){
                assertEquals(list.get(list.size()-1),s);
                System.out.println(s+"对");
            }else{
                System.out.println(s+"错");
            }
        }
    }

    /**
     * 需要手动校错
     */
    @Test
    public void testNoSpace(){
        List<String> list=new ArrayList<>();
        list.add("\t");
        list.add("\n");
        list.add(null);
        list.add("");
        list.add("a");

        list.add(" ");//返回true，上面均为false
        list.add("a b c");
        list.add(" abc ");

        for (String s : list) {
            if(StringLibrary.NoSpace(s)){
                System.out.println(s+"对");
            }else{
                System.out.println(s+"错");
            }
        }
    }

    /**
     * 绿了就能过
     */
    @Test
    public void testPureDigital(){
        List<String> list=new ArrayList<>();
        list.add(null);
        list.add("");
        list.add("\t");
        list.add("\n");
        list.add(" ");
        list.add("abc");
        list.add("1 2 3 4 5");

        list.add("123456");//返回true，上面均为false

        for(String s:list){
            if(StringLibrary.PureDigital(s)){
                assertEquals(list.get(list.size()-1),s);
                System.out.println(s+"对");
            }else{
                System.out.println(s+"错");
            }
        }
    }

    /**
     * 需要手动校错
     */
    @Test
    public void testAnyAlphabetNumbers(){
        List<String> list=new ArrayList<>();
        list.add(null);
        list.add("");
        list.add("\t");
        list.add("\n");
        list.add(" ");
        list.add("a b c");
        list.add("1 2 3 4 5");

        list.add("a b c 1 2 3 4 5");//返回true，上面均为false
        list.add(" abc123456 ");
        list.add("abc123456!@#$%^&*");
        list.add("abc123456");
        list.add("A1");
        for (String s : list) {
            if(StringLibrary.AnyAlphabetNumbers(s)){
                System.out.println(s+"对");
            }else{
                System.out.println(s+"错");
            }
        }
    }

    /**
     * 需要手动校错
     */
    @Test
    public void testMaxAndMixLettersAndNumerals(){
        List<String> list=new ArrayList<>();
        list.add(null);
        list.add("");
        list.add("\t");
        list.add("\n");
        list.add(" ");
        list.add("a b c");
        list.add("1 2 3 4 5");
        list.add("a b c 1 2 3 4 5");
        list.add(" abc123456 ");
        list.add("abc123456!@#$%^&*");
        list.add("abc123456");
        list.add("A1");

        list.add("Aa1");//返回true，上面均为false
        list.add("A a 1");
        list.add(" A a 1 ");
        list.add("Aa1!@#$%%^");
        for (String s : list) {
            if(StringLibrary.MaxAndMixLettersAndNumerals(s)){
                System.out.println(s+"对");
            }else{
                System.out.println(s+"错");
            }
        }
    }

    /**
     * 绿了就能过
     */
    @Test
    public void testStringLength(){
        assertTrue(StringLibrary.StringLength("abc", 3));
        assertTrue(StringLibrary.StringLength("123", 3));
        assertTrue(StringLibrary.StringLength("ABC", 3));

        assertTrue(StringLibrary.StringLength("a b c", 5));
        assertTrue(StringLibrary.StringLength(" abc ", 5));

        assertTrue(StringLibrary.StringLength("abc123", 6));

        assertTrue(StringLibrary.StringLength("我是苹果", 4));

        assertTrue(StringLibrary.StringLength("\t\t\n", 3));
    }

    /**
     * 需要手动校错，手动调整参数
     */
    @Test
    public void desensitize(){
        String str="1234567890";

        System.out.println(StringLibrary.Desensitize(str,3,3,'*'));
        System.out.println(StringLibrary.Desensitize(str,1,10,'*'));

        //下面就是要它强行抛异常
        //System.out.println(StringLibrary.Desensitize(str,9,3,'*'));
        //System.out.println(StringLibrary.Desensitize(str,0,3,'*'));
    }

    /**
     * 需要手动校错
     */
    @Test
    public void CodeConversion(){
        //默认值,注意，str的原文本编码来自于编程软件的设置
        String str="这里采用中文，为什么采用中文呢？因为字符编码大部分时候出问题的都是中文";

        //转换成乱码就成功了
        System.out.println(StringLibrary.CodeConversion(str, String.valueOf(StandardCharsets.ISO_8859_1)));
    }

    /**
     * 需要手动校错
     */
    @Test
    public void RandomChar(){
        System.out.print("数字：");
        for(int i=0;i<100;i++){
            System.out.print(StringLibrary.RandomChar(1));
        }
        System.out.println();
        System.out.print("任意大小写：");
        for(int i=0;i<100;i++){
            System.out.print(StringLibrary.RandomChar(2));
        }
        System.out.println();
        System.out.print("小写：");
        for(int i=0;i<100;i++){
            System.out.print(StringLibrary.RandomChar(3));
        }
        System.out.println();
        System.out.print("大写：");
        for(int i=0;i<100;i++){
            System.out.print(StringLibrary.RandomChar(4));
        }
        System.out.println();
        System.out.print("特殊符号：");
        for(int i=0;i<100;i++){
            System.out.print(StringLibrary.RandomChar(5));
        }
    }

    /**
     * 需要手动校错
     */
    @Test
    public void RandomString(){
        System.out.println("数字字母："+StringLibrary.RandomString(100,1));
        System.out.println("数字："+StringLibrary.RandomString(100,2));
        System.out.println("字母+特殊符号："+StringLibrary.RandomString(100,3));
        System.out.println("数字+特殊符号："+StringLibrary.RandomString(100,4));
        System.out.println("数字+字母+特殊符号："+StringLibrary.RandomString(100,5));
        System.out.println("纯字母："+StringLibrary.RandomString(100,6));
        System.out.println("纯特殊符号："+StringLibrary.RandomString(100,7));
    }

    /**
     * 绿了即可
     */
    @Test
    public void SHA256Encrypt(){
        //不加盐测试
        String str1="这段是需要加密的文本";
        String str2="这段是需要加密的文本1";
        String hx1=StringLibrary.SHA256Encrypt(str1);
        String hx2=StringLibrary.SHA256Encrypt(str2);
        assertNotEquals(hx1, hx2);
        //加盐测试
        String[] hx3=StringLibrary.SHA256Encrypt(str1,5);
        System.out.println("盐："+hx3[1]);
        assertNotEquals(hx1, hx3[0]);
    }

    /**
     * 绿了即可
     */
    @Test
    public void SHA256Equivalent(){
        //不加盐
        String str="我是苹果，我是苹果，我是苹果";
        String hx1=StringLibrary.SHA256Encrypt(str);
        assertTrue(StringLibrary.SHA256Equivalent(hx1,str));
        //加盐
        String[] hx=StringLibrary.SHA256Encrypt(str,5);
        assertTrue(StringLibrary.SHA256Equivalent(hx[0],str,hx[1]));
    }
}
