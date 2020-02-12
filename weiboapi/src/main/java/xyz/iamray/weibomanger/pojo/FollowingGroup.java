package xyz.iamray.weibomanger.pojo;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 * 關注分組
 */
@Data
public class FollowingGroup {

    private String groupId;

    private String name;

    private String description;

    private boolean isPublic;
}
