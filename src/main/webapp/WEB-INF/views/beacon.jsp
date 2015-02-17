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
                                    <th>id</th>
                                    <th>uuid</th>
                                    <th>major</th>
                                    <th>minor</th>
                                    <th>user</th>
                                    <th>shop</th>
                                    <th>department</th>
                                    <th>edit/delite</th>
                                </tr>
                                </thead>
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
                        <div class="form-group">
                            <select class="form-control" required="true" id="user">
                                <option value="">--- user ---</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.username}">${user.username}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control" required="true" id="shop">
                                <option value="">--- магазин ---</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control" id="department">
                                <option value="">--- отдел ---</option>
                            </select>
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
        var shops={}
        $("#user").change(function() {
            if ($("#user").val()){
                $.ajax({
                    url: "/ajax/beacon/shops.json",
                    data: JSON.stringify({username:$("#user").val()}),
                    type: "POST",
                    contentType: 'application/json',
                    success: function (data) {
                        shops=data;
                        if (data["error"]) alert(data["error"])
                        $('#shop').empty().append(new Option("--- магазин ---", ""))
                        $.each(data, function(i, item) {
                            $("#shop").append(new Option(item['title'], item['id']));
                        });
                    }
                });
            }
        });
        $("#shop").change(function() {
            $('#department').empty().append(new Option("--- отдел ---", ""))
            $.each(shops, function(i, item) {
                if (item["id"]==$("#shop").val()){
                    $.each(item["departments"], function(i, item) {
                        $("#department").append(new Option(item['title'], item['id']));
                    });
                }
            })
        });
    })
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
                {"data": "user"},
                {"data": "shop"},
                {"data": "department"},
                {"data": "id"},
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
               $('td', nRow).eq(7).html('<button onclick="edit('+aData['id']+')"></button><a class="edit" href="javascript:;">Edit</a>' +
               ' <a class="delete" href="javascript:;">Delete</a>')
           }
        });



        $('#formAddBeacon').submit(function (event) {

            var uuid = $('#uuid').val();
            var major = $('#major').val();
            var minor = $('#minor').val();
            var user = $('#user').val();
            var shop = $('#shop').val();
            var department = $('#department').val();
            var json = {uuid: uuid, major: major, minor: minor, user:user, shop:shop, department:department};

            $.ajax({
                url: "/ajax/beacon/add",
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    if (data["error"]) alert(data["error"])
                }
            });
            event.preventDefault();
        });
    });


</script>

</body>
</html>