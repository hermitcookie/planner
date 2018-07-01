<!-- return new ModelAndView("ListBidItems", "bidItemList", petStore.getMyBidItems(username)); -->
<%@ include file="IncludeTop.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<script type="text/javascript">
</script>
<div align="center">
  <p>
    <font size="4"><b>My Bid Items</b></font>
  </p>
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>입찰ID</b></td>
      <td><b>입찰가</b></td>
      <td><b>시작가</b></td>
      <td><b>아이템ID</b></td>
      <td><b>마감일</b></td>
      <td><b>상태</b></td>
    </tr>
    <c:forEach var="bid" items="${bidItemList}" varStatus="status">
      <tr bgcolor="#FFFF88">
      	<td>
          <b>
          	<font color="black"><c:out value="${bid.bidId}" /></font>
          </b>
        </td>
        <td>
          <b>
          	<font color="black"><c:out value="${bid.bidPrice}" /></font>
          </b>
        </td>
        <td>
          <b>
          	<font color="black"><c:out value="${bid.item.startPrice}" /></font>
          </b>
        </td>
        <td>
          <b><a href='<c:url value="/shop/viewAuctionItem.do">
              <c:param name="itemId" value="${bid.item.itemId}"/></c:url>'>
              <font color="black"><c:out value="${bid.item.itemId}" /></font>
            </a></b>
        </td>
        <td>
          <b>
          	<font color="black"><c:out value="${bid.item.endDate}" /></font>
          </b>
        </td>
        <td>
          <b>
          	<font color="black"><c:out value="${bid.item.aucStatus}" /></font>
          </b>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
<%@ include file="IncludeBottom.jsp"%>
