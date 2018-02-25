/**  
 * @Title: ResultBean.java  
 * @Package com.sapling.spiderMans.pojo.common  
 * @Description: TODO 
 * @author superman  
 * @date 2018年2月20日  
 * @version V1.0  
 */ 
package com.sapling.spiderMans.pojo.common;

/**  
 * @ClassName: ResultBean  
 * @Description: 公共的返回bean类，用于和后台进行数据交互
 * @author 文泊山  
 * @date 2018年2月20日    
 */
public class ResultBean {
    private long code;
    private String msg;
    private long count;
    private Object data;

    public long getCode() {
        return code;
    }
    public void setCode(long code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    /**
     * @param code
     * @param msg
     */
    public ResultBean(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
