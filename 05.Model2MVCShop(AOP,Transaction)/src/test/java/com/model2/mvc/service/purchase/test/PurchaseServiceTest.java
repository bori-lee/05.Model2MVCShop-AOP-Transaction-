package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 *	FileName :  ProductServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" }) // 귀찮으면 이렇게,,
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		User user = new User();
		
		Product product = new Product();
		
		product.setProdNo(10004);
		purchase.setPurchaseProd(product);
		
		user.setUserId("user23");
		purchase.setBuyer(user);
		
		purchase.setPaymentOption("1");
		purchase.setReceiverName("이수아");
		purchase.setReceiverPhone("01080077546");
		purchase.setDivyAddr("없");
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);
		
		//product = productService.getProduct(10024);

		//==> console 확인
		System.out.println("구매" +purchase);
		
		//==> API 확인
		Assert.assertEquals("user23", purchase.getBuyer().getUserId());
//		Assert.assertEquals("1", purchase.getPaymentOption());
//		Assert.assertEquals("SCOTT", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("없옹", purchase.getDivyAddr());
//		Assert.assertEquals("1", purchase.getTranCode());
	
	} // end of testAddPurchase 
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		purchase = purchaseService.getPurchase(10136);

		//==> console 확인
		System.out.println(" 구매 : " +purchase);
		
		//==> API 확인
//		Assert.assertEquals(10004, purchase.getPurchaseProd().getProdNo());
//		Assert.assertEquals("이수아", purchase.getReceiverName());
//		Assert.assertEquals("20220407", product.getManuDate());
//		Assert.assertEquals(8000, product.getPrice());
//		Assert.assertEquals("없어", product.getFileName());

		//Assert.assertNotNull(productService.getProduct(10000));
		//Assert.assertNotNull(productService.getProduct(10001));
		
	}// end of testGetPurchase

	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10137);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("이수아", purchase.getReceiverName());
		Assert.assertEquals("01080077546", purchase.getReceiverPhone());
		//Assert.assertEquals("없", purchase.getDivyAddr());
		
		purchase.setReceiverName("예쁜이");
		purchase.setReceiverPhone("01080077545");
		purchase.setDivyAddr("없어");
	    purchase.setDivyRequest("도착시연락바람");
	   
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10137);
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
//		Assert.assertEquals("예쁜이", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("없어", purchase.getDivyAddr());
//		Assert.assertEquals("도착시연락바람", purchase.getDivyRequest());
	 }
	 
	//==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetPurchaseListAll() throws Exception {

         Search search = new Search();
         search.setCurrentPage(1);
         search.setPageSize(3);

         User user = new User();
         user.setUserId("user23");

         Map<String, Object> map = purchaseService.getPurchaseList(search, "user23");
         
         List<Object> list = (List<Object>) map.get("list");
         Assert.assertEquals(2, list.size());

         // ==> console 확인
         // System.out.println(list);

         Integer totalCount = (Integer) map.get("totalCount");
         System.out.println(totalCount);

         System.out.println("=======================================");

         search.setCurrentPage(1);
         search.setPageSize(3);
         map = purchaseService.getPurchaseList(search, "user23");
         

         list = (List<Object>) map.get("list");
         Assert.assertEquals(2, list.size());

         // ==> console 확인
         // System.out.println(list);

         totalCount = (Integer) map.get("totalCount");
         System.out.println(map);
      }
	 
	 
	//@Test
	 public void testUpdateTrancode() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10136);
		//Assert.assertNotNull(purchase);
		
		Assert.assertEquals("예쁜이", purchase.getReceiverName());
		//Assert.assertEquals("01080077545", purchase.getReceiverPhone());
		//Assert.assertEquals("없", purchase.getDivyAddr());
		
//		purchase.setTranCode("2");
//		purchase.setReceiverName("이수아");
//		purchase.setReceiverPhone("01043997545");
//		purchase.setDivyAddr("뭐야");
//	    purchase.setDivyRequest("연락부탁");
	   
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10136);
		//Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
//		Assert.assertEquals("예쁜이", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("없어", purchase.getDivyAddr());
//		Assert.assertEquals("도착시연락바람", purchase.getDivyRequest());
	 } 
	 
	 
	
//
//	
} // end of class