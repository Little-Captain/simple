package tk.mybatis.simple.model;

import tk.mybatis.simple.type.Enabled;

import java.util.Date;
import java.util.List;

public class SysRole {

    private Long id;
    private String name;
    private Enabled enabled;
    private Long createBy;
    private Date createTime;

    private CreateInfo createInfo;

    public CreateInfo getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    private List<SysPrivilege> privileges;

    public List<SysPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<SysPrivilege> privileges) {
        this.privileges = privileges;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enabled getEnabled() {
        return enabled;
    }

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}
