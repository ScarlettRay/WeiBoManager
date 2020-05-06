package test;

/**
 * @author winray
 * @since v1.0.1
 */
public class SubTest extends ParentTest{

     {
        str = "sub";
    }
    public static void main(String[] args) {
        System.out.println(new SubTest().str);
        System.out.println(new SubTest1().str);
    }
}
