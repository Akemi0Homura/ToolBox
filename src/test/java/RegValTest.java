import com.Akemi0Homura.RegVal;
import com.Akemi0Homura.StringTool;
import org.junit.Test;

/**
 * @author Akemi0Homura
 */
public class RegValTest {
    @Test
    public void testSheerString1(){
        System.out.println(com.Akemi0Homura.RegVal.sheerString("-1",1));
        System.out.println(RegVal.sheerString("0.1",1));
        System.out.println(RegVal.sheerString("1 1",1));
        System.out.println(RegVal.sheerString("1\t1",1));
        System.out.println(RegVal.sheerString("1\n1",1));

        System.out.println(RegVal.sheerString("001",1));
    }

    @Test
    public void testSheerString2(){
        System.out.println(RegVal.sheerString("1a",2));
        System.out.println(RegVal.sheerString(".a",2));
        System.out.println(RegVal.sheerString("a a",2));
        System.out.println(RegVal.sheerString("a\ta",2));
        System.out.println(RegVal.sheerString("a\na",2));

        System.out.println(RegVal.sheerString("a",2));
    }

    @Test
    public void testSheerString3(){
        System.out.println(RegVal.sheerString("1A",3));
        System.out.println(RegVal.sheerString(".A",3));
        System.out.println(RegVal.sheerString("A A",3));
        System.out.println(RegVal.sheerString("A\tA",3));
        System.out.println(RegVal.sheerString("A\nA",3));

        System.out.println(RegVal.sheerString("A",3));
    }
}
