<%-- 
    Document   : MangerOrder
    Created on : 12 thg 3, 2025, 14:47:14
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

        <title>Admin Dashboard</title>
        <link rel="shortcut icon" href="favicon.png">

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="">

            <jsp:include page="Mn-left.jsp"/>

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <jsp:include page="Mn_header.jsp"/>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h2>History <b>Orders</b></h2>
                                    </div>
                                    <div class="col-sm-6 text-right">
                                        <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
                                            <i class="fas fa-trash"></i> <span>Delete</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="selectAll">
                                                    <label for="selectAll"></label>
                                                </span>
                                            </th>
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
                                                <td>
                                                    <span class="custom-checkbox">
                                                        <input type="checkbox" id="checkbox${o.OId}" name="options[]" value="${o.OId}">
                                                        <label for="checkbox${o.OId}"></label>
                                                    </span>
                                                </td>
                                                <td>${o.OId}</td>
                                                <td>${o.date}</td>
                                                <td>${o.totalMoney}$</td>
                                                <td>${o.userId}</td>
                                                <td>${o.status}</td>
                                                <td style="white-space: nowrap;">
                                                    <a href="viewOrderDetail?oId=${o.OId}" class="btn btn-sm " data-toggle="tooltip" title="View Details">
                                                        <i class="fas fa-eye small"></i>
                                                    </a>
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

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2025</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>

        <!-- Custom scripts for checkbox and delete functionality -->
        <script>
            $(document).ready(function() {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();
                
                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function() {
                    if(this.checked) {
                        checkbox.each(function() {
                            this.checked = true;                        
                        });
                    } else {
                        checkbox.each(function() {
                            this.checked = false;                        
                        });
                    } 
                });
                
                checkbox.click(function() {
                    if(!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
                
                // Delete button click event
                $(".delete-btn").click(function(e) {
                    e.preventDefault();
                    var id = $(this).data('id');
                    $("#deleteIds").val(id);
                    $("#deleteEmployeeModal").modal('show');
                });
            });
        </script>
    </body>
</html>