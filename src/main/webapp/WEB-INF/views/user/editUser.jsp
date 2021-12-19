<%@ include file="headerUser.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-black">Edit Personal Details</h1>
            </div>
            <div class="row">

                <div class="col-lg-6">
                    <!-- Details form -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Your Personal Details</h6>
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="user" class="user">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="firstName" class="form-control form-control-user"/>
                                    </div>
                                    <div class="col-sm-6">
                                        <form:input path="lastName" class="form-control form-control-user"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:input path="email" class="form-control form-control-user"/>
                                </div>
                                <div class="form-group">
                                    <form:input path="address" class="form-control form-control-user"/>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="login" class="form-control form-control-user"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">Update</button>
                            </form:form>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->
<%@ include file="footerUser.jsp" %>