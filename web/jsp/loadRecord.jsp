<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2019/2/14
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Load Record</title>
    <link rel="stylesheet" href="../css/loadRecord.css">
</head>
<body>
<div>
    <form class="div_1" action="../load" method="get">
        <input type="text" class="text_1" name="event_category" placeholder value="category"/><br>
        <input type="text" class="text_1" name="event_name" placeholder value="event_name"/><br>
        <input type="text" class="text_1" name="level_rank" placeholder value="level_rank"/><br>
        <input type="text" class="text_1" name="time_use" placeholder value="01:10:20"/><br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
