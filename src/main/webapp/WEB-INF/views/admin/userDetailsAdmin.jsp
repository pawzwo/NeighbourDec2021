<%@ include file="headerAdmin.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-black">Admin - User Details</h1>
            </div>

            <div class="row">

                <div class="col-lg-6">
                    <!-- Personal Details-->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div class="row">
                                <div class="col-lg-10">
                                    <h6 class="m-0 font-weight-bold text-primary">Personal Details ${user.login}</h6>
                                </div>
                                <c:choose>
                                    <c:when test="${user.roles.stream().count()==1}">
                                        <div>
                                            <a href="/admin/makeAdmin/${user.id}"
                                               class="btn btn-danger btn-icon-split btn-sm">
                                                <span class="text">Admin</span>
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div>
                                            <a href="/admin/makeUser/${user.id}"
                                               class="btn btn-danger btn-icon-split btn-sm">
                                                <span class="text">User</span>
                                            </a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="card-body">
                            <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                <li>First name: ${user.firstName}</li>
                                <li>Last name: ${user.lastName}</li>
                                <li>Email: ${user.email}</li>
                                <li>Address: ${user.address}</li>
                                <c:choose>
                                    <c:when test="${user.roles.stream().count()>1}">
                                        <li>Role: Admin</li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>Role: User</li>
                                    </c:otherwise>
                                </c:choose>

                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <!-- Parking Spot -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">${user.login} Parking Spots</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <thead>
                                    <tr class="row-cols-sm-3">
                                        <th>Parking</th>
                                        <th>Spot</th>
                                        <th>Available/Occupied</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${user.parkingSpots}" var="spot">
                                            <c:set value="${spot.id}" var="spotId"/>
                                            <tr class="row-cols-sm-4">
                                                <td>${spot.parking}<br>
                                                    <div class="row justify-content-end">
                                                        <div>
                                                            <form:form action="/admin/parking/remove" method="post">
                                                                <input type="hidden" name="spotId" value="${spot.id}">
                                                                <input type="hidden" name="userId" value="${user.id}">
                                                                <button type="submit"
                                                                        class="btn btn-danger btn-icon-split btn-sm">Remove</button>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>${spot.place}</td>
                                                <td>
                                                    <c:forEach items="${availables}" var="available">
                                                        <c:if test="${available.parkingSpot.id == spotId}">
                                                            <table class="table table-responsive-sm">
                                                                <thead>
                                                                <tr class="row-cols-sm-3">
                                                                    <th>Shared</th>
                                                                    <th>Used by</th>
                                                                    <th></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                            ${available.start} to ${available.end}
                                                                    </td>
                                                                    <td>
                                                                        <c:forEach items="${occupied}" var="occupied">
                                                                            <c:if test="${occupied.availableId==available.id}">
                                                                                ${occupied.user.login}
                                                                                <br>
                                                                                ${occupied.start} to ${occupied.end}
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </td>
                                                                    <td>
                                                                        <button onclick="location.href='/available/disable/${available.id}'" type="submit" class="btn btn-warning btn-icon-split btn-sm">
                                                                            Stop Sharing
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                    </c:forEach>
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

                <div class="col-lg-6">
                    <!-- Add Parking Spot -->
                    <div class="card shadow mb-4" style="display: block">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Add Parking Spot</h6>
                        </div>
                        <div class="card-body">
                            <form:form action="/admin/parking/add/${user.id}" method="post" modelAttribute="spot"
                                       class="user">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="parking" class="form-control form-control-user" id="parking"
                                                    placeholder="Parking"/>
                                    </div>
                                    <div class="col-sm-6">
                                        <form:input path="place" class="form-control form-control-user" id="place"
                                                    placeholder="Place"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary rounded-pill btn-user btn-sm">Add Parking Spot
                                </button>
                            </form:form>
                        </div>
                    </div>


                </div>
                <div class="col-lg-6">
                    <!-- Used Parking Spot -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div class="row">
                                <div class="col-lg-9">
                                    <h6 class="m-0 font-weight-bold text-primary">Parking Spots used
                                        by ${user.login}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr class="row-cols-sm-4">
                                        <th>Parking</th>
                                        <th>Spot</th>
                                        <th>Occupied</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${occupiedByUser}" var="occupied">
                                        <tr class="row-cols-sm-4">
                                            <td>${occupied.parkingSpot.parking}</td>
                                            <td>${occupied.parkingSpot.place}</td>
                                            <td>
                                                    ${occupied.start} to ${occupied.end}
                                            </td>
                                            <td>
                                                <div class="row justify-content-end">
                                                    <div>
                                                        <form:form action="/occupied/disable" method="post">
                                                            <input type="hidden" name="occupiedId"
                                                                   value="${occupied.id}">
                                                            <button type="submit"
                                                                    class="btn btn-danger btn-icon-split">Release</button>
                                                        </form:form>
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

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->
<%@ include file="footerAdmin.jsp" %>