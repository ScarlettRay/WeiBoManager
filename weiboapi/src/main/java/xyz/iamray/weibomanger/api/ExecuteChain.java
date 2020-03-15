package xyz.iamray.weibomanger.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * API的执行链
 */
public class ExecuteChain {

    private List<String> apiNumberList = new ArrayList<>();

    private ExecuteChain(){}

    /**
     *
     * @param apiNumbers
     * @return
     */
    public static ExecuteChain create(APINumber... apiNumbers){
        ExecuteChain chain = new ExecuteChain();
        for (APINumber apiNumber : apiNumbers) {
            chain.apiNumberList.add(apiNumber.name());
        }
        return chain;
    }


}
