
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="favicon.png">

        <!-- Bootstrap 5 CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .profile-card {
                border-radius: 10px;
                padding: 20px;
            }
            .profile-img {
                width: 200px;
                height: 200px;
                border-radius: 50%;
                display: block;
                margin: 0 auto;
                object-fit: cover;
            }
        </style>
    </head>
    <body>

        <!-- Start Header/Navigation -->
        <c:import url="Header.jsp"></c:import>
            <!-- End Header/Navigation -->

            <!--chưa dùng file nên xóa enctype="multipart/form-data" của form-->

            <form action="profile?uid=${sessionLogin.uid}" method="POST" >

            <div class="container py-5">
                <div class="row justify-content-center g-4">

                    <!-- Profile Picture -->
                    <div class="col-md-3">
                        <div class="profile-card text-center">
                            <h5>Profile Picture</h5>

                            <img id="previewImage" src="" alt="Profile Picture" class="profile-img mb-3">


                            <!-- Input file tam thoi comment -->
                            <input type="file" class="form-control" id="fileInput" name="file" placeholder="Enter photo">
                        </div>
                    </div>

                    <!-- Profile Information Form -->
                    <div class="col-md-5">
                        <div class="profile-card">
                            <h5 class="text-center">YOUR PROFILE</h5>
                            <div class="mb-3">
                                <label class="form-label">Username</label>
                                <input type="text" class="form-control" name="username" value="${sessionLogin.userName}" >
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Full Name</label>
                                <input type="text" class="form-control" name="fullName" value="${sessionLogin.fullName}">
                            </div>
                            <!-- Role & Address trên cùng một hàng -->
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Role</label>
                                    <input type="text" class="form-control" name="role" value="${sessionLogin.roleId == 0 ? "User" : (sessionLogin.roleId == 1 ? "Admin" : (sessionLogin.roleId == 2 ? "Seller" : "Unknown"))}" readonly>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Address</label>
                                    <input type="text" class="form-control" name="address" value="${sessionLogin.address}">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email Address</label>
                                <input type="email" class="form-control" name="email" value="${sessionLogin.email}">
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Phone Number</label>
                                    <input type="text" class="form-control" name="phone" value="${sessionLogin.phone}">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Birthdate</label>
                                    <input type="date" class="form-control" name="birthDay" value="${not empty sessionLogin.birthDay ? sessionLogin.birthDay : ''}">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary w-100" ">Save Changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>


    </body>
    <!-- Start Footer Section -->
    <footer class="footer-section">
        <div class="container relative">

            <div class="row g-5 mb-5">
                <div class="col-lg-4">
                    <div class="mb-4 footer-logo-wrap"><a href="#" class="footer-logo">Furni<span>.</span></a></div>
                    <p class="mb-4">Donec facilisis quam ut purus rutrum lobortis. Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique. Pellentesque habitant</p>

                    <ul class="list-unstyled custom-social">
                        <li><a href="#"><span class="fa fa-brands fa-facebook-f"></span></a></li>
                        <li><a href="#"><span class="fa fa-brands fa-twitter"></span></a></li>
                        <li><a href="#"><span class="fa fa-brands fa-instagram"></span></a></li>
                        <li><a href="#"><span class="fa fa-brands fa-linkedin"></span></a></li>
                    </ul>
                </div>

                <div class="col-lg-8">
                    <div class="row links-wrap">
                        <div class="col-6 col-sm-6 col-md-3">
                            <ul class="list-unstyled">
                                <li><a href="#">About us</a></li>
                                <li><a href="#">Services</a></li>
                                <li><a href="#">Blog</a></li>
                                <li><a href="#">Contact us</a></li>
                            </ul>
                        </div>

                        <div class="col-6 col-sm-6 col-md-3">
                            <ul class="list-unstyled">
                                <li><a href="#">Support</a></li>
                                <li><a href="#">Knowledge base</a></li>
                                <li><a href="#">Live chat</a></li>
                            </ul>
                        </div>

                        <div class="col-6 col-sm-6 col-md-3">
                            <ul class="list-unstyled">
                                <li><a href="#">Jobs</a></li>
                                <li><a href="#">Our team</a></li>
                                <li><a href="#">Leadership</a></li>
                                <li><a href="#">Privacy Policy</a></li>
                            </ul>
                        </div>

                        <div class="col-6 col-sm-6 col-md-3">
                            <ul class="list-unstyled">
                                <li><a href="#">Nordic Chair</a></li>
                                <li><a href="#">Kruzo Aero</a></li>
                                <li><a href="#">Ergonomic Chair</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>

            <div class="border-top copyright">
                <div class="row pt-4">
                    <div class="col-lg-6">
                        <p class="mb-2 text-center text-lg-start">Copyright &copy;<script>document.write(new Date().getFullYear());</script>. All Rights Reserved. &mdash; Designed with love by <a href="https://untree.co">Untree.co</a>  Distributed By <a href="https://themewagon.com">ThemeWagon</a> <!-- License information: https://untree.co/license/ -->
                        </p>
                    </div>

                    <div class="col-lg-6 text-center text-lg-end">
                        <ul class="list-unstyled d-inline-flex ms-auto">
                            <li class="me-4"><a href="#">Terms &amp; Conditions</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                        </ul>
                    </div>

                </div>
            </div>

        </div>
    </footer>
    <!-- End Footer Section -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
                            document.addEventListener("DOMContentLoaded", function () {
                                var imagePath = "${sessionLogin.image}";
                                var imageElement = document.getElementById("previewImage");

                                // Kiểm tra xem imagePath có phải là URL hay không
                                if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                                    imageElement.src = imagePath; // Dùng URL trực tiếp
                                } else {
                                    imageElement.src = "images/" + imagePath; // Thêm thư mục images nếu chỉ có tên file
                                }
                            });
    </script>
</html>
