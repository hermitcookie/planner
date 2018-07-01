<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.graybtn{
 margin-top: 3%;
 margin-bottom: 3%; 
margin-left: 15%;
}
.right {
 	border: 1px solid #ddd;
    background-color: #FFEB3B;
    color: #313030;
    font-size: 22px;
    padding: 19px 60px;
    text-decoration:none;
    float: left;
}
.left {
	border: 1px solid #ddd;
    background-color: #FFEB3B;
    color: #313030;
    font-size: 22px;
    padding: 19px 60px;
    text-decoration:none;
    float: right;
}
</style>



<div class="graybtn" style="width:600px; height:270px;">
 <div><a class="right" href="<c:url value="/shop/addItem.do"/>">P2P물품 등록</a></div>
 <div> <a class="left" href="<c:url value="/shop/addAuctionItem.do"/>">경매물품 등록</a></div>
</div>


<%@ include file="IncludeBottom.jsp"%>

