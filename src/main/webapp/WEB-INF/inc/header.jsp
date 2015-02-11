<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"  trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
            <a href="/">
                <%--<img src="/resources/images/red6.png" alt="logo" class="logo-default" style="margin-top: 12px"/>--%>
                <img src="/resources/images/logo-shopian.png" alt="logo" class="logo-default" />
            </a>
            <div class="menu-toggler sidebar-toggler hide">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">

                <%--<c:import url="/WEB-INF/inc/header/notificationDropdown.jsp"/>--%>
                <%--<c:import url="/WEB-INF/inc/header/inboxDropdown.jsp"/>--%>
                <%--<c:import url="/WEB-INF/inc/header/todoDropdown.jsp"/>--%>
                <c:import url="/WEB-INF/inc/header/userLoginDropdown.jsp"/>

                <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                <li class="dropdown dropdown-quick-sidebar-toggler">
                    <a href="/j_spring_security_logout" class="dropdown-toggle">
                        <i class="icon-login"></i>
                    </a>
                </li>
                <!-- END QUICK SIDEBAR TOGGLER -->
            </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
