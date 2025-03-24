<%-- 
    Document   : HistoryOrder
    Created on : 23 thg 3, 2025, 13:33:13
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="favicon.png">

        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <title>Furni Store</title>

    </head>

    <body id="page-top">

        <!-- Start Header/Navigation -->
        <c:import url="Header.jsp"></c:import>
            <!-- End Header/Navigation -->


            <!-- Page Wrapper -->
            <div id="">

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>History <b>Orders</b></h2>
                                </div>

                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Date</th>
                                        <th>Total Money</th>
                                        <th>User ID</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listO}" var="o">
                                    <tr>
                                        <td>${o.OId}</td>
                                        <td>${o.date}</td>
                                        <td>${o.totalMoney}$</td>
                                        <td>${o.userId}</td>
                                        <td>${o.isPay==0?"Unpaid":"Paied"}</td>
                                        <td style="white-space: nowrap;">
                                            <c:if test="${o.isPay==0}">
                                                <form action="payment" method="post">
                                                    <input type="hidden" name="totalBill" value="${o.totalMoney}">
                                                    <input type="hidden" name="payID" value="${o.OId}">
                                                    <button type="submit" class="btn btn-sm"><i class="fas fa-check"></i></button>
                                                </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="deleteOrders" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Delete Orders</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these records?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                    <input type="hidden" name="deleteIds" id="deleteIds" value="">
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="js/sb-admin-2.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/tiny-slider.js"></script>
<script src="js/custom.js"></script>
<script src="js/click-event.js"></script>


</body>
</html>