<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="metronicUrl" value="http://static.lafox.net/metronic-3.3.1" scope="request"/>
<c:set var="title" value="Dashboard" scope="request"/>

<tags:metronicHead title="${title}"/>
<%--
 DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices
 DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default
 DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle
 DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle
 DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle
 DOC: Apply "page-sidebar-fixed" class to have fixed sidebar
 DOC: Apply "page-footer-fixed" class to the body element to have fixed footer
 DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side
 DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu
 --%>
<body class="page-header-fixed page-quick-sidebar-over-content">


<c:import url="/WEB-INF/inc/header.jsp"/>
<div class="clearfix"></div>

<%-- BEGIN CONTAINER --%>
<div class="page-container">
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <tags:sidebarMenu selectedItem="dashboard"/></div>
    </div>

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <tags:breadCrumbs titleShort="${title}" titleLong="отчеты и статистика" showCalendar="true"/>
            <sec:authorize url="/ajax/stat/chart/action.json">
            <c:import url="/WEB-INF/inc/container/dashbordStats.jsp"/>

            <div class="clearfix"></div>

            <div class="row">
                <!-- BEGIN INTERACTIVE CHART PORTLET-->
                <div class="col-md-6 col-sm-6">
                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <div class="caption"><i class="fa fa-bar-chart-o"></i>Посещения</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload" id="reloadChart_1"></a>
                                <a href="javascript:;" class="fullscreen"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="chart_1" class="chart"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="fa fa-bullhorn"></i>Акции</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload" id="reloadChart_2"></a>
                                <a href="javascript:;" class="fullscreen"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="chart_2" class="chart"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption"><i class="fa fa-bullhorn"></i>Среднее время посещения</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload" id="reloadChart_3"></a>
                                <a href="javascript:;" class="fullscreen"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="chart_3" class="chart"></div>
                        </div>
                    </div>
                </div>
            </div>
                </sec:authorize>
        </div>
        <!-- END CONTENT -->

    </div>
    <%--END CONTAINER--%>
</div>


<tags:metronicJS/>
<script src="${metronicUrl}/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${metronicUrl}/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        QuickSidebar.init(); // init quick sidebar
        Demo.init(); // init demo features
        Index.init();
        Index.initDashboardDaterange();
        Index.initCalendar(); // init index page's custom scripts
//        Index.initCharts(); // init index page's custom scripts
//        Index.initJQVMAP(); // init index page's custom scripts
//        Index.initChat();
//        Index.initMiniCharts();
//        Tasks.initDashboardWidget();
    });


    $(document).ready(function () {
        $.getJSON('${jsonURL}', function (data) {
            console.log(data)
        })
    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $.getJSON('/ajax/stat/count/client.json', function (data) {
            $('#countClient').html(data['count'])
        })
        $.getJSON('/ajax/stat/count/beacon.json', function (data) {
            $('#countBeacon').html(data['count'])
        })
        $.getJSON('/ajax/stat/count/shop.json', function (data) {
            $('#countShop').html(data['count'])
        })
        $.getJSON('/ajax/stat/count/api.json', function (data) {
            $('#countApi').html(data['count'])
        })
    });
</script>

<script type="text/javascript">
    var stack = 0,
            bars = true,
            lines = false,
            steps = false;

    var options = {
        xaxis: {
            tickLength: 0,
            tickDecimals: 0,
            mode: "categories",
            min: 0,
            font: {
                lineHeight: 14,
                style: "normal",
                variant: "small-caps",
                color: "#6F7B8A"
            }
        },
        yaxis: {
            ticks: 5,
            tickDecimals: 0,
            tickColor: "#eee",
            font: {
                lineHeight: 14,
                style: "normal",
                variant: "small-caps",
                color: "#6F7B8A"
            }
        },
        grid: {
            hoverable: true,
            clickable: true,
            tickColor: "#eee",
            borderColor: "#eee",
            borderWidth: 1
        }
    }

    var options2 = {
        series: {
            stack: stack,
            lines: {
                show: lines,
                fill: true,
                steps: steps,
                lineWidth: 0
            },
            bars: {
                show: bars,
                barWidth: 0.5,
                lineWidth: 0,
                shadowSize: 0,
                align: 'center'
            }
        },
        grid: {
            tickColor: "#eee",
            borderColor: "#eee",
            borderWidth: 1
        }
//                            ,xaxis: {mode:"time", timeformat:"%d/%m/%y", tickSize:[1,"day"]}
    }

    jQuery(document).ready(function () {

        $("#reloadChart_1").click(function () {
            $.getJSON('/ajax/stat/chart/clients.json', function (data) {
                $.each(data, function (i, item) {
                    var series = [{
                        data: item.data,
                        lines: {fill: 0.6, lineWidth: 0},
                        color: ['#ffb848'],
                        label: "посетители"
                    }, {
                        data: item.data,
                        points: {show: true, fill: true, radius: 5, fillColor: "#ffb848", lineWidth: 3},
//                        color: '#fff',
                        color: '#ffe',
                        shadowSize: 0
                    }];
                    console.log(item)
                    $.plot("#chart_1", series, options);
                })
            })
        });

        $("#reloadChart_2").click(function () {
            $.getJSON('/ajax/stat/chart/action.json', function (data) {
                console.log(data)
                $.plot("#chart_2", data, options2);
            })
        });

        $("#reloadChart_3").click(function () {
            $.getJSON('/ajax/stat/chart/time.json', function (data) {
                $.each(data, function (i, item) {
                    var series = [{
                        data: item.data,
                        lines: {fill: 0.6, lineWidth: 0},
                        color: ['#35aa47'],
                        label: "время посещения"
                    }, {
                        data: item.data,
                        points: {show: true, fill: true, radius: 5, fillColor: "#35aa47", lineWidth: 3},
                        color: '#efe',
                        shadowSize: 0
                    }];
                    console.log(item)
                    $.plot("#chart_3", series, options);
                })
            })
        });

        $("#reloadChart_1").click();
        $("#reloadChart_2").click();
        $("#reloadChart_3").click();
    })//jQuery(document).ready()

</script>


<%-- END JAVASCRIPTS --%>
</body>
<%-- END BODY --%>
</html>