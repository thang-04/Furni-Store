<%-- 
    Document   : Header
    Created on : 9 thg 1, 2025, 16:42:00
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style_0.css" rel="stylesheet">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <title>Furni Store</title>
    </head>
    <nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

        <div class="container">
            <a class="navbar-brand" href="home">Furni<span>.</span></a>


            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <%
           String currentPage = request.getRequestURI();
           currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1);
            %>
            <div class="collapse navbar-collapse" id="navbarsFurni">
                <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                    <li class="nav-item <%= "Home.jsp".equals(currentPage) ? "active" : "" %>">
                        <a class="nav-link" href="home">Home</a>
                    </li>
                    <li class="nav-item <%= "Shop.jsp".equals(currentPage) ? "active" : "" %>">
                        <a class="nav-link" href="shop">Shop</a>
                    </li>
                    <li class="nav-item <%= "Contact.jsp".equals(currentPage) ? "active" : "" %>">
                        <a class="nav-link" href="Contact.jsp">Contact us</a>
                    </li>
                </ul>
            </div>

            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <c:if test="${sessionScope.sessionLogin==null}">
                    <li><a class="nav-link" href="login"><img src="images/user.svg"></a></li>
                        </c:if>
                <li><a id="cartSize" class="nav-link" href="view"><img src="images/cart.svg">(${size!=null?size:0})</a></li>

            </ul>
            <c:if test="${sessionScope.sessionLogin != null}">
                <div class="row_dropdow justify-content-center end">
                    <div class="col-md-5">
                        <div class="dropdown custom-dropdown">
                            <a href="#" data-toggle="dropdown" class="d-flex align-items-center dropdown-link text-left" 
                               aria-haspopup="true" aria-expanded="false" data-offset="0, 20">
                                <div class="profile-pic mr-3">
                                    <img src="images/undraw_profile.svg" alt="Image">
                                </div>
                                <div class="profile-info">
                                    <h3>${sessionScope.sessionLogin.userName}</h3>
                                </div>
                            </a>

                            <div class="dropdown-menu active" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="profile?uID=${sessionScope.sessionLogin.uid}">
                                    <span class="icon icon-user"></span> User Profile
                                </a>
<!--                                <a class="dropdown-item" href="#">
                                    <span class="icon icon-cog"></span> Settings
                                </a>-->
                                <c:if test="${sessionScope.sessionLogin.roleId!=0}">
                                    <a class="dropdown-item" href="manageProduct?uID=${sessionScope.sessionLogin.uid}">
                                        <span class="icon icon-list"></span> Manager 
                                    </a>
                                </c:if>
                                <a class="dropdown-item" href="logout">
                                    <span class="icon icon-sign-out"></span> Log out
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

</nav>


<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<!--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->

<script>
    function addToCart(productId) {
        $.ajax({
            url: "buy",
            type: "POST",
            data: {id: productId, num: 1},
            success: function () {
                updateCartSize(); // Cập nhật ngay khi thêm sản phẩm
            },
            error: function (xhr, status, error) {
                console.error("Lỗi khi thêm vào giỏ hàng: " + error);
            }
        });
    }

    function updateCartSize() {
        $.ajax({
            url: "sizeCart",
            type: "GET",
            success: function (response) {
                $("#cartSize").html('<img src="images/cart.svg">(' + response.cartSize + ')');
            },
            error: function (xhr, status, error) {
                console.error("Lỗi khi lấy số lượng giỏ hàng: " + error);
            }
        });
    }

    $(document).ready(function () {
        updateCartSize(); // Gọi ngay khi tải trang
    });
</script>
</html>
