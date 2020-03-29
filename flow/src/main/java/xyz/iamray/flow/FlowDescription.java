package xyz.iamray.flow;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 流程信息接口
 */
public interface FlowDescription {

    String getFlowDescription();

    Map<String,String> getPropertiesDescription();
}
