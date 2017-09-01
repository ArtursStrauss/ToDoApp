<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/header.jspf" %>
<%--content--%>

<div class="container">
    <div class="row">
        <div class="col-3">
            <%@ include file="./common/sidebar.jspf" %>
        </div>
        <div class="col-9">
            <div class="card bg-light mt-3 p-1">
                <div class="card-body">
                    <h1 class="card-title">My Todo list</h1>
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
                                <td><fmt:formatDate value="${task.getDueDate()}" pattern="dd/MM/yyyy"/></td>
                                <td>
                                    <!--<i class="icon-circle-arrow-<tl:priorityIcon priority="${task.getPriority()}"/>"></i>-->
                                        ${task.getPriority()}
                                </td>
                                <td>
                                    <!-- <span class="label <tl:statusStyle status="${task.isDone()}"/> "> <tl:statusLabel status="${task.isDone()}"/></span>-->
                                    ${task.isDone()}
                                </td>
                                <td>
                                    <a class="btn btn-mini btn-primary" href="/todos/update?todoId=${task.getId()}"><i
                                            class="icon-edit icon-white"></i> Edit</a>
                                    <a class="btn btn-mini btn-danger" data-toggle="modal"
                                       href="#confirm_delete_${task.getId()}"><i class="icon-remove icon-white"></i>
                                        Delete</a>
                                    <div class="modal hide" id="confirm_delete_${task.getId()}">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">×</button>
                                            <h3>Confirmation</h3>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure to delete todo ${task.getId()} '${task.getTitle()}'
                                                ?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <form action="/todos/delete.do" method="post">
                                                <input type="hidden" name="todoId" value="${task.getId()}">
                                                <a href="#" class="btn" data-dismiss="modal">Cancel</a>
                                                <button type="submit" class="btn btn-primary">Confirm</button>
                                            </form>
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
                                        class="badge badge-inverse">${requestScope.totalCount}</span></div>
                            </td>
                            <td colspan="2">
                                <div align="center">Todo = <span class="badge">${requestScope.todoCount}</span></div>
                            </td>
                            <td colspan="2">
                                <div align="center">Done = <span
                                        class="badge badge-success">${requestScope.doneCount}</span></div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                    <c:if test="${empty requestScope.taskList}">
                        <div class="alert alert-info">
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
<%@ include file="./common/footer.jspf" %>