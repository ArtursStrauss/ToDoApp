<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf"%>
<%--content--%>

<div class="container">
  <div class="card p-3 bg-light text-center">
    <h2 class="card-title">Sign up</h2>
    <form method="post">
      <div class="form-row">
        <div class="form-group col-4 mx-auto">
          <label for="name">Name:</label>
          <input id="name" name="name" type="text" class="form-control" required="required"/>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group col-4 mx-auto">
          <label for="login">Login</label>
          <input id="login" name="login" type="text" class="form-control" required="required"/>
          <p class="form-text"><c:out value="${map['LOGIN'].getErrorMessage()}"/></p>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group col-4 mx-auto">
          <label class="control-label" for="password">Password:</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="min 6 characters" required="required"/>
          <p class="form-text"><c:out value="${map['PASSWORD'].getErrorMessage()}"/></p>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group col-4 mx-auto">
          <label for="confirmationPassword">Confirmation password:</label>
          <input type="password" id="confirmationPassword" name="confirmationPassword" class="form-control" placeholder="min 6 characters" required="required"/>
          <p class="form-text"><c:out value="${map['CONFIRM_PASSWORD'].getErrorMessage()}"/></p>
        </div>
      </div>

      <button type="submit" class="btn btn-primary">
        <i class="icon-lock icon-white"></i>Sign up
      </button>

      <div align="center">
        You have already an account?
        <a href="/login">Sign in here</a>
      </div>
    </form>
  </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf"%>