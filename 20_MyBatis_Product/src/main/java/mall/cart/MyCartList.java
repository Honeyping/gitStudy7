package mall.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyCartList { // ��ٱ��� class
	
	private Map<Integer,Integer> orderlists = null; // Map<��� ��ǰ, �>
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
		
	}
	
	public void addOrder(int pnum, int oqty) {
		
		if(orderlists.containsKey(pnum) == false) {
			orderlists.put(pnum, oqty);
			
		}else {
			int qty = orderlists.get(pnum);
			orderlists.put(pnum,oqty + qty);
		}
		
		Set<Integer> key_set = orderlists.keySet();
		System.out.println("key_set"+key_set); // ��ǰ��ȣ�� ������
		
		for(int key : key_set) {
			System.out.println(key+","+orderlists.get(key));
		}
		
	}

	public Map<Integer, Integer> getAllOrderLists() {
		
		return orderlists;
	}

}
