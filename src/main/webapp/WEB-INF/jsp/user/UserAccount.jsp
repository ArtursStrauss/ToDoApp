<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>
<%--content--%>
<div class="container">
    <div class="row">
        <div class="col-3">
            <%@ include file="../common/sidebar.jspf" %>
        </div>
        <div class="col-9">
            <div class="card bg-light mt-3 p-1">
                <div class="card-body">
                    <h1 class="card-title">My settings</h1>
                    <div class="row">
                        <form action="/user/account/update" method="post">
                            <legend>Update my profile <p
                                    class="alert-success">${requestScope.updateProfileSuccessMessage}</p></legend>
                            <div class="form-group">
                                <label for="id">Id:</label>
                                <input name="id" id="id" value="${sessionScope.user.getId()}" type="text"
                                       class="form-control" disabled="disabled"/>
                            </div>

                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input name="name" id="name" value="${sessionScope.user.getFullName()}" type="text"
                                       class="form-control" required="required"/>
                            </div>

                            <button type="submit" class="btn btn-primary"><i class="fa fa-refresh"
                                                                             aria-hidden="true"></i> Update my profile
                            </button>
                        </form>
                    </div>

                    <div class="row">
                        <form action="/user/account/password" method="post">
                            <legend>Update my Password <p
                                    class="alert-success">${requestScope.updatePasswordSuccessMessage}</p>
                            </legend>
                            <div class="form-group">
                                <label for="currentPassword">Current password:</label>
                                <input type="password" id="currentPassword" name="currentPassword"
                                       class="form-control" placeholder="min 6 characters"
                                       required="required"/>
                                <p class="help-block alert-error"><c:out
                                        value="${requestScope.errorCurrentPassword}"/></p>
                            </div>

                            <div class="form-group">
                                <label for="newPassword">New password:</label>
                                <input type="password" id="newPassword" name="newPassword"
                                       class="form-control"
                                       placeholder="min 6 characters" required="required"/>
                                <p class="help-block alert-error"><c:out
                                        value="${requestScope.errorNewPassword}"/></p>
                            </div>

                            <div class="form-group">
                                <label for="confirmationPassword">Confirmation password:</label>
                                <input type="password" id="confirmationPassword" name="confirmationPassword"
                                       class="form-control" placeholder="min 6 characters"
                                       required="required"/>
                                <p class="help-block alert-error"><c:out
                                        value="${requestScope.errorConfirmationPassword}"/></p>
                            </div>
                            <button type="submit" class="btn btn-primary"><i class="fa fa-refresh"
                                                                             aria-hidden="true"></i> Update my password
                            </button>

                        </form>
                    </div>

                    <div class="row">
                        <legend>Delete my account</legend>
                        <div class="alert alert-danger fade show" role="alert">
                            <p>You are about to remove your account from "TodoApp". This will completely
                                delete all your data and you will be no more able to use your account.
                                Please be sure.</p>
                            <p>
                                <a class="btn btn-danger" data-toggle="modal" href="#deleteAccountLink"><i
                                        class="fa fa-times" aria-hidden="true"></i> Delete my account</a>
                            </p>
                            <div class="modal fade" id="deleteAccountLink">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3>Confirmation</h3>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure to delete your account?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <form method="post" action="/user/account/delete">
                                                <p>
                                                    <a href="#" class="btn" data-dismiss="modal">No, I'm not sure</a>
                                                    <button type="submit" class="btn btn-danger">Yes, I do confirm!
                                                    </button>
                                                </p>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>