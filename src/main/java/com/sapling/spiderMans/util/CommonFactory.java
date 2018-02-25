/**  
 * @Title: CommonFactory.java  
 * @Package com.sapling.spiderMans.util  
 * @Description: TODO 
 * @author superman  
 * @date 2018年2月20日  
 * @version V1.0  
 */ 
package com.sapling.spiderMans.util;

import com.sapling.spiderMans.pojo.common.ResultBean;

/**  
 * @ClassName: CommonFactory  
 * @Description: 公用的工厂类，用来获取许多基础的对象
 * @author 文泊山  
 * @date 2018年2月20日    
 */
public class CommonFactory {
    public final static ResultBean resultBeanDefault=new ResultBean(0,"操作成功");
    public final static ResultBean resultBeanFailed=new ResultBean(2,"操作成功");
    /**
     * 获取默认的后台返回数据
     * @return
     */
    public static ResultBean getDefaultResultBean() {
        return resultBeanDefault;
    }
    public static ResultBean getFailResultBean() {
        return resultBeanFailed;

    }
}
