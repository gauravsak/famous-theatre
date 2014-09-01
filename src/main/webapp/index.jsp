<%--
  Created by IntelliJ IDEA.
  User: gsakhardande
  Date: 1/9/14
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <form action="bookTickets" method="GET">
        <label for="sms">SMS : </label><input type="text" id="sms" name="sms" tabindex="1"/>
        <label for="sms">Mobile : </label><input type="text" id="mobile" name="mobile" tabindex="2"/>
        <button value="Send" id="sendSmsBtn" type="submit" tabindex="3">Send</button>
    </form>
  </body>
</html>
