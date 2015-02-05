<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<%--<tiles:importAttribute name="title_small" toName="t"/>--%>



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

