package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Message;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.*;

/**
 * @author winray
 * @since v1.0.1
 */
public class BuildMessagesBridgeAPI implements ApiBridge<List<WeiBoer>, List<Message>> {

    public static final BuildMessagesBridgeAPI INSTANCE = new BuildMessagesBridgeAPI();

    private static final String DEFAULT_MESSAEG = "真心互粉互赞互评，粉我，我必回粉，[互粉][互粉][互粉] 请回粉：";

    public static final String MESSAGE_KEY = "BuildMessagesBridgeAPI-message";
    @Override
    public String getNumber() {
        return "BuildMessagesBridgeAPI";
    }

    @Override
    public R<List<Message>> exe(List<WeiBoer> weiBoers, Context context) {
        Map<String,List<WeiBoer>> map = context.getProperty(GroupsToWeiBosAPI.GROUP_TO_WEIBOS, HashMap.class);
        List<Message> messageList = new ArrayList<>();
        for (Map.Entry<String, List<WeiBoer>> entry : map.entrySet()) {
            Message message = new Message();
            message.setType(Message.Type.G);
            StringBuilder sb = new StringBuilder();
            if(context.getProperty(MESSAGE_KEY,String.class) == null){
                sb.append(DEFAULT_MESSAEG);
            }else{
                sb.append(context.getProperty(MESSAGE_KEY,String.class));
            }
            HashSet<String> nickName = new HashSet<>();
            if(entry.getValue() != null){
                entry.getValue().forEach(e->{
                    //没有关注我
                    if(!e.isFollowMe()){
                        if(!nickName.contains(e.getNickName())){
                            sb.append("@");
                            sb.append(e.getNickName());
                            sb.append(" ");
                            nickName.add(e.getNickName());
                        }
                    }
                });
            }
            message.setId(entry.getKey());
            message.setText(sb.toString());
            messageList.add(message);
        }
        return R.ok(messageList);
    }
}
