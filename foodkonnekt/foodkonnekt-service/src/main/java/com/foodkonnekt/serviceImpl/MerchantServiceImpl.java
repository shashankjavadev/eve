package com.foodkonnekt.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemTax;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantKritiq;
import com.foodkonnekt.model.MerchantLogin;
import com.foodkonnekt.model.MerchantSubscription;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.SocialMediaLinks;
import com.foodkonnekt.model.Subscription;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.TimeZone;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.ConvenienceFeeRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantKritiqRepository;
import com.foodkonnekt.repository.MerchantLoginRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.MerchantSubscriptionRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.PickUpTimeRepository;
import com.foodkonnekt.repository.SocialMediaLinksRepository;
import com.foodkonnekt.repository.SubscriptionRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.TimeZoneRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private TaxRateRepository taxRateRepository;

	@Autowired
	private SocialMediaLinksRepository socialMediaLinksRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private TimeZoneRepository timeZoneRepository;

	@Autowired
	private OpeningClosingDayRepository openingClosingDayRepository;

	@Autowired
	private OpeningClosingTimeRepository openingClosingTimeRepository;

	@Autowired
	private PickUpTimeRepository pickUpTimeRepository;

	@Autowired
	private ConvenienceFeeRepository convenienceFeeRepository;

	@Autowired
	private MerchantSubscriptionRepository merchantSubscriptionRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MerchantLoginRepository merchantLoginRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private MerchantKritiqRepository merchantKritiqRepository;

	@Autowired
	private ItemmRepository itemmRepository;

	@Autowired
	private ItemTaxRepository itemTaxRepository;

	/**
	 * Find by posMerchantId
	 */
	public Merchant getMerchantByMerchantPsoId(String posMerchantId) {
		return merchantRepository.findByPosMerchantId(posMerchantId);
	}

	/**
	 * Find address by merchantId
	 */
	public List<Address> findAddressByMerchantId(Integer merchantId) {
		return addressRepository.findByMerchantId(merchantId);
	}

	/**
	 * Save and update merchant logo
	 */
	public Merchant addMerchantLogo(Merchant merchant) {
		Merchant resultMerchant = merchantRepository.findOne(merchant.getId());
		resultMerchant.setMerchantLogo(merchant.getMerchantLogo());
		merchantRepository.save(resultMerchant);
		return resultMerchant;
	}

	/**
	 * Find by merchant id
	 */
	public Merchant findByMerchantId(Integer merchantId) {
		return merchantRepository.findOne(merchantId);
	}

	/**
	 * find by opening closing id
	 */
	public List<OpeningClosingTime> findOpeningClosingHourByMerchantId(
			Integer merchantId) {
		List<OpeningClosingDay> openingClosingDays = openingClosingDayRepository
				.findByMerchantId(merchantId);
		List<OpeningClosingTime> openingClosingTimes = new ArrayList<OpeningClosingTime>();
		for (OpeningClosingDay day : openingClosingDays) {
			List<OpeningClosingTime> times = openingClosingTimeRepository
					.findByOpeningClosingDayId(day.getId());
			for (OpeningClosingTime openingClosingTime : times) {
				openingClosingTimes.add(openingClosingTime);
			}
		}
		return openingClosingTimes;
	}

	/**
	 * Find by merchantId
	 */
	public Integer findVendorIdByMerchantId(Integer merchantId) {
		return merchantRepository.findOwnerByMerchantId(merchantId);
	}

	/**
	 * Find by merchant id
	 */
	public Merchant findById(int merchantId) {
		return merchantRepository.findOne(merchantId);
	}

	/**
	 * Find by merchant posId
	 */
	public Merchant findbyPosId(String merchantId) {
		Merchant merchant = merchantRepository.findByPosMerchantId(merchantId);
		if (merchant != null && merchant.getTimeZone() == null) {
			List<Address> addresses = addressRepository
					.findByMerchantId(merchant.getId());
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				address.setMerchant(merchant);
				if (address != null && address.getZip() != null) {
					String timeZoneCode = DateUtil.getTimeZone(address);
					if (timeZoneCode != null) {
						TimeZone timeZone = timeZoneRepository
								.findByTimeZoneCode(timeZoneCode);
						merchant.setTimeZone(timeZone);
						merchantRepository.save(merchant);
					}
				}

			}
		}
		return merchant;
	}

	public Merchant findbyStoreId(String storeId) {
		return merchantRepository.findByStoreId(storeId);
	}

	/**
	 * Find openingClosingHours by merchantId
	 */
	public List<OpeningClosingDay> findOpeningClosingDayByMerchantId(
			Integer merchantId) {
		List<OpeningClosingDay> openingClosingDays = openingClosingDayRepository
				.findByMerchantId(merchantId);
		for (OpeningClosingDay day : openingClosingDays) {
			List<OpeningClosingTime> times = openingClosingTimeRepository
					.findByOpeningClosingDayId(day.getId());
			day.setTimes(times);
		}
		return openingClosingDays;
	}

	/**
	 * Find by merchantId
	 */
	public List<Merchant> findMerchantById(Integer merchantId) {
		List<Merchant> merchants = new ArrayList<Merchant>();
		Merchant merchant = merchantRepository.findOne(merchantId);
		List<Address> addresses = addressRepository
				.findByMerchantId(merchantId);
		merchant.setAddress(addresses.get(0).getAddress1() + " "
				+ addresses.get(0).getAddress2() + " "
				+ addresses.get(0).getAddress3() + " "
				+ addresses.get(0).getCity() + " "
				+ addresses.get(0).getState() + " "
				+ addresses.get(0).getCountry() + "-"
				+ addresses.get(0).getZip());
		merchants.add(merchant);
		return merchants;
	}

	/**
	 * Save merchant
	 */
	public Merchant save(Merchant merchant) {
		Vendor vendor = merchant.getOwner();
		if (vendor != null) {
			vendor = vendorRepository.save(vendor);
			merchant.setOwner(vendor);
		}

		Merchant result = merchantRepository.save(merchant);
		if (merchant.getAddresses() != null
				&& Hibernate.isInitialized(merchant.getAddresses())
				&& merchant.getAddresses().size() > 0) {
			Address address = merchant.getAddresses().get(0);
			address.setMerchant(merchant);
			if (address != null && address.getZip() != null) {
				String timeZoneCode = DateUtil.getTimeZone(address);
				if (timeZoneCode != null) {
					TimeZone timeZone = timeZoneRepository
							.findByTimeZoneCode(timeZoneCode);
					merchant.setTimeZone(timeZone);
					merchantRepository.save(merchant);
				}
			}
			addressRepository.save(address);
		}

		return merchant;
	}

	/**
	 * Save pickup time
	 */
	public void savePickupTime(PickUpTime pickUpTime) {
		try {
			if (pickUpTime.getPickUpTime() != null) {
				if (pickUpTime.getPickUpTime().isEmpty()) {
					pickUpTime.setPickUpTime(IConstant.DEFAULT_PICKUP_TIME);
				}
			} else {
				pickUpTime = new PickUpTime();
				pickUpTime.setPickUpTime(IConstant.DEFAULT_PICKUP_TIME);
			}
			pickUpTimeRepository.save(pickUpTime);
		} catch (Exception e) {
			if (e != null) {
				MailSendUtil.sendExceptionByMail(e);
			}
			e.printStackTrace();
		}
	}

	/**
	 * Save convenience fee
	 */
	public void saveConvenienceFee(ConvenienceFee convenienceFee,
			Merchant merchant) {
		try {
			if (convenienceFee.getConvenienceFee() != null) {
				if (convenienceFee.getConvenienceFee().isEmpty()) {
					convenienceFee.setConvenienceFee("0");
				}
				Double convenienceFees = Double.parseDouble(convenienceFee
						.getConvenienceFee());
				if (merchant.getOwner() != null
						&& merchant.getOwner().getPos() != null
						&& merchant.getOwner().getPos().getPosId() == 1) {
					if (convenienceFee.getId() == null
							|| convenienceFee.getConvenienceFeeLineItemPosId() == null) {
						String result = CloverUrlUtil.addDeliveryFeeLineItem(
								convenienceFees, merchant.getPosMerchantId(),
								merchant.getAccessToken(), "Convenience Fee",
								convenienceFee.getIsTaxable());
						JSONObject jObject = new JSONObject(result);
						convenienceFee.setConvenienceFeeLineItemPosId(jObject
								.getString("id"));
					} else {

						String result = CloverUrlUtil
								.updateDeliveryFeeLineItem(
										convenienceFees,
										merchant.getPosMerchantId(),
										merchant.getAccessToken(),
										"Convenience Fee",
										convenienceFee
												.getConvenienceFeeLineItemPosId(),
										convenienceFee.getIsTaxable());
						JSONObject jObject = new JSONObject(result);
						if (!jObject.toString().contains("Not Found")
								&& jObject.has("id"))
							convenienceFee
									.setConvenienceFeeLineItemPosId(jObject
											.getString("id"));
					}
				}
				convenienceFeeRepository.save(convenienceFee);
			}
		} catch (Exception e) {
			if (e != null) {
				MailSendUtil.sendExceptionByMail(e);
			}
			e.printStackTrace();
		}
	}

	public PickUpTime findPickupTime(Integer merchantId) {
		return pickUpTimeRepository.findByMerchantId(merchantId);
	}

	public List<Merchant> findAllMerchants() {
		// TODO Auto-generated method stub
		List<Merchant> merchants = merchantRepository.findAll();
		for (Merchant merchant : merchants) {
			List<OrderR> orderRs = orderRepository.findByMerchantId(merchant
					.getId());
			if (orderRs != null) {
				merchant.setTotalOrders(orderRs.size());
			} else {
				merchant.setTotalOrders(0);
			}

			if (merchant.getIsInstall() != null
					&& merchant.getIsInstall() == IConstant.BOOLEAN_TRUE) {
				merchant.setStatus("Installed");
			} else if (merchant.getIsInstall() != null
					&& merchant.getIsInstall() == IConstant.SOFT_DELETE) {
				merchant.setStatus("Un-installed");
			} else {
				merchant.setStatus("In Process");
			}
			List<MerchantSubscription> merchantSubscriptions = merchantSubscriptionRepository
					.findByMId(merchant.getId());
			merchant.setMerchantSubscriptions(merchantSubscriptions);

		}

		return merchants;
	}

	/* @Override */
	public MerchantLogin findByEmailAndPassword(String emailId, String password) {

		List<MerchantLogin> merchantLogins = merchantLoginRepository
				.findByEmailIdAndPassword(emailId, password);
		MerchantLogin merchantLogin = null;
		try {
			if (merchantLogins != null) {
				if (!merchantLogins.isEmpty()) {
					merchantLogin = merchantLogins.get(0);

				}
			}
		} catch (Exception e) {
			if (e != null) {
				MailSendUtil.sendExceptionByMail(e);
			}
			e.printStackTrace();
		}
		return merchantLogin;
	}

	/* @Override */
	public boolean findByEmailId(String emailId) {
		List<MerchantLogin> merchantLogins = merchantLoginRepository
				.findByEmailId(emailId);
		if (merchantLogins != null) {
			if (!merchantLogins.isEmpty()) {
				int status = 0;
				for (MerchantLogin merchantLogin : merchantLogins) {
					if (merchantLogin.getPassword() != null) {
						if (!merchantLogin.getPassword().isEmpty()) {
							status++;
						}
					}
				}
				if (status > 0) {
					MailSendUtil.forgotMerchantPasswordEmail(merchantLogins
							.get(0));
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/* @Override */
	public MerchantLogin findByMerchantEmailId(String emailId) {
		List<MerchantLogin> merchantLogins = merchantLoginRepository
				.findByEmailId(emailId);
		if (merchantLogins != null) {
			if (!merchantLogins.isEmpty()) {
				return merchantLogins.get(0);
			}
		}
		return null;
	}

	/* @Override */
	public MerchantLogin saveMerchantLogin(MerchantLogin merchantLogin) {
		if (merchantLogin != null)
			return merchantLoginRepository.save(merchantLogin);
		else
			return null;
	}

	/* @Override */
	public List<Merchant> findAllMerchantsByVendorId(Integer vendorId) {

		List<Merchant> merchants = merchantRepository
				.findByOwnerIdAndIsInstallNot(vendorId, IConstant.SOFT_DELETE);
		for (Merchant merchant : merchants) {
			List<OrderR> orderRs = orderRepository.findByMerchantId(merchant
					.getId());
			if (orderRs != null) {
				merchant.setTotalOrders(orderRs.size());
			} else {
				merchant.setTotalOrders(0);
			}
			Double totalValue = 0.0;
			for (OrderR orderR : orderRs) {
				Double orderPrice = orderR.getOrderPrice();
				if (orderPrice != null) {
					totalValue = totalValue + orderPrice;
				}
			}
			double roundOff = (double) Math.round(totalValue * 100) / 100;
			merchant.setTotalOrdersPrice(roundOff);
		}

		return merchants;
	}

	public SocialMediaLinks saveSocialMediaLinks(
			SocialMediaLinks socialMediaLinks) {
		// TODO Auto-generated method stub
		return socialMediaLinksRepository.save(socialMediaLinks);
	}

	public SocialMediaLinks getSocialMediaLinksByMerchantId(Integer merchantId) {
		// TODO Auto-generated method stub
		return socialMediaLinksRepository.findByMerchantId(merchantId);
	}

	public String generateLinkActiveCustomerFeedback(Integer merchantId) {
		// TODO Auto-generated method stub
		String merchId = merchantId.toString();
		String base64encode = EncryptionDecryptionUtil.encryption(merchId);
		String orderLink = UrlConstant.WEB_BASE_URL
				+ "/ofeedbackForm?merchantId=" + base64encode;
		System.out.println(orderLink);
		return orderLink;
	}

	public String getMerchantSubscription(Integer merchantId, String status) {
		// TODO Auto-generated method stub
		String orderLink = "";
		MerchantKritiq merchantKritiq = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (merchantId != null && status != null && !status.isEmpty()
				&& status != "") {
			List<MerchantSubscription> merchantSubscriptions = merchantSubscriptionRepository
					.findByMId(merchantId);
			Merchant merchant = merchantRepository.findById(merchantId);
			Subscription subscription = null;
			if (merchantSubscriptions != null
					&& merchantSubscriptions.size() > 0)
				subscription = subscriptionRepository
						.findOne(merchantSubscriptions.get(0).getSubscription()
								.getId());
			List<MerchantKritiq> kritiqs = merchantKritiqRepository
					.findByMerchantId(merchantId);
			boolean isFreeSubcription = false;
			if (kritiqs != null && !kritiqs.isEmpty()) {
				// for(MerchantKritiq ktitiq:kritiqs){
				MerchantKritiq ktitiq = kritiqs.get(kritiqs.size() - 1);
				if (ktitiq != null && ktitiq.getSubscrptionType() != null
						&& ktitiq.getSubscrptionType().equals("free")) {
					isFreeSubcription = true;
				} else {
					isFreeSubcription = false;
				}
				if (ktitiq != null && ktitiq.getActive() != null
						&& ktitiq.getActive() == true) {
					merchantKritiq = ktitiq;
				}
				// }
			}
			Date currentDate = new Date();
			if (status.equals("yes")) {

				if (subscription != null && subscription.getPrice() >= 49.9) {
					// MERCHANT IS PAID

					if (!kritiqs.isEmpty()) {
						// OLD PAID MERCHANT
						try {
							Date date1 = format.parse(kritiqs.get(
									kritiqs.size() - 1).getValidityDate());
							/* if (date1.compareTo(currentDate) >= 0) { */
							// validate date is greater then current date
							orderLink = this
									.generateLinkActiveCustomerFeedback(merchantId);
							merchantKritiq = new MerchantKritiq();
							merchant.setActiveCustomerFeedback(1);
							merchantKritiq.setMerchant(merchant);
							merchantKritiq.setCreateDate(kritiqs.get(
									kritiqs.size() - 1).getCreateDate());
							merchantKritiq
									.setUpdateDate(currentDate.toString());

							Calendar cal = Calendar.getInstance();
							cal.setTime(currentDate);
							cal.add(Calendar.DATE, 30);
							currentDate = cal.getTime();
							merchantKritiq.setValidityDate(DateUtil
									.getYYYYMMDD(currentDate));

							merchantKritiq.setActive(true);
							merchantKritiq.setSubscrptionType("paid");

							merchantRepository.save(merchant);

							merchantKritiqRepository.save(merchantKritiq);
							/*
							 * }else{
							 * 
							 * orderLink = "VALIDITY EXPAIRED "; return
							 * orderLink; }
							 */

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {

						// NEW PAID MERCHANT

						orderLink = this
								.generateLinkActiveCustomerFeedback(merchantId);
						merchantKritiq = new MerchantKritiq();
						merchant.setActiveCustomerFeedback(1);
						merchantKritiq.setMerchant(merchant);
						merchantKritiq.setCreateDate(DateUtil
								.getYYYYMMDD(currentDate));

						merchantKritiq.setUpdateDate(DateUtil
								.getYYYYMMDD(currentDate));

						Calendar cal = Calendar.getInstance();
						cal.setTime(currentDate);
						cal.add(Calendar.DATE, 30);
						currentDate = cal.getTime();
						merchantKritiq.setValidityDate(DateUtil
								.getYYYYMMDD(currentDate));

						merchantKritiq.setActive(true);
						merchantKritiq.setSubscrptionType("paid");

						merchantRepository.save(merchant);

						merchantKritiqRepository.save(merchantKritiq);

					}

					/*
					 * merchant.setActiveCustomerFeedback(1); orderLink =
					 * this.generateLinkActiveCustomerFeedback(merchantId);
					 * merchantKritiq.setMerchant(merchant);
					 * 
					 * merchantKritiq.setUpdateDate(currentDate.toString());
					 * Calendar cal = Calendar.getInstance();
					 * cal.setTime(currentDate); cal.add(Calendar.DATE, 30);
					 * currentDate = cal.getTime();
					 * merchantKritiq.setValidityDate(currentDate.toString());
					 * merchantKritiq.setActive(true);
					 * 
					 * 
					 * merchantRepository.save(merchant);
					 * merchantKritiqRepository.save(merchantKritiq);
					 * System.out.println(merchantKritiq.getValidityDate());
					 */
				} else {
					// MERCHANT IS FREE

					if (kritiqs != null && !kritiqs.isEmpty()
							&& kritiqs.size() > 0) {
						// OLD FREE MERCHANT
						try {
							Date date1 = DateUtil.convertStringToDate(kritiqs
									.get(kritiqs.size() - 1).getValidityDate());
							// currentDate=DateUtil.convertStringToDate(currentDate.toString());
							System.out.println(date1.compareTo(currentDate));
							if (isFreeSubcription
									&& date1.compareTo(currentDate) >= 0) {
								// validate date is greater then current date
								orderLink = this
										.generateLinkActiveCustomerFeedback(merchantId);
								merchantKritiq = new MerchantKritiq();
								merchant.setActiveCustomerFeedback(1);
								merchantKritiq.setMerchant(merchant);
								merchantKritiq.setCreateDate(DateUtil
										.getYYYYMMDD(currentDate));

								merchantKritiq.setUpdateDate(DateUtil
										.getYYYYMMDD(currentDate));

								Calendar cal = Calendar.getInstance();
								cal.setTime(currentDate);
								cal.add(Calendar.DATE, 30);
								currentDate = cal.getTime();
								merchantKritiq.setValidityDate(kritiqs.get(
										kritiqs.size() - 1).getValidityDate());

								merchantKritiq.setActive(true);
								merchantKritiq.setSubscrptionType("free");

								merchantRepository.save(merchant);

								merchantKritiqRepository.save(merchantKritiq);
							} else {

								orderLink = "Please upgrade your subscription to use Kritiq functionalities";
								return orderLink;
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {

						// NEW PAID MERCHANT
						merchantKritiq = new MerchantKritiq();
						orderLink = this
								.generateLinkActiveCustomerFeedback(merchantId);
						merchant.setActiveCustomerFeedback(1);
						merchantKritiq.setMerchant(merchant);
						merchantKritiq.setCreateDate(DateUtil
								.getYYYYMMDD(currentDate));

						merchantKritiq.setUpdateDate(DateUtil
								.getYYYYMMDD(currentDate));

						Calendar cal = Calendar.getInstance();
						cal.setTime(currentDate);
						cal.add(Calendar.DATE, 30);
						currentDate = cal.getTime();

						// Date currentDateFormated=
						// format.parse(currentDate.toString());
						merchantKritiq.setValidityDate(DateUtil
								.getYYYYMMDD(currentDate));

						merchantKritiq.setActive(true);
						merchantKritiq.setSubscrptionType("free");

						merchantRepository.save(merchant);

						merchantKritiqRepository.save(merchantKritiq);

					}

				}
			} else {
				// for NO CLICK
				if (!kritiqs.isEmpty()) {
					for (MerchantKritiq kritiqs2 : kritiqs) {
						if (kritiqs2.getActive()) {
							kritiqs2.setActive(false);
							kritiqs2.setUpdateDate(DateUtil
									.getYYYYMMDD(currentDate));
							merchantKritiqRepository.save(kritiqs2);
						}
					}

					merchant.setActiveCustomerFeedback(0);
					merchantRepository.save(merchant);
				}
			}

		}
		return orderLink;
	}

	public List<TaxRates> findAllTaxRatesByMerchantId(Integer merchantId) {

		return taxRateRepository.findByMerchantIdAndNameNot(merchantId,
				"NO_TAX_APPLIED");
	}

	public String addDefaultTaxByMerchantId(Integer merchantId) {
		String response = "failed";
		try {

			Merchant merchant = merchantRepository.findOne(merchantId);
			if (merchant != null) {
				List<Item> items = itemmRepository.findByMerchantId(merchantId);
				TaxRates rate = new TaxRates();
				rate.setIsDefault(IConstant.BOOLEAN_TRUE);
				rate.setMerchant(merchant);
				rate.setName("Default_Sales_Tax");
				rate.setRate(0.0);
				taxRateRepository.save(rate);
				for (Item item : items) {
					if (item.getIsDefaultTaxRates() == true) {

						ItemTax itemTax = new ItemTax();
						itemTax.setItem(item);
						itemTax.setTaxRates(rate);
						itemTaxRepository.save(itemTax);

					}

				}
				response = "success";
			}
		} catch (Exception e) {
			response = "failed";
		}
		return response;
	}

	public Vendor findVendorById(Integer intVendorId) {
		Vendor vendor = new Vendor();
		if(intVendorId!= null){
			vendor =  vendorRepository.findOne(intVendorId);
		}else{
			vendor = null;
		}
		return vendor;
	}

}
