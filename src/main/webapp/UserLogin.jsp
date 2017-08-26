<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf"%>
<%--content--%>

<div class="container">
  <div class="card p-3 bg-light mx-auto" style="width: 45rem;">
    <h2 class="card-title text-center">Sign in</h2>

    <form method="post">
      <div class="form-group row justify-content-center">
        <label class="text-right col-2 col-form-label" for="email">Email:</label>
        <input id="email" name="email" type="email" class="form-control col-4" placeholder="your@email.com" required="required"/>
        <p class="form-text"><c:out value="${requestScope.errorEmail}"/></p>
      </div>

      <div class="form-group row justify-content-center">
        <label class="text-right col-2 col-form-label" for="password">Password:</label>
        <input type="password" id="password" name="password" class="form-control col-4" placeholder="min 6 characters" required="required"/>
        <p class="form-text"><c:out value="${requestScope.errorPassword}"/></p>
      </div>

      <div class="text-center">
        <button type="submit" class="btn btn-primary">
          <i class="icon-lock icon-white"></i>
          Sign in</button>
      </div>

      <div align="center">
        You don't have an account yet?
        <a href="register">Register here for free!</a>
      </div>
    </form>
  </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf"%>