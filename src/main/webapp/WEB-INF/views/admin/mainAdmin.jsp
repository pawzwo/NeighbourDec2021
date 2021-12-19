<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Admin Panel</h1>
    </div>
    <div class="row">

        <div class="col-lg-12">
            <!-- Parking Spot -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Users</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable">
                            <thead>
                            <tr class="row-cols-sm-4">
                                <th>Id</th>
                                <th>Login</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr class="row-cols-sm-4">
                                    <td>
                                            ${user.id}
                                    </td>
                                    <td>
                                            ${user.login}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.enabled == 1}">Active</c:when>
                                            <c:otherwise>Disabled</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <a href="/admin/sendMessage/${user.id}"
                                                   class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="text">Send message</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/user/details/${user.id}"
                                                   class="btn btn-success btn-icon-split btn-sm">
                                                    <span class="text">Details</span>
                                                </a>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${user.enabled==1}">
                                                        <form:form action="/admin/user/disable" method="post">
                                                            <input type="hidden" name="userId" value="${user.id}">
                                                            <button type="submit"
                                                                    class="btn btn-danger btn-icon-split btn-sm"/>
                                                            <span class="text">Disable</span>
                                                        </form:form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form action="/admin/user/enable/" method="post">
                                                            <input type="hidden" name="userId" value="${user.id}">
                                                            <button type="submit"
                                                                    class="btn btn-warning btn-icon-split btn-sm"/>
                                                            <span class="text">Enable</span>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">

        <div class="col-lg-12">
            <!-- Parking Spots -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Parking Spots</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr class="row-cols-sm-4">
                                <th>Id</th>
                                <th>Parking: Spot</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${spots}" var="spot">
                                <tr class="row-cols-sm-4">
                                    <td>${spot.id}</td>
                                    <td>${spot.parking}: ${spot.place}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${spot.disable == 0}">Active</c:when>
                                            <c:otherwise>Disabled</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <c:choose>
                                                    <c:when test="${spot.disable==0}">
                                                        <form:form action="/admin/parking/disable" method="post">
                                                            <input type="hidden" name="spotId" value="${spot.id}">
                                                            <button type="submit"
                                                                    class="btn btn-danger btn-icon-split btn-sm"/>
                                                            <span class="text">Disable</span>
                                                        </form:form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form action="/admin/parking/enable" method="post">
                                                            <input type="hidden" name="spotId" value="${spot.id}">
                                                            <button type="submit"
                                                                    class="btn btn-success btn-icon-split btn-sm"/>
                                                            <span class="text">Enable</span>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="footerAdmin.jsp" %>