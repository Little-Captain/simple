package test1.model;

import java.util.Date;
import lombok.Data;

@Data
public class SysUser {
    /**
     * 用户 ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 简介
     */
    private String info;

    /**
     * 头像
     */
    private byte[] headImg;

    /**
     * 创建时间
     */
    private Date createTime;
}