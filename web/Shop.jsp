<%-- 
    Document   : Shop
    Created on : 7 thg 1, 2025, 23:25:53
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
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
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/style_1.css">

    </head>

    <body>

        <!-- Start Header/Navigation -->
        <c:import url="Header.jsp"></c:import>
            <!-- End Header/Navigation -->



            <!-- Start Hero Section -->
            <div class="hero">
                <div class="container">
                    <div class="row justify-content-between">
                        <div class="col-lg-5">
                            <div class="intro-excerpt">
                                <h1>Shop</h1>
                            </div>
                        </div>
                        <div class="col-lg-7"></div>
                    </div>
                </div>
            </div>
            <!-- End Hero Section -->


            <div class="untree_co-section product-section before-footer-section">
                <div class="container">

                    <div class="row">
                        <form action="search" method="post" class="row g-3"style="padding-bottom:50px;">
                            <div class="col-auto">
                                <input type="text" class="form-control" name="search" value="${requestScope.value}" placeholder="Searching">
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-primary" type="submit">
                                <span class="icon-search"></span>
                            </button>
                        </div>
                    </form>



                    <!-- Filter Section -->
                    <div class="col-lg-2">
                        <div class="filter-section">
                            <h5>FILTER <span class="icon-filter"></span></h5>
                            <hr>

                            <div class="filter-category">
                                <h6>BRAND</h6>
                                <ul style="list-style-type: none; padding-left: 0;">
                                    <li><input type="checkbox" id="all" name="brand" value="all"> <label for="all">ALL </label></li>
                                        <c:forEach items="${listC}" var="c">
                                        <li><input type="checkbox" id="all" name="brand" value="all"> <label for="all">${c.categoryName}</label></li>
                                        </c:forEach>

                                </ul>
                            </div>
                            <hr>
                            <div class="filter-reviews">
                                <h6>AVERAGE REVIEWS</h6>
                                <ul style="list-style-type: none; padding-left: 0;">
                                    <li><input type="checkbox" id="five-stars" name="reviews" value="5"> <label for="five-stars">Above <span class="stars">★★★★★</span></label></li>
                                    <li><input type="checkbox" id="four-stars" name="reviews" value="4"> <label for="four-stars">Above <span class="stars">★★★★☆</span></label></li>
                                    <li><input type="checkbox" id="three-stars" name="reviews" value="3"> <label for="three-stars">Above <span class="stars">★★★☆☆</span></label></li>
                                    <li><input type="checkbox" id="two-stars" name="reviews" value="2"> <label for="two-stars">Above <span class="stars">★★☆☆☆</span></label></li>
                                </ul>
                            </div>

                            <!---------------fix here---------------------------------------------------------------->

                            <div class="row-cols-auto ">
                                <button class="btn btn-filter">
                                    <span class="icon-check"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- End Filter Section -->


                    <!-- Product Listing Section -->
                    <div class="col-lg-10">

                        <div class="row">
                            <!-- Start Product Column -->

                            <c:forEach items="${listP}" var="p">
                                <div class="col-12 col-md-6 col-lg-4 mb-5 ">
                                    <div class="product-item">
                                        <a onclick="openModal(${p.pId}, '${p.image}', '${p.pName}', ${p.price}, `${fn:escapeXml(p.description)}`)">
                                            <img src="${p.image}" class="img-fluid product-thumbnail">
                                            <h3 class="product-title">${p.pName}</h3>
                                            <strong class="product-price">$${p.price}</strong>
                                        </a>
                                        <span class="icon-cross">
                                            <img src="images/cross.svg" class="img-fluid" onclick="addToCart('${p.pId}', 1)">
                                        </span>
                                    </div>
                                </div>

                            </c:forEach>

                            <!-- End Product Column -->

                        </div>
                    </div> 
                </div>
            </div>
        </div>



        <!-- Start Footer Section -->
        <footer class="footer-section">
            <div class="container relative">

                <div class="sofa-img">
                    <img src="images/sofa.png" alt="Image" class="img-fluid">
                </div>

                <div class="row">
                    <div class="col-lg-8">
                        <div class="subscription-form">
                            <h3 class="d-flex align-items-center"><span class="me-1"><img src="images/envelope-outline.svg" alt="Image" class="img-fluid"></span><span>Subscribe to Newsletter</span></h3>

                            <form action="#" class="row g-3">
                                <div class="col-auto">
                                    <input type="text" class="form-control" placeholder="Enter your name">
                                </div>
                                <div class="col-auto">
                                    <input type="email" class="form-control" placeholder="Enter your email">
                                </div>
                                <div class="col-auto">
                                    <button class="btn btn-primary">
                                        <span class="fa fa-paper-plane"></span>
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

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


        <style>

            .product-thumbnail {
                width: 100%;
                height: 305px;
                object-fit: inherit;
            }

        </style>

        <!--modelbox-->

        <div class="modal fade" id="modal_box" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                    <div class="modal_body">
                        <div class="container">
                            <div class="row">
                                <!-- Hình ảnh sản phẩm -->
                                <div class="col-lg-5 col-md-5 col-sm-12">
                                    <div class="modal_tab">
                                        <div class="tab-content product-details-large">
                                            <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                <div class="modal_tab_img">
                                                    <a href="#"><img id="modal_image1" src="" alt=""></a>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                <div class="modal_tab_img">
                                                    <a href="#"><img id="modal_image2" src="" alt=""></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal_tab_button">
                                            <ul class="nav product_navactive" role="tablist">
                                                <li>
                                                    <a style="width:100px" href="#tab1" class="nav-link active" data-toggle="tab" role="tab"
                                                       aria-controls="tab1" aria-selected="false">
                                                        <img id="modal_thumb1" src="" alt="">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a style="width:100px" href="#tab2" class="nav-link" data-toggle="tab" role="tab"
                                                       aria-controls="tab2" aria-selected="false">
                                                        <img id="modal_thumb2" src="" alt="">
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <!-- Thông tin sản phẩm -->
                                <div class="col-lg-7 col-md-7 col-sm-12">
                                    <div class="modal_right">
                                        <div class="modal_title mb-10">
                                            <h2 id="modal_name"></h2>
                                        </div>
                                        <div class="modal_price mb-10">
                                            <span class="new_price">Rs. <span id="modal_salePrice"></span>$</span>
                                        </div>
                                        <div class="modal_description mb-15">
                                            <p id="modal_description"></p>
                                        </div>
                                        <div class="variants_selects">
                                            <div class="variants_size">
                                                <h2>Supplier: <span id="modal_companyName"></span></h2><br/>
                                            </div>
                                            <!--                                            <div class="variants_fragrance">
                                                                                            <h2>Size</h2>
                                                                                            <select id="modal_size_select" class="select_option" style="padding: 10px 0"></select>
                                                                                        </div>-->
                                            <div class="modal_add_to_cart">
                                                <form id="modal_form">
                                                    <input id="quantity" name="quantity" type="number" min="1" max="100" step="1" value="1">
                                                    <input id="modal_id" name="id" type="hidden">
                                                    <input id="role" name="role" type="hidden" value="add">
                                                    <button id="modal_add_button" type="submit">Add to cart</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div> 
                        </div> 
                    </div>
                </div>
            </div> 
        </div> 




        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/click-event.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



    </body>

</html>
