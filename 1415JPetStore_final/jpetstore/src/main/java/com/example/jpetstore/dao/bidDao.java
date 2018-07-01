package com.example.jpetstore.dao;
import java.util.List;

import org.springframework.dao.DataAccessException;
import com.example.jpetstore.domain.Bid;

public interface bidDao {
	void insertBid(Bid bid) throws DataAccessException;

	List<Bid> getMyBidItems(String username) throws DataAccessException;
}


