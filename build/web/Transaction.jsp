<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="margin: 20px 0px 22px; visibility: visible;" class="test1 nav  nav-justified nav-pills nav-tabs">
                <li class="active"><a data-toggle="tab" href="#NetBanking"  >NetBanking</a></li>
                <li class=""><a data-toggle="tab" href="#Card"><span class="glyphicon glyphicon-credit-card"></span> Card</a></li>
                <li class=""><a data-toggle="tab" href="#CoD">COD</a></li>
            </ul>

            <div class="tab-content">
                <div id="NetBanking" class="tab-pane fade in active">
                    <div class="container" style="border-radius: 1%; background-color: lightgrey; border: solid .5px silver" >
                        <img class="img-rounded" src="Images/net banking.png"alt="Bank Logo" style="width: 99%; height:15vh; padding-top: 1%">
                        <h3><center>Bank Details</center></h3>
                        <label>Bank Name:</label>
                        <select name="BankName" class="form form-control" onchange="bDetails()" id="bank" required>
                            <option value="">Select Bank Name...</option>
                                <%
                                    String bank[]={
                                    "Allahabad Bank","Andhra Bank","Axis Bank","Bandhan Bank","Bank of Baroda","Bank of India",
                                    "Bank of Maharashtra","Canara Bank","Catholic Syrian Bank","Central Bank of India","City Union Bank",
                                    "Corporation Bank","DCB Bank","Dena Bank","Dhanlaxmi Bank","Federal Bank","HDFC Bank","ICICI Bank",
                                    "IDBI Bank","Indian Bank","Indian Overseas Bank","IndusInd Bank","Jammu and Kashmir Bank",
                                    "Karnataka Bank","Karur Vysya Bank","Kotak Mahindra Bank","Oriental Bank of Commerce",
                                    "Punjab and Sind Bank","Punjab National Bank","RBL Bank","Reserve Bank of India","South Indian Bank",
                                    "Standard Chartered","State Bank of India","Syndicate Bank","UCO Bank","Union Bank of India",
                                    "United Bank of India","Vijaya Bank","Yes Bank"};
                                    for (String elem : bank) {
                                        out.println("<option>"+elem+"</option>");
                                    }
                                %>
                        </select>
                        <br>
                        <center>
                            <div id="bDet" style="display: none; width:50%">
                                <img id="bankImg" class="img-rounded" src="" alt="Bank Logo" style="width: 100%; height:10vh">
                                <br>
                                <label style="float: left">Bank Username</label>
                                <input type="text" placeholder="Username" name="bnkUName" class="form form-control" required>
                                <br>
                                <label style="float: left">Password</label>
                                <input type="password" placeholder="Password" name="bnkPass" class="form form-control" required pattern=".{8,16}" title="length should be between 8-16.">
                                <br>
                                <center>
                                    <input type="button" href="" class="btn btn-success" style="width: 30%" onclick="Net()" value="Place Order">
                                </center>
                                <br>
                            </div>
                        </center>
                        <script>
                            function bDetails(){
                                if(document.getElementById("bank").value === ""){
                                    console.log("Bank Not Found");                                        
                                    document.getElementById("bDet").style.display = "none";
                                }else{
                                    document.getElementById("bDet").style.display = "block";

                                    switch(document.getElementById("bank").value){
                                        case "Allahabad Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/AllahabadBank.jpg';
                                            break;
                                        case "Andhra Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/AndhraBank.jpg';
                                            break;
                                        case "Axis Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/AxisBank.jpg';
                                            break;
                                        case "Bandhan Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/BandhanBank.jpg';
                                            break;
                                        case "Bank of Baroda":
                                            document.getElementById("bankImg").src = 'Images/Banks/BankofBaroda.jpg';
                                            break;
                                        case "Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/BankofIndia.jpg';
                                            break;
                                        case "Bank of Maharashtra":
                                            document.getElementById("bankImg").src = 'Images/Banks/BankofMaharashtra.jpg';
                                            break;
                                        case "Canara Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/CanaraBank.jpg';
                                            break;
                                        case "Catholic Syrian Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/CatholicSyrianBank.jpg';
                                            break;
                                        case "Central Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/CentralBankofIndia.jpg';
                                            break;
                                        case "City Union Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/CityUnionBank.jpg';
                                            break;
                                        case "Corporation Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/CorporationBank.jpg';
                                            break;
                                        case "DCB Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/DCBBank.jpg';
                                            break;
                                        case "Dena Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/DenaBank.jpg';
                                            break;
                                        case "Dhanlaxmi Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/DhanlaxmiBank.jpg';
                                            break;
                                        case "Federal Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/FederalBank.jpg';
                                            break;
                                        case "HDFC Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/HDFCBank.jpg';
                                            break;
                                        case "ICICI Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/ICICIBank.jpg';
                                            break;
                                        case "IDBI Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/IDBIBank.jpg';
                                            break;
                                        case "Indian Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/IndianBank.jpg';
                                            break;
                                        case "Indian Overseas Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/IndianOverseasBank.jpg';
                                            break;
                                        case "IndusInd Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/IndusIndBank.jpg';
                                            break;
                                        case "Jammu and Kashmir Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/JammuandKashmirBank.jpg';
                                            break;
                                        case "Karnataka Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/KarnatakaBank.jpg';
                                            break;
                                        case "Karur Vysya Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/KarurVysyaBank.jpg';
                                            break;
                                        case "Kotak Mahindra Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/KotakMahindraBank.jpg';
                                            break;
                                        case "Oriental Bank of Commerce":
                                            document.getElementById("bankImg").src = 'Images/Banks/OrientalBankofCommerce.jpg';
                                            break;
                                        case "Punjab and Sind Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/PunjabandSindBank.jpg';
                                            break;
                                        case "Punjab National Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/PunjabNationalBank.jpg';
                                            break;
                                        case "RBL Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/RBLBank.jpg';
                                            break;
                                        case "Reserve Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/ReserveBankofIndia.jpg';
                                            break;
                                        case "South Indian Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/SouthIndianBank.jpg';
                                            break;
                                        case "Standard Chartered":
                                            document.getElementById("bankImg").src = 'Images/Banks/StandardChartered.jpg';
                                            break;
                                        case "State Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/StateBankofIndia.jpg';
                                            break;
                                        case "Syndicate Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/SyndicateBank.jpg';
                                            break;
                                        case "UCO Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/UCOBank.jpg';
                                            break;
                                        case "Union Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/UnionBankofIndia.jpg';
                                            break;
                                        case "United Bank of India":
                                            document.getElementById("bankImg").src = 'Images/Banks/UnitedBankofIndia.jpg';
                                            break;
                                        case "Vijaya Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/VijayaBank.jpg';
                                            break;
                                        case "Yes Bank":
                                            document.getElementById("bankImg").src = 'Images/Banks/YesBank.jpg';
                                            break;
                                    }


                                }
                            }
                        </script>
                    </div>
                </div>
                <div id="Card" class="tab-pane fade">
                    <center><h3>Card Details</h3></center>
                    <div style="width: 100%">
                        <c:import url="Card.jsp"></c:import>
                    </div>
                </div>
                <div id="CoD" class="tab-pane fade">
                    <center><h3>CoD</h3></center>
                    <center><input type="button" href="" class="btn btn-success" style="width: 30%" onclick="CoD()" value="Place Order"></center>
                </div>
            </div>
        
<script>
    function CoD(){
        document.getElementById("frm").action = "PlaceOrder?mode=COD&sNo=<%=request.getParameter("Sno")%>&page=<%=request.getParameter("page")%>&dAmt="+document.getElementById('amt').value;
        
        var x = document.getElementsByClassName("form_class");
        x[0].submit();
    }
    function Net(){
        document.getElementById("frm").action = "PlaceOrder?mode=NETBANKING&sNo=<%=request.getParameter("Sno")%>&page=<%=request.getParameter("page")%>&dAmt="+document.getElementById('amt').value;
        
        var x = document.getElementsByClassName("form_class");
        x[0].submit();
    }
    function Card(){
        document.getElementById("frm").action = "PlaceOrder?mode=CARD&sNo=<%=request.getParameter("Sno")%>&page=<%=request.getParameter("page")%>&dAmt="+document.getElementById('amt').value;
        
        var x = document.getElementsByClassName("form_class");
        x[0].submit();
    }
    
</script>