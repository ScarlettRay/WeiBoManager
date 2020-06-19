package xyz.iamray.weiboapi.pojo;

import lombok.Data;
import xyz.iamray.weiboapi.common.PrizeRequirement;
import xyz.iamray.weiboapi.common.Pair;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class PrizeBlog {

    private Blog blog;

    private List<Pair<PrizeRequirement,String>> requirements;

}
