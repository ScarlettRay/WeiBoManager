package xyz.iamray.flow;

import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.exception.WbException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractFlow implements Flow{

    protected Map<String,Object> properties = new HashMap<>();

    protected Map<String, String> requiredMap = new HashMap<>();

    protected List<String> apis = new ArrayList<>();


    private FlowState state = FlowState.UNRUN;


    @Override
    public void put(String key, Object object) {
        this.properties.put(key,object);
    }

    @Override
    public FlowState getState() {
        return this.state;
    }

    @Override
    public <T> R<T> execute(Context context) throws Exception {
        check();
        properties.forEach(context::setProperty);
        return APIManager.call(properties.get(INIT_PARAM),getApis(), (String) properties.get(INIT_UID),context);
    }

    public String checkUtil(Map<String,String> requiredMap){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : requiredMap.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (properties.get(k) == null) {
                sb.append(i++).append(".").append(v)
                        .append("(key:").append(k)
                        .append(")").append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public void check() throws Exception {
        String mes = checkUtil(requiredMap);
        if(!mes.isEmpty()){
            throw new WbException(mes);
        }
    }

    protected List<String> getApis(){
        return apis;
    }
}
