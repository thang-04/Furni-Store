<%-- 
    Document   : Mn_header
    Created on : 4 thg 3, 2025, 23:44:22
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 "style="font-size: 1.1rem" >${sessionScope.sessionLogin.userName}</span>
                    <c:choose>
                        <c:when test="${fn:startsWith(sessionLogin.image, 'http')}">
                            <img class="img-profile rounded-circle" src="${sessionLogin.image}" alt="Profile Picture">
                        </c:when>
                        <c:otherwise>
                            <img class="img-profile rounded-circle" src="images/${sessionLogin.image}" alt="Profile Picture">
                        </c:otherwise>
                    </c:choose>
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="profile?uID=${sessionScope.sessionLogin.uid}">
                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                        Profile
                    </a>
                    <a class="dropdown-item" href="home">
                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                        Shop
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="logout" >
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                    </a>
                </div>
            </li>

        </ul>

    </nav>
    <!-- End of Topbar -->
</html>
