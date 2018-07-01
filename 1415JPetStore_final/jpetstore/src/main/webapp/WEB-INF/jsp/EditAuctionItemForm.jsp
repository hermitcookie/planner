<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/editAuctionItemSubmitted.do" /></c:set>

<div align="center">
<form:form method="post" commandName="auctionItemForm" action="${targetUrl}">
<form:errors cssClass="error" /> <br><br>
<table class="n13">
	<tr>
      <td align="center" colspan="2"><font size="4">
        <b>Item #<c:out value="${editAuctionItem.itemId}" /></b></font> <br />
        <form:input path="item.itemId" value="${editAuctionItem.itemId}" type="hidden"/>
    </tr>
	<tr>
		<td colspan="2">
			<font color="green" size="4"><b>경매 상품 세부 정보</b></font>
		</td>
	</tr>
	<tr>
      <td>카테고리</td>
      <td>${editAuctionItem.product.categoryId}</td>
    </tr>
    <tr>
      <td>상품명</td>
      <td>${editAuctionItem.product.name}</td>
    </tr>

	<tr>
		<td>판매자: </td> <!-- item 객체의 sellerUsername field. read only. from session. -->
		<td>${userSession.account.username}</td>
	</tr>

<!-- item 객체의 listPrice field 
	<tr>
		<td>가격: </td>  
		<td><form:input path="item.listPrice" type="number" />
		    <form:errors path="item.listPrice" />
		</td>
	</tr>
-->
	<tr>
		<td>특징: </td> <!-- item 객체의 attribute1 field -->
		<td><form:input path="item.attribute1" value="${editAuctionItem.attribute1 }"/>
		    <form:errors path="item.attribute1" />
		</td>
	</tr>
	<!-- 
	<tr>
		<td>경매시작날짜: </td> 
		<td><form:input path="item.startDate" />
		    <form:errors path="item.startDate" />
		</td>
	</tr>
	 -->
	<tr>
		<td>경매종료날짜: </td> 
		<td>${editAuctionItem.endDate}</td>
	</tr>
	<tr>
		<td>경매시작가: </td> 
		<td>${editAuctionItem.startPrice}</td>
	</tr>
	<!--
	<tr>
		<td>즉시낙찰: </td> 
		<td>
			<label for="showY"><form:radiobutton path="item.winningBid"	value="Y" id="showY1" /> 예</label>
 			<label for="showN"><form:radiobutton path="item.winningBid" value="N" id="showN2"  /> 아니오</label>
		</td>
	</tr>
	-->
</table>
<p>
	<input type="image" src="../images/button_submit.gif">
</p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>

