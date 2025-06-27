<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Product</title>
        <link rel="shortcut icon" href="favicon.png">

        <!-- Bootstrap 5 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Custom CSS -->
        <style>
            body {
                background-color: #f8f9fc;
            }
            .header {
                background-color: #4e73df;
                color: white;
                padding: 15px;
                font-size: 28px;
                font-weight: bold;
                padding-left: 100px;
            }
            .container {
                margin-top: 30px;
            }
            .card {
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .form-label {
                font-weight: 600;
            }
            img {
                width: 160px;
                height: 120px;
                margin-right: 10px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>

        <div class="header start-0 text-start m-l-100">Edit Product</div>

        <div class="container">
            <div class="card">
                <form action="updateProduct" method="post" >
                    <div class="row">
                        <!-- Left Column -->
                        <div class="col-md-6">
                            <!-- ID -->
                            <div class="mb-3">
                                <label class="form-label">Product ID</label>
                                <input value="${detailP.pId}" name="id" type="text" class="form-control" readonly>
                            </div>

                            <!-- Name -->
                            <div class="mb-3">
                                <label class="form-label">Product Name</label>
                                <input value="${detailP.pName}" name="name" type="text" class="form-control" required>
                            </div>

                            <!-- Price -->
                            <div class="mb-3">
                                <label class="form-label">Price</label>
                                <input value="${detailP.price}" name="price" type="number" step="0.01" min="0" class="form-control">
                            </div>

                            <!-- Quantity -->
                            <div class="mb-3">
                                <label class="form-label">Quantity</label>
                                <input value="${detailP.quantity}" name="quantity" type="number" min="1" class="form-control" required>
                            </div>

                            <!-- Import Date -->
                            <div class="mb-3">
                                <label class="form-label">Import Date</label>
                                <input value="${detailP.importDate}" name="importDate" type="date" class="form-control" required>
                            </div>

                            <!-- Category -->
                            <div class="mb-3">
                                <label class="form-label">Category</label>
                                <select name="categoryID" class="form-select" required>
                                    <c:forEach items="${listC}" var="o">
                                        <option value="${o.cId}" ${o.cId == detailP.categoryId ? 'selected' : ''}>${o.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Seller ID -->
                            <div class="mb-3">
                                <label class="form-label">Seller ID</label>
                                <input value="${detailP.sell_id}" name="sell_Id" type="number" class="form-control" readonly="">
                            </div>
                        </div>

                        <!-- Right Column -->
                        <div class="col-md-6">
                            <!-- Image -->
                            <div class="mb-3">
                                <label class="form-label">Product Images</label>
                                <div>
                                    <img src="${detailP.image}" alt="Product Image">
                                </div>
                                <!--<input name="image" type="file" class="form-control mt-2">-->
                                <input name="image" type="text" value="${detailP.image}" class="form-control" required>

                            </div>
                            <!-- Description -->
                            <div class="mb-3">
                                <label class="form-label">Description</label>
                                <textarea name="description" class="form-control" rows="4" required>${detailP.description}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Submit Button -->
                    <div class="text-end">
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                        <a href="manageProduct" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
