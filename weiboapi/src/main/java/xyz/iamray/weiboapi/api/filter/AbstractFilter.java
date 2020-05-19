package xyz.iamray.weiboapi.api.filter;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;


/**
 * @author Ray
 * @create 2020-05-12 16:23:48
 * <p>
 */
public abstract class AbstractFilter<I> implements Filter<I> {

    @Override
    public R<I> exe(I input, Context context) {
        if(contains(input)){
            return R.ok(input);
        }else{
            return R.no();
        }
    }
}
