package xyz.iamray.flow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractFlow implements Flow{

    private Map<String,Object> properties = new HashMap<>();

    private FlowState state = FlowState.UNRUN;

    @Override
    public void put(String key, Object object) {
        this.properties.put(key,object);
    }


    @Override
    public FlowState getState() {
        return this.state;
    }

}
