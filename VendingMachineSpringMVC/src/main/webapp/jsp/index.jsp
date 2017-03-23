<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
  <head>
      <title>Vending Machine Spring MVC</title>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
      <link href="https://fonts.googleapis.com/css?family=Gravitas+One" rel="stylesheet">
  </head>
  <body>
    
      <div class="container">
        <h1>Vending Machine</h1>
        <hr>
        
        <div id="itmemDisplay" class="col-md-8">
          <c:forEach var="machineSlot" items="${machineSlotList}">
            <a href="selectMachineSlot?slotId=${machineSlotList.indexOf(machineSlot)}">
             <div id="${machineSlotList.indexOf(machineSlot)}" class="col-md-4">
              <div class="item panel panel-default col-md-11">
                <h5>
                  <c:out value="${machineSlotList.indexOf(machineSlot)+1}"/>
                </h5>
                <h3 class="itemName">
                  <c:out value="${machineSlot.productName}"/>
                </h3>
                <h4 class="itmemPrice">
                  <c:out value="${machineSlot.price}"/>
                </h4>
               <br>
               <h4>
                 Quantity Left: <c:out value="${machineSlot.quantity}"/>
               </h4>

              </div>
             </div>
            </a>
          </c:forEach>
        </div>
        
        <div class="col-md-4" id="pannels">
          <div class="row"  id="moneyPannel">
            <h3>Total Money In</h3>
            <div class="row">
                <div class="col-md-offset-3 col-md-6 well"  id="moneyPannelDisplay">
                <c:set var="totalInserted" value="${totalInserted}"></c:set>
                <c:if test="${empty totalInserted}">
                    $0.00
                </c:if>
                <c:if test="${not empty totalInserted}">
                    <c:out value="${totalInserted}"/>
                </c:if> 
              </div>
            </div>
            <div class="form-group row">
              <div class="form-group col-md-6">
                <a href="addMoney?addButtonId=DOLLARS">
                    <button class="btn btn-success moneyButtons" id="DOLLARS">Add Dollar</button>
                </a>
              </div>
              <div class="form-group col-md-6">
                <a href="addMoney?addButtonId=QUARTERS">
                    <button class="btn btn-success moneyButtons" id="QUARTERS">Add Quarter</button>
                </a>
              </div>
              <div class="form-group col-md-6">
                <a href="addMoney?addButtonId=DIMES">
                    <button class="btn btn-success moneyButtons" id="DIMES">Add Dime</button>
                </a>
              </div>
              <div class="form-group col-md-6">
                <a href="addMoney?addButtonId=NICKELS">
                    <button class="btn btn-success moneyButtons" id="NICKELS">Add Nickel</button>
                </a>
              </div>
            </div>
          </div>
          <hr>
          <div class="row" id="messagePannel">
            <h3>Messages</h3>
            <div class="col-md-12 well" id="errorMessages">
            </div>
            <div class="form-group">
              
              <label id="itemSelectionLabel" for="itemSelection" class="col-md-3 control-label">
                Item:
              </label>
              <div class="col-md-9">
                <c:set var="selectedMachineSlot" value="${selectedMachineSlot}"></c:set>
                <c:if test="${empty selectedMachineSlot}">
                    <input type="text" id="itemSelection" class="form-control" placeholder="Choose an Item!">
                </c:if>
                <c:if test="${not empty selectedMachineSlot}">
                    <input type="text" id="itemSelection" class="form-control" value="${selectedMachineSlot.productName}">
                </c:if>
                
                 <br>
              </div>
             
              <a href="makePurchase?buyButtonId=buyButton">
                <button class="btn btn-primary col-md-offset-3" id="buyButton">Make Purchase</button>
              </a>
            </div>
          </div>
          <hr>
          <div class="row" id="changePannel">
            <h3 id="changeHeader">Your Change</h3>
              <div class="col-md-12 well" id="changePannelDisplay">
                <c:set var="changeString" value="${changeString}"></c:set>
                <c:if test="${empty selectedMachineSlot}">
                    No Change Due
                </c:if>
                <c:if test="${not empty selectedMachineSlot}">
                    <c:out value="${changeString}"/>
                </c:if>
              </div>
            <div class="form-group">
              <a href="getChange?changeReturnButton=changeReturnButton">
                <button class="btn btn-success col-md-offset-3" id="changeReturnButton">Change Return</button>
              </a>
            </div>
          </div>
        </div>
      
      
      
    </div>
      <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/script.js"></script>
  </body>
</html>

