<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tl" uri="http://tasklist.org/taglib" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>
<%--content--%>

<div class="container">
    <div class="card bg-light mt-3">
        <div class="card-body">
            <h2 class="card-title text-center">Sign up</h2>
            <form method="post">

                <%@ include file="../common/error.jspf" %>

                <div class="form-row">
                    <div class="form-group col-4 mx-auto">
                        <label class="form-control-label" for="fullName">Name:</label>
                        <input id="fullName" name="fullName" type="text"
                               class="form-control <tl:inputStyle status="${requestScope.errorFullName != null}"/>"/>

                        <c:if test="${requestScope.errorFullName != null}">
                            <div class="invalid-feedback">
                                <c:out value="${requestScope.errorFullName}"/>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-4 mx-auto">
                        <label class="form-control-label" for="login">Login</label>
                        <input id="login" name="login" type="text"
                               class="form-control <tl:inputStyle status="${requestScope.errorLogin != null}"/>"/>
                        <c:if test="${requestScope.errorLogin != null}">
                            <div class="invalid-feedback">
                                <c:out value="${requestScope.errorLogin}"/>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-4 mx-auto">
                        <label class="form-control-label" for="password">Password:</label>
                        <input type="password" id="password" name="password"
                               class="form-control <tl:inputStyle status="${requestScope.errorPassword != null}"/>"
                               placeholder="min 6 characters"/>
                        <c:if test="${requestScope.errorPassword != null}">
                            <div class="invalid-feedback">
                                <c:out value="${requestScope.errorPassword}"/>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-4 mx-auto">
                        <label class="form-control-label" for="confirmationPassword">Confirmation password:</label>
                        <input type="password" id="confirmationPassword" name="confirmationPassword"
                               class="form-control <tl:inputStyle status="${requestScope.errorConfirmationPassword != null || requestScope.errorConfirmationPasswordMatching != null}"/>"
                               placeholder="min 6 characters"/>
                        <c:if test="${requestScope.errorConfirmationPassword != null}">
                            <div class="invalid-feedback">
                                <c:out value="${requestScope.errorConfirmationPassword}"/>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.errorConfirmationPasswordMatching != null}">
                            <div class="invalid-feedback">
                                <c:out value="${requestScope.errorConfirmationPasswordMatching}"/>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">
                        <i class="icon-lock icon-white"></i>Sign up
                    </button>
                </div>
                <div align="center">
                    You have already an account?
                    <a href="login">Sign in here</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>