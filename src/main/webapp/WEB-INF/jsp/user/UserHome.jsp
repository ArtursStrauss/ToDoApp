<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tl" uri="http://tasklist.org/taglib" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jspf" %>
<%--content--%>

<div class="container">
    <div class="row">
        <div class="col-3">
            <%@ include file="../common/sidebar.jspf" %>
        </div>
        <div class="col-9">
            <div class="card bg-light mt-3">
                <div class="card-body">
                    <h1 class="card-title">My Task list</h1>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Due Date</th>
                            <th>Priority</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.taskList}" var="task">
                            <tr>
                                <td>${task.getId()}</td>
                                <td>${task.getTitle()}</td>
                                <td><fmt:formatDate value="${task.getDueDate()}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <i class="fa fa-arrow-circle-<tl:priorityIcon priority="${task.getPriority()}"/>"></i>
                                        ${task.getPriority()}
                                </td>
                                <td>
                                    <span class="badge <tl:statusStyle status="${task.isDone()}"/>"><tl:statusLabel
                                            status="${task.isDone()}"/></span>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-primary"
                                       href="/user/task/update?taskId=${task.getId()}"><i class="fa fa-pencil-square-o"
                                                                                          aria-hidden="true"></i>
                                        Edit</a>
                                    <a class="btn btn-sm btn-danger" data-toggle="modal"
                                       href="#confirm_delete_${task.getId()}"><i class="fa fa-times"
                                                                                 aria-hidden="true"></i> Delete</a>
                                    <div class="modal fade" id="confirm_delete_${task.getId()}" role="dialog"
                                         aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure to delete todo ${task.getId()}
                                                        '${task.getTitle()}'?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/user/task/delete" method="post">
                                                        <input type="hidden" name="taskId" value="${task.getId()}">
                                                        <a href="#" class="btn" data-dismiss="modal">Cancel</a>
                                                        <button type="submit" class="btn btn-primary">Confirm</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="2">
                                <div align="center">Total = <span
                                        class="badge badge-dark">${requestScope.totalCount}</span></div>
                            </td>
                            <td colspan="2">
                                <div align="center">In Progress = <span
                                        class="badge badge-secondary">${requestScope.taskCount}</span></div>
                            </td>
                            <td colspan="2">
                                <div align="center">Done = <span
                                        class="badge badge-success">${requestScope.doneCount}</span></div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                    <c:if test="${empty requestScope.taskList}">
                        <div class="alert alert-info" role="alert">
                            <div align="center">Your todo list is empty !</div>
                        </div>
                    </c:if>

                    <c:if test="${not empty requestScope.taskList}">
                        <div align="center">
                            <button class="btn" onclick="javascript:window.print()">
                                <i class="icon-print"></i>
                                Print my todo list
                            </button>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>