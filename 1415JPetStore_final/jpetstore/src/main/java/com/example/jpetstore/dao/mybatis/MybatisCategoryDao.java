package com.example.jpetstore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.mybatis.mapper.CategoryMapper;
import com.example.jpetstore.domain.Category;

@Repository
public class MybatisCategoryDao implements CategoryDao {
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList() throws DataAccessException {
		return categoryMapper.getCategoryList();
	}

	public Category getCategory(String categoryId) throws DataAccessException {
		return categoryMapper.getCategory(categoryId);
	}

	/*BestList*/
	
	public List<Category> selectBestCategoryList(String categoryId) throws DataAccessException {
		System.out.println("selectBestCategoryList MybatisCategory");
		return categoryMapper.selectBestCategoryList(categoryId);
	}

	@Override
	public List<Category> selectBestList() throws DataAccessException {
		// TODO Auto-generated method stub
		return categoryMapper.selectBestList();
	}
	/*BestList*/
	
	//!!!!!!!!!!!!!!!!0622 myActivity수정추가  
	public List<Category> selectMyRecentList(String username) throws DataAccessException {
		System.out.println("selectMyRecentList MybatisCategory");
		return categoryMapper.selectMyRecentList(username);
	}
	public List<Category> selectMyLikeList(String username) throws DataAccessException {
		System.out.println("selectMyLikeList MybatisCategory");
		return categoryMapper.selectMyLikeList(username);
	}

	@Override
	public void deleteMyRecent(String itemId, String username) {
		// TODO Auto-generated method stub
		categoryMapper.deleteMyRecent(itemId, username);
	}
	@Override
	public void deleteMyLike(String itemId, String username) {
		// TODO Auto-generated method stub
		categoryMapper.deleteMyLike(itemId, username);
	}
	@Override
	public boolean selectIfLikeQ(String itemId, String username) {
		// TODO Auto-generated method stub
		System.out.println("selectIfLikeQ MybatisCategory:"+ categoryMapper.selectIfLikeQ(itemId, username));
		return categoryMapper.selectIfLikeQ(itemId, username);
	}
}
