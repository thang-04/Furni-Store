<%-- 
    Document   : Register
    Created on : 7 thg 1, 2025, 21:15:07
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="shortcut icon" href="favicon.png">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">

        <!--===============================================================================================-->
    </head>
    <body>
        <div class="limiter"> 
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="signup" method="post">
                        <span class="login100-form-title p-b-26">
                            Create Account
                        </span>

                        <div class="wrap-input100 validate-input" data-validate="Enter your username">
                            <input class="input100" type="text" name="userName" required>
                            <span class="focus-input100" data-placeholder="Username"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter your full name">
                            <input class="input100" type="text" name="fullName" required>
                            <span class="focus-input100" data-placeholder="Full Name"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter email">
                            <input class="input100" type="email" name="email" required>
                            <span class="focus-input100" data-placeholder="Email"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="pass" required>
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Re-enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="repass" required>
                            <span class="focus-input100" data-placeholder="Re-password"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter your address">
                            <input class="input100" type="text" name="address" required>
                            <span class="focus-input100" data-placeholder="Address"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter your birthday">
                            <input class="input100" type="date" name="birthday" required>                        
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter your phone number">
                            <input class="input100" type="text" name="phone" required>
                            <span class="focus-input100" data-placeholder="Phone"></span>
                        </div>

<!--                        <div class="wrap-input100 validate-input" data-validate="Upload profile image">
                            <input class="input100" type="text" name="image" placeholder="Image URL">
                            <span class="focus-input100" data-placeholder="Image URL"></span>
                        </div>-->

                        <c:if test="${mssErr!=null}">
                            <p class="text-danger">${mssErr}</p>
                        </c:if>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Register
                                </button>
                            </div>
                        </div>

                        <div class="text-center p-t-60">
                            <span class="txt1">
                                Already have an account?
                            </span>
                            <a class="txt2" href="Login.jsp">
                                Login
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main_login.js"></script>

    </body>
</html>
