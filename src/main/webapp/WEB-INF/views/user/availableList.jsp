<%@ include file="headerUser.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-black">Parking Spots</h1>
            </div>
            <div class="row">

                <div class="col-lg-6">
                    <!-- Available Parking Spots -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Shared Parking Spots</h6>
                        </div>
                        <div class="card-body">
                            <c:if test="${CheckOccupDates != null}">
                                <div class="form-group row">
                                    <p style="color: #be2617; font-size: small">${CheckOccupDates}</p>
                                </div>
                            </c:if>
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr class="row-cols-sm-4">
                                        <th>Shared</th>
                                        <th>Occupied</th>
                                        <th>Parking</th>
                                        <th>Spot</th>
                                    </tr>
                                    </thead>
                                    <tbody id="Tab">
                                    <c:forEach items="${availSpots}" var="avail">
                                        <tr class="row-cols-sm-4">
                                            <td>
                                                <div class="row justify-content-around">
                                                    <div>${avail.start} to ${avail.end}</div>
                                                    <div>
                                                        <button class="btn btn-success btn-icon-split btn-sm" id="SelectBtn">Select</button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <c:forEach items="${avail.occupiedList}" var="occupiedAvail">
                                                    <div>${occupiedAvail.start} to ${occupiedAvail.end}</div>
                                                </c:forEach>
                                            </td>
                                            <td>${avail.parkingSpot.parking}</td>
                                            <td>${avail.parkingSpot.place}</td>
                                        </tr>
                                        <tr id="occupyForms">
                                            <td colspan="4" class="d-none clear">
                                                <div class="card shadow mb-4">
                                                    <div class="card-header py-3">
                                                        <h6 class="m-0 font-weight-bold text-primary">Select ${avail.parkingSpot.parking} ${avail.parkingSpot.place} ${avail.start} to ${avail.end}  </h6>
                                                    </div>
                                                    <div class="card-body">
                                                        <form:form action="/occupied/add" method="post" modelAttribute="occupiedNew" class="user">
                                                            <div class="form-group row">
                                                                <div class="col-sm-6">
                                                                    <label for="start">Select from:</label>
                                                                    <form:input path="start" type="date" id="start" name="start" class="form-control form-control-user"/>
                                                                    <form:errors path="start" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="end">Select to:</label>
                                                                    <form:input path="end" type="date" id="end" name="end" class="form-control form-control-user"/>
                                                                    <form:errors path="end" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                                                </div>
                                                                <div class="form-group d-none">
                                                                    <form:input path="availableId" id="availableId" name="availableId" value="${avail.id}" class="form-control form-control-user"/>
                                                                </div>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary btn-user btn-block">Select</button>
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
                <div class="col-lg-6">
                    <!-- Occupied Parking Spots -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">You are using parking spots</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <thead>
                                    <tr class="row-cols-sm-4">
                                        <th>Parking</th>
                                        <th>Spot</th>
                                        <th>Used</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${occupied}" var="occupied">
                                        <tr class="row-cols-sm-4">
                                            <td>${occupied.parkingSpot.parking}</td>
                                            <td>${occupied.parkingSpot.place}</td>
                                            <td>
                                                    ${occupied.start} to ${occupied.end}
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
<%@ include file="footerUser.jsp" %>