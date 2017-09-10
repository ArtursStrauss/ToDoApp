<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="common/header.jspf" %>

<%--content--%>

<div class="container">
    <div class="row">
        <div class="col-3">
            <%@ include file="./common/sidebar.jspf" %>
        </div>
        <div class="col-9">
            <div class="card bg-light mt-3">
                <div class="card-body">
                    <h1 class="card-title">About Todo App</h1>
                    <table class="table table-bordered table-striped">
                        <tbody>
                        <tr>
                            <td colspan="2"><strong>About</strong></td>
                        </tr>
                        <tr>
                            <td>Home page</td>
                            <td>
                                <a href="https://github.com/ArtursStrauss/ToDoApp">https://github.com/ArtursStrauss/ToDoApp</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><strong>Frameworks</strong></td>
                        </tr>
                        <tr>
                            <td><a href="http://spring.io/">Spring</a></td>
                            <td>4.3.10</td>
                        </tr>
                        <tr>
                            <td><a href="http://www.hibernate.org/">Hibernate</a></td>
                            <td>4.3.5</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<%--end content--%>
<%@ include file="./common/footer.jspf" %>
