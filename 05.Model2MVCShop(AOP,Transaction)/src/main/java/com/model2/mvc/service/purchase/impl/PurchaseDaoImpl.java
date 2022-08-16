// ���̹�Ƽ������ mapper��� �˾Ƽ� �����ؼ� ��������. mapper.xml�� ���� ����. 
package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;


//==> ���Ű��� DAO CRUD ����
@Repository("purchaseDaoImpl") //Persistence Layer(���������� �� ����)�� 1:1 ��Ī�� �����ϴ�.
public class PurchaseDaoImpl implements PurchaseDAO{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	// ���� ����X 
	///Method
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

   public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
      
      Map<String, Object> map = new HashMap<String, Object>();
      Purchase purchase = new Purchase();
      
      map.put("endRowNum",  search.getEndRowNum()+"" );
      map.put("startRowNum",  search.getStartRowNum()+"" );
      map.put("userId",userId);
      
      List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
      map.put("list", list);
      System.out.println("@@@@@@@@@@@@@@@@"+map);
      return map;
   }
   
   
   public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	   
   
   
   
   
	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}
	
} // end of class