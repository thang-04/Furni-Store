<%-- 
    Document   : MangerProduct
    Created on : 3 thg 3, 2025, 15:14:17
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
        <div id="wrapper">

            <jsp:include page="Mn-left.jsp"/>

            <!-- End of Sidebar -->

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
                                        <h2>Manage <b>Product</b></h2>
                                    </div>
                                    <div class="col-sm-6">
                                        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
                                            <i class="fas fa-plus"></i> <span>Add New Product</span>
                                        </a>
                                        <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
                                            <i class="fas fa-trash"></i> <span>Delete</span>
                                        </a>

                                    </div>
                                </div>
                            </div>
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
                                        <th>Name</th>
                                        <th>Image</th>
                                        <th>Price</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listP}" var="p">
                                        <tr>
                                            <td>
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                                    <label for="checkbox1"></label>
                                                </span>
                                            </td>
                                            <td>${p.pId}</td>
                                            <td>${p.pName}</td>
                                            <td>
                                                <img class="h-50 w-25" src="${p.image}">
                                            </td>
                                            <td>${p.price}$</td>
                                            <td style="white-space: nowrap;">
                                                <a href="updateProduct?pId=${p.pId}" class="btn btn-sm " data-toggle="tooltip" title="Edit">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                <a href="#" class="btn btn-sm delete-btn" data-id="${p.pId}" data-toggle="tooltip" title="Delete">
                                                    <i class="fas fa-trash-alt"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!--paging-->
                            <div class="clearfix">
                                <div class="hint-text">Showing <b>${startItem}</b> to <b>${endItem}</b> out of <b>${totalProducts}</b> entries</div>
                                <ul class="pagination">
                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                        <a href="manageProduct?index=${currentPage - 1}" class="page-link">Previous</a>
                                    </li>
                                    <c:forEach var="i" begin="1" end="${count}">
                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                            <a href="manageProduct?index=${i}" class="page-link">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage == count ? 'disabled' : ''}">
                                        <a href="manageProduct?index=${currentPage + 1}" class="page-link">Next</a>
                                    </li>
                                </ul>
                            </div>
                            <!--end paging-->
                        </div>
                    </div>
                    <!-- Edit Modal HTML -->
                    <div id="addEmployeeModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="addProduct" method="post">
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Add Product</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input name="name" type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Image</label>
                                            <input name="image" type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Price</label>
                                            <input name="price" type="number" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Quantity</label>
                                            <input name="quantity" type="number" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Import Date</label>
                                            <input name="importDate" type="date" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="description" class="form-control" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Category</label>
                                            <select name="categoryID" class="form-select" required>
                                                <c:forEach items="${listC}" var="c">
                                                    <option value="${c.cId}">${c.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>User ID</label>
                                            <input name="user_Id"  value="${sessionScope.dataUser.uid}" readonly>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                        <input type="submit" class="btn btn-success" value="Add">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Edit Modal HTML -->
                    <div id="editEmployeeModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form>
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Edit Employee</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input type="email" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Address</label>
                                            <textarea class="form-control" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input type="text" class="form-control" required>
                                        </div>					
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                        <input type="submit" class="btn btn-info" value="Save">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Delete Modal HTML -->
                    <div id="deleteEmployeeModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form>
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Delete Product</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <h6>Are you sure you want to delete this product?</h6>
                                        <p class="text-warning">This action cannot be undone !!</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <a href="#" id="confirmDelete" class="btn btn-danger">Delete</a>
                                    </div>
                                </form>
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

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!--         Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!--         Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

<!--         Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!--         Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
        <script>
            $(document).ready(function () {
                $(".delete-btn").click(function () {
                    var pId = $(this).data("id");
                    $("#confirmDelete").attr("href", "delete?pId=" + pId);
                    $("#deleteEmployeeModal").modal("show");
                });
            });
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
            });



        </script>
    </body>

</html>
