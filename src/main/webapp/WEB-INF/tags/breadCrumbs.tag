<%@ attribute name="titleShort" required="true" type="java.lang.String" %>
<%@ attribute name="titleLong" required="true" type="java.lang.String" %>
<%@ attribute name="showCalendar" required="false" type="java.lang.Boolean" %>
<%@tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<!-- BEGIN PAGE HEADER-->--%>
<h3 class="page-title">${titleShort}&nbsp;
    <small>${titleLong}</small>
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
        <li><a href="#">${titleShort}</a></li>
    </ul>
    <c:if test="${showCalendar}">
    <div class="page-toolbar">
        <div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height grey-salt" data-placement="top"
             data-original-title="Change dashboard date range">
            <i class="icon-calendar"></i>&nbsp;
            <span class="thin uppercase visible-lg-inline-block">&nbsp;</span>&nbsp;
            <i class="fa fa-angle-down"></i>
        </div>
    </div>
    </c:if>
</div>
<%--<!-- END PAGE HEADER-->--%>