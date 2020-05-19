package xyz.iamray.flow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.flow.impl.forwardhotlist.ForwardHotListFlow;
import xyz.iamray.flow.impl.getfansflow.AddFollowingToGroupWrapperAPI;
import xyz.iamray.flow.impl.getfansflow.BuildGroupsBridgeAPI;
import xyz.iamray.flow.impl.getfansflow.BuildWeiBoerWithGroupBridgeAPI;
import xyz.iamray.flow.impl.getfansflow.GetFansFlow;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.bridge.impl.ListToOneBridgeAPI;
import xyz.iamray.weiboapi.api.impl.ForwardBlogAPI;
import xyz.iamray.weiboapi.pojo.FollowingGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author winray
 * @since v1.0.1
 * ⣿⣿⠿⠀⢰⠇⠀⠰⣾⡯⠭⠭⣭⠭⡭⠭⠭⠭⢿⡀⠙⠿⣿
 * ⣿⣤⠀⠀⠀⢸⠀⢠⣧⣤⣤⣤⠤⢼⣾⠥⣤⡤⠤⠬⡇⣠⣴⣾
 * ⣿⣿⣿⣿⡦⠀⢸⠀⠈⢧⡀⠁⠀⠀⡠⠛⣄⠈⠀⢀⣠⠇⣿⣿⣿
 * ⣿⣿⠿⠋⠀⠀⣸⡄⠀⢤⣉⠓⠚⠋⠁⡀⢸⡩⣯⡭⢿⡀⠙⠿⣿
 * ⣿⣷⣤⣄⡀⢼⠋⠀⠀⠀⠉⠉⠁⠀⠀⠳⣼⠇⠀⠀⣼⢹⣾⣿⣿
 * ⣿⣿⣿⣿⡟⠈⠳⣤⠀⠀⡀⠀⠀⣀⣀⣀⣀⣀⡀⠀⣿⡻⣿⣿⣿
 * ⣿⣿⣿⣿⣾⣿⣿⠞⣆⠸⡇⠚⠉⠁⠀⣿⣿⡟⠉⢁⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣶⣿⣆⠈⠁⠀⠰⡖⠙⠛⠃⣠⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡦⣄⣀⣀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⡿⠿⠟⠛⡛⢷⣤⣀⣠⢾⣛⠛⠿⢿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⠃⠀⠀⠀⢰⠃⢸⠀⠀⠀⠘⡟⣆⠀⠀⢹⣿⣿⣿⣿
 *
 *
 * /＼7　　　 ∠ /
 * /　│　　 ／　／
 * │　Z ＿,＜　／　　 /`ヽ
 * │　　　　　ヽ　　 /　　〉
 * Y　　　　　`　 /　　/
 * ｲ●　､　●　　⊂⊃〈　　/
 * ()　   ︶     　  　|　＼〈
 * >ｰ ､_　 ィ　 │ ／／
 * / へ　　 /　ﾉ＜| ＼＼
 * ヽ_ﾉ　　(_／　 │／／
 * 7　　　　　　　|／
 * ＞―r￣￣`ｰ―＿丿
 */
@Slf4j
public class MainTest {

    @Test
    public void test(){
        System.out.println(((Integer)null == 1000));
    }

    /**
     * 测试转发和涨粉流程
     */
    @Test
    public void testGetFansFlow() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(60);
        RegisterCenter.registerAll();
        //LoginUtil.createSession();
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
        FollowingGroup followingGroup = new FollowingGroup();
        //互粉循环
        executor.scheduleAtFixedRate(() -> {
            Context context = ContextBuilder.buildContext(SpiderPool.executorService);
            context.setProperty(BuildWeiBoerWithGroupBridgeAPI.GROUP_ID,followingGroup.getGroupId());
            GetFansFlow flow = createGetFansFlow();
            try {
                flow.execute(context);
            } catch (Exception e) {
                log.error("Exception",e);
            }
            if(context.getProperty(AddFollowingToGroupWrapperAPI.NEW_GROUP,FollowingGroup.class) != null){
                followingGroup.setGroupId(context.getProperty(AddFollowingToGroupWrapperAPI.NEW_GROUP,FollowingGroup.class).getGroupId());
            }
            countDownLatch.countDown();
            log.info("完成一次循环");
        },0,5, TimeUnit.MINUTES);

        countDownLatch.await();
    }

    @Test
    public void testForwardFlow() throws InterruptedException {
        RegisterCenter.registerAll();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        List<String> mids = new ArrayList<>();
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
        LoginUtil.createSession();
        //转发循环
        executor.scheduleAtFixedRate(() -> {
            Context context = ContextBuilder.buildContext(SpiderPool.executorService);
            context.setProperty(ForwardBlogAPI.HAS_FORWRAD_MIDS,mids);
            ForwardHotListFlow flow = createForwardHotListFlow();
            try {
                flow.execute(context);
            } catch (Exception e) {
                log.error("Exception",e);
            }
            countDownLatch.countDown();
            log.info("完成一次转发");
        },0,60, TimeUnit.MINUTES);

        countDownLatch.await();
    }


    private ForwardHotListFlow createForwardHotListFlow(){
        ForwardHotListFlow flow = new ForwardHotListFlow();
        flow.put("MoreToOneBridgeAPI-index",1);
        flow.put(ListToOneBridgeAPI.INDEX,1);
        flow.put(Flow.INIT_PARAM,TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        return flow;
    }

    private GetFansFlow createGetFansFlow(){
        GetFansFlow flow = new GetFansFlow();
        List<String> groups = Arrays.asList("4495831107338597","4206288836226287","4480292360199904","4495157863336049",
                "4496599013634210","4494677908955722","4480524380658668","4480520966774917","4496121382332207");
        flow.put(BuildGroupsBridgeAPI.GROUPS,groups);
        flow.put(AddFollowingToGroupWrapperAPI.GROUP_NAME,"测试分组");
        flow.put(AddFollowingToGroupWrapperAPI.GROUP_DESC,"测试分组描述");
        flow.put(Flow.INIT_PARAM, TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        return flow;
    }
}
