/*
 *    Copyright 2010-2013 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import com.example.jpetstore.domain.Category;

/**
 * @author Eduardo Macarron
 *
 */
public interface CategoryMapper {

	  List<Category> getCategoryList();

	  Category getCategory(String categoryId);

	  List<Category> selectBestCategoryList(String categoryId);
	  
	  List<Category> selectBestList(); 
	  
	//!!!!!!!!!!!!!!!!0622 myActivity수정추가  
	  List<Category> selectMyRecentList(String username);
	  List<Category> selectMyLikeList(String username);
	  void deleteMyRecent(String itemId, String username);
	  void deleteMyLike(String itemId, String username);
	  boolean selectIfLikeQ(String itemId, String username);

}
