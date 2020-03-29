package xyz.iamray.flow;

import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.weiboapi.api.APIManger;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.common.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractFlow implements Flow{

    protected Map<String,Object> properties = new HashMap<>();

    private FlowState state = FlowState.UNRUN;


    @Override
    public void put(String key, Object object) {
        this.properties.put(key,object);
    }

    @Override
    public FlowState getState() {
        return this.state;
    }

    protected abstract List<String> getApis();

    @Override
    public <T> R<T> execute() throws Exception {
        check();
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        properties.forEach(context::setProperty);
        return APIManger.call(properties.get(INIT_PARAM),getApis(), (String) properties.get(INIT_UID),context);
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
}
