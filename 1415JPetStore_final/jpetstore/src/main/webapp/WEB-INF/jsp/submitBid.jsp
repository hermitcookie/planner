<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'>
      <b><font color="black" size="2">&lt;&lt; Main Menu</font></b></a>
    </td>
  </tr>  
</table>

<div align="center">
  <c:if test="${!empty message}">
    <b><c:out value="${message}" /></b>
  </c:if>
  
  <p></p>
  <table class="n13">
    <tr>
      <td align="center" colspan="2"><font size="4">
        <b>Item #<c:out value="${addedBid.itemId}" /></b></font> <br />
    </tr>
    <tr>
      <td colspan="2"><font color="green" size="4"><b>입찰 정보</b></font></td>
    </tr>
   <tr>
      <td>입찰자</td>
      <td>${addedBid.userId}</td>
    </tr>
    <tr>
      <td>입찰가</td>
      <td>${addedBid.bidPrice}</td>
    </tr>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>