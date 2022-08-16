package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

//==> ���Ű������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface PurchaseDAO {
 
//���� ��� 
 public void addPurchase(Purchase purchase) throws Exception;
 
 //���� ����ȸ
 public Purchase getPurchase(int tranNo)throws Exception;
 
 //���� �̷� ��ȸ
 public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
 
 //���� ���� 
 public void updatePurchase(Purchase purchase) throws Exception;
 
// //���� Ʈ���ڵ� ���� 
 public void updateTranCode(Purchase purchase) throws Exception;
// 
 // �Խ��� Page ó���� ���� ��üRow(totalCount)  return
 public int getTotalCount(Search search) throws Exception ;
	
}