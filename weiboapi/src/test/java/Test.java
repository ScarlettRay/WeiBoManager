/**
 * @author winray
 * @since v1.0.1
 */
public class Test {

    public static void main(String[] args) {
        String str = "a;b;c;;;";
        String[] strs = str.split(";");
        System.out.println(strs.length);
        for (String s : strs) {
            System.out.println(s);
        }
    }
}
