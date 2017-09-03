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
            <div class="card bg-light mt-3">
                <div class="card-body">
                    <h1 class="card-title">Create Task</h1>

                    <form id="createTaskForm" action="/user/todos/new" method="post">
                        <div class="form-group row">
                            <label class="text-right col-form-label col-2" for="title">Title:</label>
                            <input type="text" id="title" name="title" class="form-control col-4" required="required"
                                   autofocus="autofocus"/>
                        </div>
                        <div class="form-group row">
                            <label class="text-right col-form-label col-2" for="dueDate">Due date:</label>
                            <input type="text" id="dueDate" name="dueDate" class="form-control col-4" value="${requestScope.today}"
                                   required="required"/>
                        </div>
                        <div class="form-group row">
                            <label class="text-right col-form-label col-2" for="priority">Priority:</label>
                            <select id="priority" name="priority" class="custom-select">
                                <option value="LOW">Low</option>
                                <option value="MEDIUM">Medium</option>
                                <option value="HIGH">High</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="fa fa-check" aria-hidden="true"></i>
                            Create
                        </button>
                        <button type="button" class="btn" onclick="history.go(-1)"><i class="fa fa-trash"
                                                                                      aria-hidden="true"></i>
                            Cancel
                        </button>
                        <script>
                            $('#dueDate').datepicker({
                                format: 'yyyy-mm-dd'
                            });
                        </script>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--end content--%>
<%@ include file="../common/footer.jspf" %>