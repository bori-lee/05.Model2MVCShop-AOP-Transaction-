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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data �� �پ��ϰ� Wiring ����...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" }) // �������� �̷���,,
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		purchase.setReceiverName("�̼���");
		purchase.setReceiverPhone("01080077546");
		purchase.setDivyAddr("��");
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);
		
		//product = productService.getProduct(10024);

		//==> console Ȯ��
		System.out.println("����" +purchase);
		
		//==> API Ȯ��
		Assert.assertEquals("user23", purchase.getBuyer().getUserId());
//		Assert.assertEquals("1", purchase.getPaymentOption());
//		Assert.assertEquals("SCOTT", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("����", purchase.getDivyAddr());
//		Assert.assertEquals("1", purchase.getTranCode());
	
	} // end of testAddPurchase 
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		purchase = purchaseService.getPurchase(10136);

		//==> console Ȯ��
		System.out.println(" ���� : " +purchase);
		
		//==> API Ȯ��
//		Assert.assertEquals(10004, purchase.getPurchaseProd().getProdNo());
//		Assert.assertEquals("�̼���", purchase.getReceiverName());
//		Assert.assertEquals("20220407", product.getManuDate());
//		Assert.assertEquals(8000, product.getPrice());
//		Assert.assertEquals("����", product.getFileName());

		//Assert.assertNotNull(productService.getProduct(10000));
		//Assert.assertNotNull(productService.getProduct(10001));
		
	}// end of testGetPurchase

	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10137);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("�̼���", purchase.getReceiverName());
		Assert.assertEquals("01080077546", purchase.getReceiverPhone());
		//Assert.assertEquals("��", purchase.getDivyAddr());
		
		purchase.setReceiverName("������");
		purchase.setReceiverPhone("01080077545");
		purchase.setDivyAddr("����");
	    purchase.setDivyRequest("�����ÿ����ٶ�");
	   
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10137);
		Assert.assertNotNull(purchase);
		
		//==> console Ȯ��
		System.out.println(purchase);
			
		//==> API Ȯ��
//		Assert.assertEquals("������", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("����", purchase.getDivyAddr());
//		Assert.assertEquals("�����ÿ����ٶ�", purchase.getDivyRequest());
	 }
	 
	//==>  �ּ��� Ǯ�� �����ϸ�....
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

         // ==> console Ȯ��
         // System.out.println(list);

         Integer totalCount = (Integer) map.get("totalCount");
         System.out.println(totalCount);

         System.out.println("=======================================");

         search.setCurrentPage(1);
         search.setPageSize(3);
         map = purchaseService.getPurchaseList(search, "user23");
         

         list = (List<Object>) map.get("list");
         Assert.assertEquals(2, list.size());

         // ==> console Ȯ��
         // System.out.println(list);

         totalCount = (Integer) map.get("totalCount");
         System.out.println(map);
      }
	 
	 
	//@Test
	 public void testUpdateTrancode() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10136);
		//Assert.assertNotNull(purchase);
		
		Assert.assertEquals("������", purchase.getReceiverName());
		//Assert.assertEquals("01080077545", purchase.getReceiverPhone());
		//Assert.assertEquals("��", purchase.getDivyAddr());
		
//		purchase.setTranCode("2");
//		purchase.setReceiverName("�̼���");
//		purchase.setReceiverPhone("01043997545");
//		purchase.setDivyAddr("����");
//	    purchase.setDivyRequest("������Ź");
	   
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10136);
		//Assert.assertNotNull(purchase);
		
		//==> console Ȯ��
		System.out.println(purchase);
			
		//==> API Ȯ��
//		Assert.assertEquals("������", purchase.getReceiverName());
//		Assert.assertEquals("01080077545", purchase.getReceiverPhone());
//		Assert.assertEquals("����", purchase.getDivyAddr());
//		Assert.assertEquals("�����ÿ����ٶ�", purchase.getDivyRequest());
	 } 
	 
	 
	
//
//	
} // end of class