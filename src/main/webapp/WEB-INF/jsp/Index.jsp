<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="./common/header.jspf"%>

<div class="container">
  <div class="card bg-light">
    <div class="card-body">
      <h3 class="card-title">Welcome to ToDo App</h3>
      <p>
        <h4>ToDo App is a web-based task manager which allows you to:</h4>
        <ul>
          <li>Save and organize your todo list</li>
          <li>Search easily your todo list</li>
          <li>Sort and filter your todo list</li>
          <li>Export and report your todo list</li>
        </ul>
        <h4>And which is totally Free! Enjoy !</h4>
      </p>

      <c:if test="${sessionScope.user == null}">
        <p>
          <a class="btn btn-primary btn-large" href="login">
            Sign in
          </a>
          or
          <a class="btn btn-primary btn-large" href="register">
            Sign up
          </a>
        </p>
      </c:if>

      <c:if test="${sessionScope.user != null}">
        <p>
          <a class="btn btn-primary btn-large" href="user/todos">
            Go to my Home page
          </a>
        </p>
      </c:if>
    </div>
  </div>
</div>

<%@ include file="./common/footer.jspf"%>