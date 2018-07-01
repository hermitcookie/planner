package com.example.jpetstore.dao.mybatis.mapper;
import java.util.List;

import com.example.jpetstore.domain.Bid;

public interface BidMapper {
	
	public void insertBid(Bid bid);

	public List<Bid> getMyBidItems(String username);
	
	public void inputHighest(Bid bid);
}
