package xyz.iamray.flow;

/**
 * @author winray
 * @since v1.0.1
 * 官方的流程接口，用户不一定要实现这个接口
 * 这个接口只是让官方的流程更加规范化
 */
public interface Flow {

    /**
     * 放入运行时流程需要用到的参数
     * @param key
     * @param object
     */
    void put(String key,Object object);

    /**
     * 运行前检查是否符合运行条件
     * @throws Exception
     */
    void check() throws Exception;

    /**
     * 获取运行状态
     */
    FlowState getState();

    /**
     * 执行，返回流程id
     * @return
     */
    long execute();

    enum FlowState{
        UNRUN,
        RUNNING,
        STOPPING,
        STOPED;
    }
}
