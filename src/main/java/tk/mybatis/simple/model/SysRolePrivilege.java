package tk.mybatis.simple.model;

public class SysRolePrivilege {

    private Long sysRoleId;
    private Long sysPrivilegeId;

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysPrivilegeId() {
        return sysPrivilegeId;
    }

    public void setSysPrivilegeId(Long sysPrivilegeId) {
        this.sysPrivilegeId = sysPrivilegeId;
    }
}
