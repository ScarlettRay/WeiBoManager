package xyz.iamray.weiboapi.common;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class Pair<A,B> {

    public A A;

    public B B;

    public Pair(A a, B b){
        A = a;
        B = b;
    }


}
