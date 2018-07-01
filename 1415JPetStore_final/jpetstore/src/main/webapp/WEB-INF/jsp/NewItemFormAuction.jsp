<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/newItemSubmitted2.do" /></c:set>

<div align="center">
<form:form method="post" commandName="itemFormAuction" action="${targetUrl}">
<form:errors cssClass="error" /> <br><br>
<table class="n13">
	<tr>
		<td colspan="2">
			<font color="green" size="4"><b>경매 상품 상세 정보</b></font>
		</td>
	</tr>
	<tr>
		<td>카테고리: </td> <!-- Product 객체의 categoryId field -->
		<td><form:select path="item.product.categoryId" items="${categoryNames}" />
		</td>
	</tr>	
	<tr>
		<td>상품명: </td> <!-- Product 객체의 name field -->
		<td><form:select path="item.product.name" items="${productNames}" />
		    <form:errors path="item.product.name" />
		</td>
	</tr>
	<tr>
		<td>판매자: </td> <!-- item 객체의 sellerUsername field. read only. from session. -->
		<td><form:input path="item.sellerUsername" value="${userSession.account.username}" disabled="true" />
		    <form:errors path="item.sellerUsername" />
		</td>
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
		<td><form:input path="item.attribute1" />
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
		<td><form:input path="item.endDate" value="YYYY-MM-DD HH:mm" />
		    <form:errors path="item.endDate" />
		</td>
	</tr>
	<tr>
		<td>경매시작가: </td> 
		<td><form:input path="item.startPrice" />
		    <form:errors path="item.startPrice" />
		</td>
	</tr>
	<!--  
	<tr>
		<td>즉시낙찰: </td> 
		<td>
			<label for="showY"><form:radiobutton path="item.winningBid"	value="Y" id="showY1" /> 예</label>
 			<label for="showN"><form:radiobutton path="item.winningBid" value="N" id="showN2"  /> 아니오</label>
	</tr>
	-->
</table>
<p>
	<input type="image" src="../images/button_submit.gif">
</p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>

