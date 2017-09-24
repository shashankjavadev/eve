package com.foodkonnekt.contoller;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.util.MailSendUtil;

@Component
@Controller
public class CronScheduler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemModifierRepository itemModifierRepository;

    @Autowired
    private MerchantRepository merchantRepository;
    
    @Autowired
    private CustomerrRepository customerRepository ;
  

    public static OrderRepository mermoryOrderRepository;
    public static OrderItemRepository memoryOrderItemRepository;
    public static OrderItemModifierRepository memoryItemModifierRepository;
    public static MerchantRepository memorymerchantRepository;
    //public static MailSendUtil memoryMailSendUtil;
    public static CustomerrRepository mermoryCustomerRepository;

   /* @PostConstruct
    public void updateLatLong() throws Exception {
        mermoryOrderRepository = orderRepository;
        memoryOrderItemRepository = orderItemRepository;
        memoryItemModifierRepository = itemModifierRepository;
        memorymerchantRepository = merchantRepository;

        JobKey jobKeyA = new JobKey("futureOrder", "group1");
        JobDetail futureOrder = JobBuilder.newJob(FutureOrder.class).withIdentity(jobKeyA).build();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName1", "group1")
                        .withSchedule(CronScheduleBuilder.cronSchedule("2 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(futureOrder, trigger1);

    }

    
    @SuppressWarnings("restriction")
	@PostConstruct
    public void sendMailMerchant() throws Exception {
    	
        mermoryOrderRepository = orderRepository;
        memoryOrderItemRepository = orderItemRepository;
        memoryItemModifierRepository = itemModifierRepository;
        memorymerchantRepository = merchantRepository;
        JobKey jobKeyA = new JobKey("futureOrder", "group2");
        JobDetail futureOrder = JobBuilder.newJob(TestCronSchedular.class).withIdentity(jobKeyA).build();
        // Trigger will fire on 23:59:59 PM daily
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group2")
                        .withSchedule(CronScheduleBuilder.cronSchedule("00 59 23 * * ?")).build();   
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("00 59 23 * * ?")).build();   
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(futureOrder, trigger1);
    }
    */
    @SuppressWarnings("restriction")
   	@PostConstruct
       public void sendMailMerchant() throws Exception {
       	
           mermoryOrderRepository = orderRepository;
           memoryOrderItemRepository = orderItemRepository;
           memoryItemModifierRepository = itemModifierRepository;
           memorymerchantRepository = merchantRepository;
           mermoryCustomerRepository = customerRepository;
           JobKey jobKeyA = new JobKey("futureOrder", "group2");
           JobDetail futureOrder = JobBuilder.newJob(TestCronSchedular.class).withIdentity(jobKeyA).build();
           // Trigger will fire on 23:59:59 PM daily
           Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group2")
                           .withSchedule(CronScheduleBuilder.cronSchedule("00 59 23 * * ?")).build();   
          /* Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group2")
                   .withSchedule(CronScheduleBuilder.cronSchedule("10 * * * * ?")).build();   */
           
           Scheduler scheduler = new StdSchedulerFactory().getScheduler();
           scheduler.start();
           scheduler.scheduleJob(futureOrder, trigger1);
       }
    
    
}
