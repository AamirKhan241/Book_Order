<%@page import="cart.Cart_DAO_Implt"%>
<%@page import="cart.Cart_DAO"%>
<div id="cart-container">
      <div id="cart">
        <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="font-size: 15px"></i>
      </div>
        <%
            Cart_DAO cdao=new Cart_DAO_Implt();
            if(cdao.item(Integer.valueOf(request.getSession().getAttribute("id").toString())) >0){
        %>
        <span id="itemCount"><%=cdao.item(Integer.valueOf(request.getSession().getAttribute("id").toString()))%></span>
        <%}%>
    </div>
 <style>


#cart-container {
  float: right;
  width: 15px;
  position: relative;
}

#itemCount {
  position: absolute;
  display: block;
  top: -10px;
  left: -10px;
  width: auto;
  height: auto;
  font-size:auto;
  border-radius: 50%;
  background: green;
  color: white;
  text-align: center;
}

i:hover {
  cursor: pointer;
}
 </style>