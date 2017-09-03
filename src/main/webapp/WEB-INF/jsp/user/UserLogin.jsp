<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf"%>
<%--content--%>

<div class="container">
  <div class="card p-3 bg-light mx-auto" style="width: 45rem;">
    <h2 class="card-title text-center">Sign in</h2>

    <form action="/login" method="POST">
      <div class="form-group row justify-content-center">
        <label class="text-right col-2 col-form-label" for="login">Login:</label>
        <input id="login" name="login" type="text" class="form-control col-4" required="required"/>
      </div>

      <div class="form-group row justify-content-center">
        <label class="text-right col-2 col-form-label" for="password">Password:</label>
        <input type="password" id="password" name="password" class="form-control col-4" placeholder="min 6 characters" required="required"/>
      </div>
      <c:if test="${map != null}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <h5 class="form-text"><c:out value="${map['LOGIN'].getErrorMessage()}"/></h5>
        </div>
      </c:if>
      <div class="text-center">
        <button type="submit" class="btn btn-primary">
          <i class="icon-lock icon-white"></i>
          Sign in</button>
      </div>

      <div align="center">
        You don't have an account yet?
        <a href="/register">Register here for free!</a>
      </div>
    </form>
  </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf"%>