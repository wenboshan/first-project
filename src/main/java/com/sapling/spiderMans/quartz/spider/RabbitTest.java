/**  
 * @Title: RabbitTest.java  
 * @Package com.sapling.spiderMans.quartz.spider  
 * @Description: TODO 
 * @author superman  
 * @date 2018年2月23日  
 * @version V1.0  
 */ 
package com.sapling.spiderMans.quartz.spider;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.sapling.spiderMans.service.douban.DoubanSpiderService;
import com.sapling.spiderMans.service.douban.Impl.DoubanSpoiderServiceImpl;

/**  
 * @ClassName: RabbitTest  
 * @Description: 测试rabbitMq  
 * @author 文泊山  
 * @date 2018年2月23日    
 */
@Service
public class RabbitTest extends QuartzJobBean {
    private final String queue_name="SUPERMAN_QUEUE_A";
    //    @Autowired
    //    private DoubanSpiderService doubanSpiderService;

    /* (non-Javadoc)
     * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        DoubanSpiderService doubanSpiderService=applicationContext.getBean(DoubanSpoiderServiceImpl.class);
        doubanSpiderService.sendMqMessage(queue_name, "sssss"+new Date().toString());
    }

}
