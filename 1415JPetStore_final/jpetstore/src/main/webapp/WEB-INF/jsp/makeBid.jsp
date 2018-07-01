<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/submitBid.do" /></c:set>

<div align="center">
<form:form method="post" commandName="bidForm" action="${targetUrl}">
<br><br>
<table class="n13">
	<tr>
		<td colspan="2">
			<font color="green" size="4"><b>입찰 세부 정보</b></font>
		</td>
	</tr>
	<tr>
		<td>아이템ID: </td> 
		<td><c:out value="${newBid.itemId}"/>
		</td>
	</tr>	
	<tr>
		<td>사용자: </td> 
		<td><c:out value="${newBid.userId}"/>
		</td>
	</tr>	
	<tr>
		<td>입찰희망가: </td> 
		<td><form:input path="bid.bidPrice" type="number" /></td>
	</tr>
</table>
<p>
	<input type="submit">
</p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>
