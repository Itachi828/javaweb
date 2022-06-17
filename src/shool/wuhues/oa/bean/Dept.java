package shool.wuhues.oa.bean;

/**
 * @Author: Itachi
 * @Date: 2022/6/15 15:09
 * @Version: jdk1.8
 * @Description: 部门类
 */
public class Dept {
    private String deptno;
    private String dname;
    private String loc;

    public Dept(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
