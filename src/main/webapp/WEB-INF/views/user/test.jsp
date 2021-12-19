<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 08.12.2021
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/available/add" method="post">
  <div class="form-group row">
    <div class="col-sm-6">
      <label for="start">Share from:</label>
      <input type="text" id="start" name="start" class="form-control form-control-user"/>
    </div>
    <div class="form-group">
      <label for="end">Share to:</label>
      <input type="text" id="end" name="end" class="form-control form-control-user"/>
    </div>
  </div>
  <button type="submit" class="btn btn-primary btn-user btn-block">Share</button>
  <form:form method="post" class="user">
    <div class="form-group row">
      <div class="col-sm-6">
        <label for="start">Share from:</label>
        <input type="date" id="start" name="start" class="form-control form-control-user"/>
      </div>
      <div class="form-group">
        <label for="end">Share to:</label>
        <input type="date" id="end" name="end" class="form-control form-control-user"/>
      </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user btn-block">Share</button>
  </form:form>
</form>

</body>
</html>
