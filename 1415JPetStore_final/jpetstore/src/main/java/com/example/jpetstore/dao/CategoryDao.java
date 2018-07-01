package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Category;

public interface CategoryDao {

	List<Category> getCategoryList() throws DataAccessException;

	Category getCategory(String categoryId) throws DataAccessException;
	
	
	List<Category> selectBestCategoryList(String categoryId) throws DataAccessException;
	List<Category> selectBestList() throws DataAccessException;
	
	//!!!!!!!!!!!!!!!!0622 myActivity수정추가  
	List<Category> selectMyRecentList(String username) throws DataAccessException;
	List<Category> selectMyLikeList(String username) throws DataAccessException;
	void deleteMyRecent(String itemId, String username);
	void deleteMyLike(String itemId, String username);
	boolean selectIfLikeQ(String itemId, String username);
}
