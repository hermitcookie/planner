package com.example.jpetstore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.BlackListDao;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.dao.bidDao;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Bid;
import com.example.jpetstore.domain.BlackList;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;

/**
 * JPetStore primary business object.
 * 
 * <p>This object makes use of five DAO objects, decoupling it
 * from the details of working with persistence APIs. So
 * although this application uses iBATIS for data access,
 * a different persistence tool could be dropped in without
 * breaking this class.
 *
 * <p>The DAOs are made available to the instance of this object
 * using Dependency Injection. (The DAOs are in turn configured using
 * Dependency Injection themselves.) We use Setter Injection here,
 * exposing JavaBean setter methods for each DAO. This means there is
 * a JavaBean property for each DAO. In the present case, the properties
 * are write-only: there are no corresponding getter methods. Getter
 * methods for configuration properties are optional: Implement them
 * only if you want to expose those properties to other business objects.
 *
 * <p>There is one instance of this class in the JPetStore application.
 * In Spring terminology, it is a "singleton", referring to a
 * per-Application Context singleton. The factory creates a single
 * instance; there is no need for a private constructor, static
 * factory method etc as in the traditional implementation of
 * the Singleton Design Pattern. 
 *
 * <p>This is a POJO. It does not depend on any Spring APIs.
 * It's usable outside a Spring container, and can be instantiated
 * using new in a JUnit test. However, we can still apply declarative
 * transaction management to it using Spring AOP.
 *
 * <p>This class defines a default transaction annotation for all methods.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified by Changsup Park
 */
@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade { 
	@Autowired	// 
	@Qualifier("mybatisAccountDao")
	private AccountDao accountDao;
	
	@Autowired		// applicationCntext.xml에 정의된 scheduler 객체를 주입 받음
	private ThreadPoolTaskScheduler scheduler;
	
	
	@Autowired  
	//@Qualifier("jdbcTemplateCategoryDao") // �뜝�떎�뙋�삕  
	// @Qualifier("namedParameterJdbcTemplateCategoryDao")  // �뜝�떎�뙋�삕 
	// @Qualifier("jdbcDaoSupportCategoryDao")  // �뜝�떎�뙋�삕
	// @Qualifier("PureJdbcCategoryDao")
	@Qualifier("mybatisCategoryDao")
	private CategoryDao categoryDao;
	
	@Autowired  // 
	@Qualifier("mybatisProductDao")
	private ProductDao productDao;
	
	@Autowired	// 
	@Qualifier("mybatisItemDao")
	private ItemDao itemDao;
	
	@Autowired	// 
	@Qualifier("mybatisOrderDao")
	private OrderDao orderDao;
	
	@Autowired	// 
	@Qualifier("mybatisBidDao")
	private bidDao bidDao;
	
	@Autowired
	private BlackListDao BlackListDao;

	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

	

	public List<BlackList> getBlackList() {
		return BlackListDao.getBlackList();
	}
	
	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return accountDao.getUsernameList();
	}

	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return itemDao.getItem(itemId);
	}
	public Item getAuctionItem(String itemId) {//0624슈종
		return itemDao.getAuctionItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemDao.isItemInStock(itemId);
	}

	//insertOrder 占쎈뼄占쎈뻬 餓ο옙 占쎌궎�몴占� 占쎄문疫꿸퀡�늺 占쎌읈占쎌벥 占쎌삂占쎈씜占쎈즲 rollback 占쎈┷占쎈선占쎈튊占쎈맙
	//alter table inventory add constraint c1 check(qty >= 0) 占쎈�믭옙�뵠�뇡遺용퓠 占쎌뵠 占쎌젫占쎈튋鈺곌퀗援� �빊遺쏙옙占쎈퉸占쎈튊占쎈맙 
	//@Transactional 嚥∽옙 占쎄퐨占쎈섧占쎌읅 Transcation �꽴占썹뵳占� (insertOrder占쎈뮉 2揶쏆뮆占쏙옙 占쎈릭占쎄돌嚥∽옙 �눧�씈堉깍옙苑�..)
	public void insertOrder(Order order) {
		int point = (int) (order.getTotalPrice()*0.1);
		accountDao.updatePoint(order.getUsername(), point);
		order.setPoint(point);
		itemDao.updateQuantity(order);	    
		orderDao.insertOrder(order);
	}
	
	//Added method for showing list of my selling items
	public List<Item> getSellingItemListBySellerUsername(String username) {
		return itemDao.getSellingItemListBySellerUsername(username);
	}
	
	//0624 수정
	public List<Item> getSellingItemList() {
		return itemDao.getSellingItemList();
	}
	
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderDao.getOrdersByUsername(username);
	}
	
	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.insertItem(item);
		itemDao.updateQuantityForInsertItem(item);
	}
	@Override
	public void auctionInsertItem(Item item) {
		// TODO Auto-generated method stub
		System.out.println("auctionInsertItem PETSTOREIMPL");
		itemDao.insertItem(item);
		itemDao.insertAuctionItem(item);
		itemDao.updateQuantityForInsertItem(item);
		
	}
	
	@Override
	public void deleteItem(String itemId) {
		// TODO Auto-generated method stub
		itemDao.deleteItemMyActivity(itemId);
		itemDao.deleteItemInventory(itemId);
		itemDao.deleteItem(itemId);
	}
	//0624 수정
	@Override
	public void deleteAuctionItem(String itemId) {
		// TODO Auto-generated method stub
		System.out.println("deleteAuctionItem PetStoreImpl");
		itemDao.deleteAuctionItem(itemId);
		itemDao.deleteItemMyActivity(itemId);
		itemDao.deleteItemInventory(itemId);
		itemDao.deleteItem(itemId);
		
	}
	
	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.updateItem(item);
	}

	public void updatePoint(String username, int point) {
		accountDao.updatePoint(username, point);
	}
	
	public int getUserPoint(Account account) {
		return accountDao.getUserPoint(account);
	}
	
	//u
	@Override
	public void updateCountView(String itemId, String username) {
		// TODO Auto-generated method stub
		System.out.println("updateCountView PETsTROEiMPL");
		itemDao.updateCountView(itemId);
		System.out.println("itemDaoselectIfRecent:"+itemDao.selectIfRecent(itemId, username));
		boolean b = itemDao.selectIfRecent(itemId, username);
		if( b == false){//중복값이 없다면
			System.out.println("중복값 없음");
			itemDao.insertCountActivity(itemId, username);
		}
	}
	@Override
	public void updateCountView(String itemId) {
		// TODO Auto-generated method stub
		itemDao.updateCountView(itemId);	
	}
	
	@Override
	public void updateCountLike(String itemId, String username) {
		// TODO Auto-generated method stub
//0622 추가 수정 이미 myactivity 에 있을 경우 저장하지 않는다.		
		//if(itemDao.selectIfRecent(itemId, username));
		System.out.println("11");
		 
		System.out.println("itemDaoselectIfLike:"+itemDao.selectIfLike(itemId, username));
		
		boolean b = itemDao.selectIfLike(itemId, username);
		if( b == false){//중복값이 없다면
			System.out.println("중복값 없음");
			itemDao.updateCountLike(itemId);
			itemDao.insertCountLikeActivity(itemId, username);
		}
		
	}
	
	@Override
	public List<Category> selectBestCategoryList(String categoryId) {
		// TODO Auto-generated method stub
		System.out.println("petStoreImple categorylist 1�ܰ�");
		return categoryDao.selectBestCategoryList(categoryId);
	}
	
	@Override
	public List<Category> selectBestList() {
		// TODO Auto-generated method stub
		System.out.println("petStoreImple selectBestList 1�ܰ�");
		return categoryDao.selectBestList();
	}

	//!!!!!!!!!!!!!!!!0622 myActivity수정추가  
	@Override
	public List<Category> selectMyRecentList(String username) {
		// TODO Auto-generated method stub
		System.out.println("petStoreImple selectMyRecentList");
		return categoryDao.selectMyRecentList(username);
	}
	@Override
	public List<Category> selectMyLikeList(String username) {
		// TODO Auto-generated method stub
		System.out.println("petStoreImple selectMyLikeList");
		return categoryDao.selectMyLikeList(username);
	} 
	
	public void deleteMyRecent(String itemId, String username) {
		categoryDao.deleteMyRecent(itemId, username);
	}
	public void deleteMyLike(String itemId, String username) {
		categoryDao.deleteMyLike(itemId, username);
	} 
	public boolean selectIfLikeQ(String itemId, String username){
		return categoryDao.selectIfLikeQ(itemId, username);
	}
	
	@Override
	public List<Item> getSellingAuctionItemListBySellerUsername(String username) {
		// TODO Auto-generated method stub
		return itemDao.getSellingAuctionItemListBySellerUsername(username);
	}

	@Override
	public void insertBid(Bid bid) {
		// TODO Auto-generated method stub
		bidDao.insertBid(bid);
	}

	@Override
	public List<Bid> getMyBidItems(String username) {
		// TODO Auto-generated method stub
		return bidDao.getMyBidItems(username);
	}
	
	
	public void AuctionScheduler(Date closingTime) {
		Runnable updateTableRunner = new Runnable() {
		//anonymous class 정의
		@Override
		public void run() {// 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의	
			//실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경. 즉 curTime은 실행시점의 시간임.
			Date curTime = new Date();

			//만료된 옥션의 아이템아이디 목록을 불러옴 
			List<String> listOfItemId = new ArrayList<String>();
			listOfItemId = itemDao.getAuctionItemIdAtClose(curTime);
			
			for(int i = 0; i < listOfItemId.size(); i++) {
				// highestUser찾는다. 
				String highestUser = itemDao.findHighestUser(listOfItemId.get(i));
				
				//낙찰시킴. (insertOrder이용) 
				//orderDao.insertOrder(order);
//				System.out.println(listOfItemId.get(i) + "의 낙찰자는" + highestUser+ "\n");
				//그리고 이렇게 옥션을 닫음 
				itemDao.closeAuction(curTime);
			}
			System.out.println("updateTableRunner is excuted" + curTime);
			}
		};
		
		//스케줄생성: closingTime에 updateTableRunner.run() 메소드 실행
		scheduler.schedule(updateTableRunner, closingTime);
		System.out.println("updateTaleRunner has been scheduled to execute at " + closingTime);
	}


	 
	
}