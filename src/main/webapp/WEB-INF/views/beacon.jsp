<!DOCTYPE html>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="metronicUrl" value="http://static.lafox.net/metronic-3.3.1" scope="request"/>
<c:set var="title" value="Маячок" scope="request"/>

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
            <tags:sidebarMenu selectedItem="beacon"/></div>
    </div>

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <tags:breadCrumbs titleShort="${title}" titleLong="просмотр и изменение"/>
            <%--<c:import url="/WEB-INF/inc/container/dashbordStats.jsp"/>--%>
            <div class="clearfix"></div>


            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-edit"></i>Editable Table
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                                <a href="#portlet-config" data-toggle="modal" class="config">
                                </a>
                                <a href="javascript:;" class="reload">
                                </a>
                                <a href="javascript:;" class="remove">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-toolbar">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="btn-group">
                                            <button id="sample_editable_1_new" class="btn green">
                                                Add New <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <table class="table table-striped table-hover table-bordered" id="aTable">
                                <thead>
                                <tr>
                                    <%--<th>id</th>--%>
                                    <%--<th>title</th>--%>
                                    <th>id</th>
                                    <th>uuid</th>
                                    <th>major</th>
                                    <th>minor</th>
                                    <th>edit/delite</th>
                                </tr>
                                </thead>
                                <tfoot>
                                </tfoot>
                                <%--                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                        alex
                                                                    </td>
                                                                    <td>
                                                                        Alex Nilson
                                                                    </td>
                                                                    <td>
                                                                        1234
                                                                    </td>
                                                                    <td class="center">
                                                                        power user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        lisa
                                                                    </td>
                                                                    <td>
                                                                        Lisa Wong
                                                                    </td>
                                                                    <td>
                                                                        434
                                                                    </td>
                                                                    <td class="center">
                                                                        new user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        nick12
                                                                    </td>
                                                                    <td>
                                                                        Nick Roberts
                                                                    </td>
                                                                    <td>
                                                                        232
                                                                    </td>
                                                                    <td class="center">
                                                                        power user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        goldweb
                                                                    </td>
                                                                    <td>
                                                                        Sergio Jackson
                                                                    </td>
                                                                    <td>
                                                                        132
                                                                    </td>
                                                                    <td class="center">
                                                                        elite user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        webriver
                                                                    </td>
                                                                    <td>
                                                                        Antonio Sanches
                                                                    </td>
                                                                    <td>
                                                                        462
                                                                    </td>
                                                                    <td class="center">
                                                                        new user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        gist124
                                                                    </td>
                                                                    <td>
                                                                        Nick Roberts
                                                                    </td>
                                                                    <td>
                                                                        62
                                                                    </td>
                                                                    <td class="center">
                                                                        new user
                                                                    </td>
                                                                    <td>
                                                                        <a class="edit" href="javascript:;">
                                                                            Edit </a>
                                                                    </td>
                                                                    <td>
                                                                        <a class="delete" href="javascript:;">
                                                                            Delete </a>
                                                                    </td>
                                                                </tr>
                                                                </tbody>--%>
                            </table>


                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>


            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <h4>Добавить новый iBeacon</h4>

                    <form class="form-inline" role="form" id="formAddBeacon">
                        <div class="form-group">
                            <label class="sr-only" for="uuid">UUID</label>

                            <div class="input-icon">
                                <i class="icon-pointer"></i>
                                <input type="text" class="form-control" id="uuid" placeholder="Введите UUID" required="true" size="40">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="major">major</label>

                            <div class="input-icon">
                                <i class=" fa-check-circle-o"></i>
                                <input type="number" class="form-control" id="major" placeholder="major" min="1" required="true" size="8" style="width: 12em">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="minor">minor</label>

                            <div class="input-icon">
                                <i class=" fa-check-circle-o"></i>
                                <input type="number" class="form-control" id="minor" placeholder="minor" min="1" required="true" style="width: 12em">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-default">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- END CONTENT -->

    </div>
    <%--END CONTAINER--%>
</div>


<tags:metronicJS/>
<script src="${metronicUrl}/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${metronicUrl}/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>

<%--<link rel="stylesheet" type="text/css" href="${metronicUrl}/assets/global/plugins/select2/select2.css"/>--%>
<%--<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/select2/select2.min.js"></script>--%>

<link rel="stylesheet" type="text/css" href="${metronicUrl}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>

<script src="/resources/js/table-beacon.js" type="text/javascript"></script>
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

//        TableEditable.init();

    });

    function editRow(oTable, nRow) {
        var aData = oTable.fnGetData(nRow);
        var jqTds = $('>td', nRow);
//        jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '">';
        jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
        jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
        jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '">';
        jqTds[4].innerHTML = '<a class="edit" href="">Save</a> <a class="cancel" href="">Cancel</a>';
    }
    function edit(id){
        alert(id)
    }
    $(document).ready(function () {
        var table=$('#aTable')
       var oTable= table.dataTable({
           serverSide: true,

           ajax: {
               url: 'ajax/beacon/list.json',
               type: 'POST'
           },
            columns: [
                {"data": "id"},
                {"data": "uuid"},
                {"data": "major"},
                {"data": "minor"},
                {"data": "title"},
            ],
            lengthMenu: [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            pageLength: 5,
            order: [
                [0, "asc"]
            ],
           fnRowCallback: function (nRow, aData, iDisplayIndex) {
               $('td', nRow).eq(4).html('<button onclick="edit('+aData['id']+')"></button><a class="edit" href="javascript:;">Edit</a>' +
               ' <a class="delete" href="javascript:;">Delete</a>')
           }
        });

        table.on('click', '.delete', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            oTable.fnDeleteRow(nRow);
            alert("Deleted! Do not forget to do some ajax to sync with backend :)");
        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();

            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];
            alert(nRow)

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "Save") {
                /* Editing this row and want to save it */
                saveRow(oTable, nEditing);
                nEditing = null;
                alert("Updated! Do not forget to do some ajax to sync with backend :)");
            } else {
                /* No edit in progress - let's start one */
                alert(oTablex)
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    })
    $(document).ready(function () {
        $('#formAddBeacon').submit(function (event) {

            var uuid = $('#uuid').val();
            var major = $('#major').val();
            var minor = $('#minor').val();
            var json = {"uuid": uuid, "major": major, "minor": minor, 'title': 'untitled'};

            $.ajax({
                url: "/ajax/beacon/add",
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    if (data["error"]) alert(data["error"]) // TODO out error to label and pretty alert
                }
            });
            event.preventDefault();
        });
    });


</script>
<%--

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

--%>

<%-- END JAVASCRIPTS --%>
</body>
<%-- END BODY --%>
</html>