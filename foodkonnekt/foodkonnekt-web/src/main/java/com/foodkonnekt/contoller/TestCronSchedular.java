package com.foodkonnekt.contoller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;

public class TestCronSchedular implements Job {
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		Integer activeCustomerFeedback = IConstant.BOOLEAN_TRUE;
		DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDD);
		DateFormat dateFormat1 = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
		java.util.Date date = new java.util.Date();
		
		String date1 = dateFormat.format(date).toString();
		System.out.println("date1----"+date1);
		String s1 = date1+IConstant.STARTDATE;
		String s2 = date1+IConstant.ENDDATE;
		
		
		java.util.Date startDate;
		try {
			startDate = dateFormat1.parse(s1);
			Date endDate = dateFormat1.parse(s2);
			 MailSendUtil.webhookMail("Test CronSchedular","Working at "+date);
			List<OrderR> order=CronScheduler.mermoryOrderRepository.findByStartDateAndEndDateAndMerchantActiveCustomerFeedback(startDate, endDate,activeCustomerFeedback);
			for(OrderR order1:order)
			{
				
			Integer customerId=	order1.getCustomer().getId();
		List<OrderR> orderList=	CronScheduler.mermoryOrderRepository.findByStartDateAndEndDateAndCustomerId(startDate, endDate, customerId);
				
				MailSendUtil.sendOrderReceiptAndFeedbackEmail(order1, orderList);
			}
			
			
			
			
			//code for Birthday and anniversary mail
			/*DateFormat monthFormat = new SimpleDateFormat("MMM");
			DateFormat dayFormat = new SimpleDateFormat("dd");
			
			
			Calendar calendar = Calendar.getInstance(); 
			
			System.out.println("cakl-"+calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			System.out.println("tomorrow date-"+calendar.getTime());
		//	Date dates = calendar.getTime();
			
			String month = monthFormat.format(calendar.getTime());
			String day = dayFormat.format(calendar.getTime());
			String wishDate = month+","+day;
			System.out.println(wishDate);
		
			List<Customer> birthdayList=CronScheduler.mermoryCustomerRepository.findByBirthDate(wishDate);
			List<Customer> anniversaryList=CronScheduler.mermoryCustomerRepository.findByAnniversaryDate(wishDate);
			System.out.println("brthdy-"+birthdayList.size());
			System.out.println("anvrsry-"+anniversaryList.size());
			for(Customer customerBirthday:birthdayList)
			{
				MailSendUtil.birthdayWishMail(wishDate, customerBirthday);
			}
			for(Customer customerAnniversary:anniversaryList)
			{
				MailSendUtil.anniversaryWishMail(wishDate, customerAnniversary);
			}
			*/
					
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	 }

}
