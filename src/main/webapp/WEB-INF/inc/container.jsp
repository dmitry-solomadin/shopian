<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <tags:sidebarMenu selectedItem="dashboard" />
        </div>
    </div>
    <!-- END SIDEBAR -->

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            Widget settings form goes here
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">Save changes</button>
                            <button type="button" class="btn default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->


            <c:import url="/WEB-INF/inc/container/styleCustomizer.jsp"/>


            <!-- BEGIN PAGE HEADER-->
            <h3 class="page-title">
                Dashboard <small>reports & statistics</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                </ul>
                <div class="page-toolbar">
                    <div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height grey-salt" data-placement="top" data-original-title="Change dashboard date range">
                        <i class="icon-calendar"></i>&nbsp;
                        <span class="thin uppercase visible-lg-inline-block">&nbsp;</span>&nbsp;
                        <i class="fa fa-angle-down"></i>
                    </div>
                </div>
            </div>
            <!-- END PAGE HEADER-->


            <c:import url="/WEB-INF/inc/container/dashbordStats.jsp" />


            <div class="clearfix">
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/siteVisits.jsp" />
                </div>
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/revenue.jsp" />
                </div>
            </div>
<%--
            <div class="clearfix"></div>
            <div class="row ">
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/recentActivities.jsp" />
                </div>
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/tasks.jsp" />
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row ">
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/generalStats.jsp" />
                </div>
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/serverStats.jsp" />
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row ">
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/regionalStates.jsp" />
                </div>
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/feeds.jsp" />
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row ">
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/calendar.jsp" />
                </div>
                <div class="col-md-6 col-sm-6">
                    <c:import url="/WEB-INF/inc/container/portlets/chat.jsp" />
                </div>
            </div></div>
 --%>

    </div>
    <!-- END CONTENT -->

                    <%--<c:import url="/WEB-INF/inc/container/quickSidebar.jsp" />--%>
</div>
<!-- END CONTAINER -->
</div>