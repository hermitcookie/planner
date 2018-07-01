package com.example.jpetstore.dao.mybatis;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.example.jpetstore.dao.bidDao;
import com.example.jpetstore.dao.mybatis.mapper.BidMapper;
import com.example.jpetstore.domain.Bid;

@Repository
public class MybatisBidDao implements bidDao{

	@Autowired
	private BidMapper bidMapper;
	
	public void insertBid(Bid bid) {
		bidMapper.insertBid(bid);
		bidMapper.inputHighest(bid);
	}

	@Override
	public List<Bid> getMyBidItems(String username) throws DataAccessException {
		// TODO Auto-generated method stub
		return bidMapper.getMyBidItems(username);
	}
}
