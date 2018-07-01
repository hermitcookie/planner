<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyActivity</title>
<style>
.joinText{
	margin-left:5%;
	margin-top: 5%;
    margin-bottom: 0px;
    font-size: 30px;
    font-weight: bold;
    font-family: Arial, sans-serif;
    color: #333;
}
.categoryBox{
	display:flex;
	margin-top:3%;
	justify-content:space-around;
	border-bottom: 2px solid #ddd;
}
.categortTxt{
	font-size: 18px;
    font-weight: 600;
    text-align: center;
    margin-top:3%;
    margin-bottom:5%;
    text-decoration:none;
    color:black;
}
.cTa{
	text-decoration:none;
}
.seelist{
	width:48%;
}
.manyPicture{
	width:100%;
	height:200px;
}
.imgListCla{
	display:flex;
	flex-wrap:wrap;
}
</style>
</head>
<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/editAccount.do">
        <c:param name="categoryId" value="${product.categoryId}"/></c:url>'>
        <b><font color="black" size="2">
          &lt;&lt;MyAccount</font></b></a>
    </td>
  </tr>
</table>

<div>
	<div style="display:flex;justify-content:center;">
		<div class="joinText">My Activity</div>
	</div>
	
	<div class="categoryBox">
		
				<a class="cTa" href="/jpetstore/shop/myActivity.do">
					<div class="categortTxt">recent</div>
				</a>
			
		<div>	
			<div>
			<a class="cTa" href="/jpetstore/shop/myLikeActivity.do">
				<div class="categortTxt">like</div>
			</a>
			</div>
			<img src="../images/tab_on_1.png"/>
		</div>
	</div>
</div>
	
	<div class="imgListCla" style="padding-left: 20%;padding-top: 5%;">
		<c:forEach var="result" items="${resultRecentList }">
			<div class="seelist">	
			<a href="/jpetstore/shop/DeletemyLikeActivity.do?itemId=${result.itemId}"><img src="../images/X.png"/></a>
					<div>${result.name }</div>
					<div>${result.pdate }</div>
					<div class="manyPicture"><a href="/jpetstore/shop/viewItem.do?itemId=${result.itemId}">${result.descn }</a></div>
				
			</div>
		</c:forEach> 
	</div>



<%@ include file="IncludeBottom.jsp"%>