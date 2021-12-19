<%@ include file="headerUser.jsp" %>


        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-black">User Dashboard</h1>
            </div>
            <div class="row">

                <div class="col-lg-6">
                    <!-- Parking Spot -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Your Parking Spots</h6>
                        </div>
                        <div class="card-body">
                            <c:if test="${CheckAvailDates!=null}">
                                <div class="form-group row">
                                    <p style="color: #be2617; font-size: small">${CheckAvailDates}</p>
                                </div>
                            </c:if>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <thead>
                                    <tr class="row-cols-sm-3">
                                        <th>Parking</th>
                                        <th>Spot</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody id="Tab">
                                    <c:forEach items="${user.parkingSpots}" var="avail">
                                        <c:if test="${avail.disable==0}">
                                            <c:set value="${avail.id}" var="spotId"/>
                                            <tr class="row-cols-sm-4">
                                                <td>
                                                    <div class="row justify-content-around">
                                                        <div>${avail.parking}</div>
                                                        <div>
                                                            <button class="btn btn-success btn-icon-split btn-sm" id="ShareBtn">Share</button>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>${avail.place}</td>
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
                                                                                ${occupied.user.firstName} ${occupied.user.lastName} <br>
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
                                            <tr id="shareForms">
                                                <td colspan="3" class="d-none clear">
                                                    <div class="card shadow mb-4">
                                                        <div class="card-header py-3">
                                                            <h6 class="m-0 font-weight-bold text-primary">Share</h6>
                                                        </div>
                                                        <div class="card-body">
                                                            <form:form action="/available/add/${avail.id}" method="post" modelAttribute="available" class="user">
                                                                <div class="form-group row">
                                                                    <div class="col-sm-6">
                                                                        <label for="start">Share from:</label>
                                                                        <form:input path="start" type="date" id="start" name="start" class="form-control form-control-user"/>
                                                                        <form:errors path="start" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="end">Share to:</label>
                                                                        <form:input path="end" type="date" id="end" name="end" class="form-control form-control-user"/>
                                                                        <form:errors path="end" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                                                    </div>
                                                                    <div class="form-group d-none">
                                                                        <form:input path="parkingSpot" id="spot" name="spot" value="${avail.id}" class="form-control form-control-user"/>
                                                                    </div>
                                                                </div>
                                                                <button type="submit" class="btn btn-primary btn-user btn-sm">Share</button>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <!-- Used Parking Spot -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div class="row justify-content-around">
                                <div class="col-lg-10">
                                    <h6 class="m-0 font-weight-bold text-primary">You are using parking spots</h6>
                                </div>
                                    <button onclick="location.href='/available/list'" type="button" class="btn btn-primary rounded-pill btn-user btn-sm">Use</button>
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
                                                            <button type="submit" class="btn btn-danger btn-icon-split btn-sm">
                                                                Release
                                                            </button>

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

            <div class="row">

                <div class="col-lg-6">
                    <!-- Personal Details -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div class="row justify-content-around">
                                <div class="col-lg-10">
                                    <h6 class="m-0 font-weight-bold text-primary">Personal Details</h6>
                                </div>
                                <button onclick="location.href='/user/edit'" type="button" class="btn btn-primary rounded-pill btn-user btn-sm">Edit</button>
                            </div>
                        </div>
                        <div class="card-body">
                            <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                <p>First name: ${user.firstName}</p>
                                <p>Last name: ${user.lastName}</p>
                                <p>Email: ${user.email}</p>
                                <p>Address: ${user.address}</p>
                            </ul>
                        </div>
                    </div>

                </div>

                <div class="col-lg-6">


                </div>

            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->
<%@ include file="footerUser.jsp" %>