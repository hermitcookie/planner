<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/editItemSubmitted.do"/></c:set>

<div align="center">
<form:form method="post" commandName="editItemForm" action="${targetUrl}">
<form:errors cssClass="error" /> <br><br>
<table class="n13" id="table">
   <tr>
      <td align="center" colspan="2"><font size="4">
        <b>Item #<c:out value="${editItem.itemId}" /></b></font> <br />
        <form:input path="item.itemId" value="${editItem.itemId}" type="hidden"/>
    </tr>
    <tr>
      <td colspan="2"><font color="green" size="4"><b>상품 상세 정보</b></font></td>
    </tr>
   <tr>
      <td>카테고리</td>
      <td>${editItem.product.categoryId}</td>
    </tr>
    <tr>
      <td>상품명</td>
      <td>${editItem.product.name}</td>
    </tr>
     <tr>
      <td>가격</td>
      <td>
      	<form:input path="item.listPrice" type="number" value="${editItem.listPrice}"/>
		<form:errors path="item.listPrice" />
      </td>
    </tr>
    <tr>
      <td>특징</td>
      <td>
		<form:input path="item.attribute1" value="${editItem.attribute1}"/>
		<form:errors path="item.attribute1" />
	  </td>
    </tr> 
</table>
<p>
	<input type="image" src="../images/button_submit.gif">
</p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>

