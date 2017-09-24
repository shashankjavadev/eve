package com.foodkonnekt.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*import java.time.LocalDate;*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.ConvenienceFeeRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.PaymentModeRepository;
import com.foodkonnekt.repository.PickUpTimeRepository;
import com.foodkonnekt.repository.ZoneRepository;
import com.foodkonnekt.service.BusinessService;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.IConstant;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OpeningClosingDayRepository openingClosingDayRepository;

    @Autowired
    private OpeningClosingTimeRepository openingClosingTimeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ConvenienceFeeRepository convenienceFeeRepository;

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private PickUpTimeRepository pickUpTimeRepository;

    @Autowired
    private ZoneRepository zoneRepository;
    
    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * Find opening closing day and time by merchantId
     */
    public List<OpeningClosingDay> findBusinessHourByMerchantId(Integer merchantId) {
        List<OpeningClosingDay> openingClosingDays = openingClosingDayRepository.findByMerchantId(merchantId);
        for (OpeningClosingDay day : openingClosingDays) {
            List<OpeningClosingTime> times = openingClosingTimeRepository.findByOpeningClosingDayId(day.getId());
            for (OpeningClosingTime closingTime : times) {
                closingTime.setStartTime(DateUtil.get12Format(closingTime.getStartTime()));
                closingTime.setEndTime(DateUtil.get12Format(closingTime.getEndTime()));
            }
            day.setTimes(times);
        }
        return openingClosingDays;
    }

    /**
     * Find Location details by merchantId
     */
    public Address findLocationByMerchantId(Integer merchantId) {
        List<Address> addresses = addressRepository.findByMerchantId(merchantId);
        if (addresses != null) {
            if (!addresses.isEmpty()) {
                return addresses.get(0);
            }
        }
        return null;
    }
    
    public void saveDefaultBusinessHours(Merchant merchant){
    	String days[]={"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
    	for(String day:days){
    	OpeningClosingDay openingClosingDay = new OpeningClosingDay();
    	openingClosingDay.setIsHoliday(IConstant.BOOLEAN_FALSE);
    	openingClosingDay.setDay(day);
    	openingClosingDay.setMerchant(merchant);
        openingClosingDayRepository.save(openingClosingDay);

     OpeningClosingTime openingClosingTime = new OpeningClosingTime();
     openingClosingTime.setStartTime("09:00");
     openingClosingTime.setEndTime("18:00");
     openingClosingTime.setOpeningClosingDay(openingClosingDay);
        openingClosingTimeRepository.save(openingClosingTime);
    	}
    }
    
    public void saveDefaultPaymentMode(Merchant merchant){
    	PaymentMode paymentMode= new PaymentMode();
    	paymentMode.setLabel("Credit Card");
    	paymentMode.setAllowPaymentMode(IConstant.BOOLEAN_TRUE);
    	paymentMode.setMerchant(merchant);
    	paymentModeRepository.save(paymentMode);
    	PaymentMode paymentMode1= new PaymentMode();
    	paymentMode1.setLabel("Cash");
    	paymentMode1.setAllowPaymentMode(IConstant.BOOLEAN_FALSE);
    	paymentMode1.setMerchant(merchant);
    	paymentModeRepository.save(paymentMode1);
    }

    public ConvenienceFee findConvenienceFeeByMerchantId(Integer merchantId) {
        return convenienceFeeRepository.findByMerchantId(merchantId);
    }

    public PaymentMode findByMerchantIdAndLabel(Integer merchantId, String label) {
        return paymentModeRepository.findByMerchantIdAndLabel(merchantId, label);
    }

    public void updateBusinessHour(String startTime, String endTime, String selectedDay) {

        String startT[] = startTime.split(",");
        String endT[] = endTime.split(",");
        for (int i = 0; i < startT.length; i++) {
            String startIndex = startT[i];
            String sArray[] = startIndex.split("_");
            int dayId = Integer.valueOf(sArray[0]);
            List<OpeningClosingTime> openingClosingTimes = openingClosingTimeRepository
                            .findByOpeningClosingDayId(dayId);
            if (openingClosingTimes != null) {
                if (!openingClosingTimes.isEmpty()) {
                    for (OpeningClosingTime closingTime : openingClosingTimes) {
                        openingClosingTimeRepository.delete(closingTime.getId());
                    }
                }
            }
        }

        List<OpeningClosingTime> closingTimes = new ArrayList<OpeningClosingTime>();
        for (int i = 0; i < startT.length; i++) {
            OpeningClosingTime closingTime = new OpeningClosingTime();
            String startIndex = startT[i];
            String sArray[] = startIndex.split("_");
            int dayId = Integer.valueOf(sArray[0]);
            OpeningClosingDay openingClosingDay = openingClosingDayRepository.findOne(dayId);
            openingClosingDay.setIsHoliday(0);
            openingClosingDayRepository.save(openingClosingDay);
            openingClosingDay.setMerchant(null);
            String startTim = sArray[1];
            String endTm = endT[i];
            closingTime.setStartTime(DateUtil.getTwentyFourFormat(startTim));
            closingTime.setEndTime(DateUtil.getTwentyFourFormat(endTm));
            closingTime.setOpeningClosingDay(openingClosingDay);
            closingTimes.add(closingTime);
        }
        openingClosingTimeRepository.save(closingTimes);

        String days[] = selectedDay.split(",");
        for (int i = 0; i < days.length; i++) {
            String dayArray = days[i];
            String dArray[] = dayArray.split(":");
            int dayId = Integer.valueOf(dArray[0]);
            String status = dArray[1];
            OpeningClosingDay openingClosingDay = openingClosingDayRepository.findOne(dayId);
            if (status.equals("close")) {
                openingClosingDay.setIsHoliday(1);
                openingClosingDayRepository.save(openingClosingDay);
                List<OpeningClosingTime> openingClosingTimes = openingClosingTimeRepository
                                .findByOpeningClosingDayId(dayId);
                if (openingClosingTimes != null) {
                    if (!openingClosingTimes.isEmpty()) {
                        for (OpeningClosingTime closingTime : openingClosingTimes) {
                            openingClosingTimeRepository.delete(closingTime.getId());
                        }
                    }
                }
            }
        }
    }

    public void savePaymentMode(PaymentMode mode) {
        paymentModeRepository.save(mode);

    }

    public PickUpTime findPickUpTimeByMerchantId(Integer merchantId) {
        PickUpTime pickUpTime = pickUpTimeRepository.findByMerchantId(merchantId);
        if (pickUpTime == null) {
            pickUpTime = new PickUpTime();
            pickUpTime.setPickUpTime("0");
        }
        return pickUpTime;
    }

    /**
     * Find opening hour for future orders
     */
    public List<String> findFutureOrderOpeningHours(String futureDate, Integer merchantId, String orderType) {
        if(futureDate!=null && !futureDate.equals("select")){
    	Merchant merchant= merchantRepository.findById(merchantId);
    	Integer hourDifference=0;
    	Integer minutDifference=0;
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getHourDifference()!=null){
    		hourDifference=merchant.getTimeZone().getHourDifference();
    		if(merchant.getTimeZone().getMinutDifference()!=null)
    			minutDifference=merchant.getTimeZone().getMinutDifference();
    	}
        	String pickOrDeliverytime = "30";
        if ("Pickup".equals(orderType)) {
            PickUpTime pickUpTimes = pickUpTimeRepository.findByMerchantId(merchantId);
            if (pickUpTimes != null) {
                if (pickUpTimes.getPickUpTime() != null) {
                    pickOrDeliverytime = pickUpTimes.getPickUpTime();
                }
            }
        } else {
            /*List<Zone> zones = zoneRepository.findByMerchantId(merchantId);
            if (!zones.isEmpty()) {
                pickOrDeliverytime = zones.get(0).getAvgDeliveryTime();
            }*/
        	String zones = zoneRepository.findMaxDeliveryAvgTimeByMerchantId(merchantId);
            if (zones != null) {
            	 System.out.println("maximum avg"+zones);
                pickOrDeliverytime = zones;
               
            }
        }
        OpeningClosingDay openingClosingDay = openingClosingDayRepository.findByDayAndMerchantId(
                        DateUtil.findDayNameFromDate(futureDate), merchantId);
        List<OpeningClosingTime> openingClosingTimes = openingClosingTimeRepository
                        .findByOpeningClosingDayId(openingClosingDay.getId());
        
        String currentTimeArray1[]=DateUtil.findCurrentTime().split(":");
        int current_hour=Integer.parseInt(currentTimeArray1[0]);
        
        Date date= new Date();
    	SimpleDateFormat dateForma=  new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	
    	
    	String futureDay=DateUtil.findDayNameFromDate(futureDate);
    	
        List<java.sql.Time> intervals = new ArrayList<java.sql.Time>();
        for (OpeningClosingTime closingTime : openingClosingTimes) {
        	String opeingArray[] = closingTime.getStartTime().split(":");
        	
            
            String endtime = "";
            if ("00:00".equals(closingTime.getEndTime())) {
                endtime = "24:00";
            } else {
                endtime = closingTime.getEndTime();
            }
            String closingArray[] = endtime.split(":");
            @SuppressWarnings("deprecation")
            java.sql.Time startTime = new java.sql.Time(Integer.parseInt(opeingArray[0]),
                            Integer.parseInt(opeingArray[1]), 0);
            
            
            
            @SuppressWarnings("deprecation")
            java.sql.Time endTime = new java.sql.Time(Integer.parseInt(closingArray[0]),
                            Integer.parseInt(closingArray[1]), 0);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endTime);
            String currentDate=dateForma.format(date);
        	
        	LocalDate start = LocalDate.parse(dateForma.format(date));
        	if(current_hour+hourDifference<0)
            	{
            		start = LocalDate.parse(dateForma.format(date));
            		start= start.minusDays(1);
        		currentDate = start .toString( );
        		cal.add(Calendar.HOUR, -24);
        		endCal.add(Calendar.HOUR, -24);
            	}
        	String currentDay=DateUtil.findDayNameFromDate(currentDate);
            System.out.println("Start time->"+cal.getTime());
            
            String currentTimeArray[]=DateUtil.findCurrentTime().split(":");
       	 @SuppressWarnings("deprecation")
       	 java.sql.Time currentTime = new java.sql.Time(Integer.parseInt(currentTimeArray[0]),
                    Integer.parseInt(currentTimeArray[1]), 0);
       	
       	 Calendar currentCal = Calendar.getInstance();
       	 currentCal.setTime(currentTime);
       	 currentCal.add(Calendar.HOUR, hourDifference);
       	 currentCal.add(Calendar.MINUTE, minutDifference);
       	
    	 //currentDate=dateForma.format(currentCal.getTime());
    	 //currentDay=DateUtil.findDayNameFromDate(currentDate);
       	System.out.println(dateForma.format(currentCal.getTime()));
       	
       	System.out.println("Current time->"+currentCal.getTime());
       	System.out.println("Start time->"+cal.getTime());
             if(currentDay.equals(futureDay)){
        		
            	 
            	 
            	 if(!cal.getTime().after(currentCal.getTime())){
            		// startTime=currentTime;
            		 cal.setTime(currentCal.getTime());
            		 int unroundedMinutes = cal.get(Calendar.MINUTE);
            		 int mod = unroundedMinutes % 15;
            		 cal.add(Calendar.MINUTE, mod < 8 ? -mod : (15-mod));
            	 }
            	 
        	}
             if (!pickOrDeliverytime.isEmpty()) {
                 cal.add(Calendar.MINUTE, Integer.parseInt(pickOrDeliverytime));
             }else{
            	 cal.add(Calendar.MINUTE, 30);
             }
             
            if(!cal.getTime().after(endCal.getTime()) && !cal.getTime().equals(endCal.getTime())){
            
                intervals.add(new java.sql.Time(cal.getTimeInMillis()));
            
            while (cal.getTime().before(endCal.getTime())) {
                cal.add(Calendar.MINUTE, 15);
                if (cal.getTime().before(endCal.getTime())) {
                    intervals.add(new java.sql.Time(cal.getTimeInMillis()));
                }
            }
            
            intervals.add(endTime);
            }
            
        }
        
        List<String> times = new ArrayList<String>();
        for (java.sql.Time time : intervals) {
            times.add(DateUtil.get12Format(time.toString()));
        }
        return times;
        }else{
        	return null;
        }
    }
    
    
    public List<String> findFutureDates(Merchant merchant) {
        
    	//Merchant merchant= merchantRepository.findById(merchantId);
    	Integer hourDifference=0;
    	Integer minutDifference=0;
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getHourDifference()!=null){
    		hourDifference=merchant.getTimeZone().getHourDifference();
    		if(merchant.getTimeZone().getMinutDifference()!=null)
    			minutDifference=merchant.getTimeZone().getMinutDifference();
    	}
    	List<OpeningClosingDay> openingClosingDays = openingClosingDayRepository.findByMerchantIdAndIsHolidayNot(merchant.getId(),IConstant.BOOLEAN_TRUE);
    	if(openingClosingDays!=null && openingClosingDays.size()>0){
    	String days="";
    	
    	for(OpeningClosingDay day:openingClosingDays){
    		days=days+", "+day.getDay();
    	}
    		
    	String currentTimeArray[]=DateUtil.findCurrentTime().split(":");
   	 @SuppressWarnings("deprecation")
//   	 java.sql.Time currentTime = new java.sql.Time(Integer.parseInt(currentTimeArray[0]),
//                Integer.parseInt(currentTimeArray[1]), 0);
//   	 Calendar currentCal = Calendar.getInstance();
//   	 currentCal.setTime(currentTime);
   	int a	=Integer.parseInt(currentTimeArray[0]);
    	
    	Date date= new Date();
    	SimpleDateFormat dateForma=  new SimpleDateFormat("yyyy-MM-dd");
    	dateForma.format(date);
    	LocalDate start = LocalDate.parse(dateForma.format(date));
    	if(a+hourDifference<0)
    	{
    		start = LocalDate.parse(dateForma.format(date));
    		start= start.minusDays(1);
    	}else{
    		 start = LocalDate.parse(dateForma.format(date));
    	}
    	 
    	 /*String dateStart=start.toString();
    		
    	 System.out.println(start.plusDays(1));*/
    	 LocalDate nextDate=start;
    	 
    	 List<String> dates = new ArrayList<String>();
    	 int futureDaysAhead=10;
    	 if(merchant.getFutureDaysAhead()!=null && merchant.getFutureDaysAhead()>0){
    		 futureDaysAhead=merchant.getFutureDaysAhead();
    	 }
    	 int i=1;
        while(i<=futureDaysAhead){
        	String dateStart=nextDate.toString();
        	String dayName=DateUtil.findDayNameFromDate(dateStart);
        	if(days.contains(dayName)){
        		dates.add(dateStart);
        	    i++;
        	}
        	nextDate= nextDate.plusDays(1);
        	
        }
        
       
        
        return dates;
    	}else{
    		return null;
    	}
    }
}
