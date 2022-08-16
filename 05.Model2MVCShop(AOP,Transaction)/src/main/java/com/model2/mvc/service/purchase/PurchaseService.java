package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;


	public interface PurchaseService{
		
		public void  addPurchase(Purchase purchase) throws Exception; // ���Ÿ� ���� ����Ͻ� ����  
		
		public Purchase getPurchase(int tranNo)throws Exception;  // �������� ����ȸ�� ����   
		
		public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;  // ���Ÿ�� ���⸦ ���� 
		
		public void updatePurchase(Purchase purchase) throws Exception;  // ���� ���� ������ ���� 
		
		public void updateTranCode(Purchase purchase) throws Exception; // ���� �����ڵ� ������ ����

		
	}

