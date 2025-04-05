<%-- 
    Document   : Login
    Created on : 7 thg 1, 2025, 21:48:11
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login </title>
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
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="login" method="post">
                        <span class="login100-form-title p-b-26">
                            Welcome
                        </span>
                        <!--  <span class="login100-form-title p-b-48">
                            <i class="zmdi zmdi-font"></i>
                         </span>-->

                        <div class="wrap-input100 validate-input" ">
                            <input class="input100" type="text" name="user">
                            <span class="focus-input100" data-placeholder="User"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="pass">
                            <span class="focus-input100" data-placeholder="Password"></span>

                        </div>
                        <c:if test="${mssErr!=null}" >
                            <p class="text-danger">${mssErr}</p>
                        </c:if>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Login
                                </button>
                            </div>
                        </div><br>

                        <style>
                            .hidden {
                                display: none;
                            }
                        </style>
                        <!--recaptcha-->
                        <div id="recaptcha-container" class="g-recaptcha hidden" data-sitekey="6LdjEvUqAAAAAHLAhBPmrmSmAV4otZLVM4sr18HS"></div>
                        <!-------------->
                        <div class="text-center m-t-19">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/furni-store/login&response_type=code&client_id=454637358826-ucfl3hhghfmdb70epb0d03v5k9jbphlp.apps.googleusercontent.com&prompt=consent"
                               class="btn btn-lg">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                </svg>
                                <span class="ms-2 fs-6">Sign in with Google</span>
                            </a>

                            
                        </div>

                        <div class="text-center p-t-60">
                            <span class="txt1">
                                Don’t have an account?
                            </span>

                            <a class="txt2" href="Register.jsp">
                                Sign Up
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div

        <!--===============================================================================================-->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
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

        <!--        Handel recaptha-->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var loginButton = document.querySelector(".login100-form-btn");
                var recaptchaContainer = document.getElementById("recaptcha-container");
                var form = document.querySelector(".login100-form");

                loginButton.addEventListener("click", function (event) {
                    var recaptchaResponse = grecaptcha.getResponse();


                    if (recaptchaContainer.classList.contains("hidden")) {
                        event.preventDefault();
                        recaptchaContainer.classList.remove("hidden");

                    } else if (!recaptchaResponse) {
                        event.preventDefault();
                        alert("Vui lòng xác nhận reCAPTCHA trước khi đăng nhập!");
                    }
                });
            });
        </script>


    </body>
</html>