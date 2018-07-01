<%@ include file="IncludeTop.jsp"%>
<style>
.graybtn2{
 margin-top: 3%;
 margin-bottom: 3%; 
}
.itisbtngray2{
 border: 1px solid #ddd;
    background-color: #d2e60d;
    color: #313030;
    font-size: 22px;
    padding: 19px 60px;
    text-decoration:none;
}
</style>

<table id="main-menu">
  <tr>
  <!-- 
    <td>
      <a href='<c:url value="/shop/viewProduct.do">
        <c:param name="productId" value="${product.productId}"/></c:url>'>
        <b><font color="black" size="2">
          &lt;&lt; <c:out value="${product.name}" /></font></b></a>
    </td>
     -->
     <td>
      <a href='<c:url value="/shop/listSellingAuctionItems.do">
        <c:param name="productId" value="${product.productId}"/></c:url>'>
        <b><font color="black" size="2">
          &lt;&lt; Auction List</font></b></a>
    </td>
  </tr>
  
  <c:if test="${userSession.account.username eq item.sellerUsername}">
  <tr>
   <td>
      <a href='<c:url value="/shop/editAuctionItem.do">
        <c:param name="itemId" value="${item.itemId}"/></c:url>'>
        <b><font color="black" size="2">
           &lt;&lt; edit</font></b></a>
    </td>
  </tr>
    <tr>
   <td>
      <a href='<c:url value="/shop/removeSellingAuctionItem.do">
        <c:param name="itemId" value="${item.itemId}"/></c:url>'>
        <b><font color="black" size="2">
           &lt;&lt; delete</font></b></a>
    </td>
  </tr>
  </c:if>
</table>
<p>

<div align="center">
  <table id="item">
    <tr>
      <td bgcolor="#FFFFFF">
      <div style="display:flex;width:100%;">
	      <div style="display:inline-block;width:60%">
	        <c:out value="${product.description}" escapeXml="false" />
	      </div>
	      <div style="display:inline-block;width:40%">
	      view : ${view.countview } like: ${view.countlike } 
	       
	      <c:if test="${!empty userSession.account.username}">
	      	<a href='<c:url value="/shop/likeUpdate.do">
	      		 <c:param name="itemId" value="${item.itemId}"/></c:url>'>
	      		 
	      			<!-- <img src="/jpetstore/images/NEW-IMG-HEART.png"/> -->
	      			<c:if test="${likyn eq 'true'}">
	      			<img style="width: 30px;margin-left: 5%;margin-top: 5%;" src="/jpetstore/images/h1.jpg"/>  
	      			</c:if>
	      			<c:if test="${likyn eq 'false'}">
	      			<img style="width: 30px;margin-left: 5%;margin-top: 5%;" src="/jpetstore/images/h2.jpg"/> 
	      			</c:if>
	      	</a>
	      </c:if> 
	      </div>
      </div>
      </td>
          </tr>
    <tr>
      <td width="100%" bgcolor="#CCCCCC"><b><c:out value="${item.itemId}" /></b></td>
    </tr>
    <tr>
      <td><b><font size="4"> 
        <c:out value="${item.attribute1}" />
        <c:out value="${item.attribute2}" /> 
        <c:out value="${item.attribute3}" />
        <c:out value="${item.attribute4}" /> 
        <c:out value="${item.attribute5}" />
        <c:out value="${product.name}" />
        </font></b></td>
    </tr>
    <tr>
      <td><font size="3"><i><c:out value="${product.name}" /></i></font></td>
    </tr>
    <!-- 
    <tr>
      <td>
      <c:if test="${item.quantity <= 0}">
        <font color="red" size="2"><i>Back ordered.</i></font>
      </c:if> 
      <c:if test="${item.quantity > 0}">
        <font size="2"><fmt:formatNumber value="${item.quantity}" /> in stock.</font>
      </c:if>
      </td>
    </tr>
     -->
    <tr>
      <td>Start Price  <fmt:formatNumber value="${item.startPrice}" pattern="$#,##0.00" /></td>
    </tr>    
    <tr>
      <td width="100%" >Deadline: <c:out value="${item.endDate}"/></td>
    </tr>
    <tr>
      <td width="100%" >Status: <c:out value="${item.aucStatus}"/></td>
    </tr>
    <tr>
      <td width="100%" >Highest User: <c:out value="${item.highestUser}"/></td>
    </tr>
    <c:if test="${item.aucStatus eq 'OPEN'}">
    <c:if test="${userSession.account.username ne item.sellerUsername}">
    <tr>
      <td>
        <a href='<c:url value="/shop/makeBid.do">
          <c:param name="workingItemId" value="${item.itemId}"/></c:url>'>
          <img border="0" src="../images/button_add_to_cart.gif" alt="" /></a>
      </td>
    </tr>
    </c:if>
    </c:if>
   
    <tr>
    <td>
    <c:if test="${item.aucStatus eq 'CLOSE'}">
    <c:if test="${userSession.account.username eq item.highestUser}">
    	<a href='<c:url value="/shop/addItemToCart.do">
    		<c:param name="workingItemId" value="${item.itemId}"/></c:url>'>
    		<img border="0" src="../images/button_add_to_cart.gif" alt="" />
    	</a>
    </c:if>
    </c:if>
    </td>
    </tr>
    
  </table>
  
	<div class="graybtn2"  style="margin-top:7%;display:flex;justify-content:space-around">
		<div class="itisbtngray2" >Highest Price : <c:out value="${item.highestPrice }"/></div>
	</div>
</div>

<%@ include file="IncludeBottom.jsp"%>
