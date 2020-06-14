package xyz.iamray.weiboapi.pojo;

import lombok.Data;
import xyz.iamray.weiboapi.common.PrizeRequirement;
import xyz.iamray.weiboapi.common.Tuple;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class PrizeBlog {

    private Blog blog;

    private List<Tuple<PrizeRequirement,String>> requirements;

}
