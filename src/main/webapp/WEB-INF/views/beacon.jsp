<!DOCTYPE html>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="metronicUrl" value="http://static.lafox.net/metronic-3.3.1" scope="request"/>
<c:set var="title" value="Маячки" scope="request"/>

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
                                <i class="icon-pointer"></i>  iBeacon's</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="javascript:;" class="fullscreen"></a>
                                <%--<a href="#portlet-config" data-toggle="modal" class="config"></a>--%>
                                <a href="javascript:;" class="reload"></a>
                                <%--<a href="javascript:;" class="remove"></a>--%>
                            </div>
                        </div>
                        <div class="portlet-body">

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
                                    <th>edit</th>
                                </tr>
                                </thead>
                            </table>


                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>

            <hr/>
            <form class="form" role="form" id="formAddBeacon">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <input type="text" class="form-input form-control" id="uuid" placeholder="Введите UUID" required="true" size="32">
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <input type="number" class="form-input form-control" id="major" placeholder="major" min="1" required="true">
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <input type="number" class="form-input form-control" id="minor" placeholder="minor" min="1" required="true">
                    </div>
                </div>
                <div class="row">
                <div class="col-md-3 col-sm-3"><br/></div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-sm-3">
                        <select class="form-input form-control" required="true" id="user">
                            <option value="">--- user ---</option>
                            <c:forEach var="user" items="${users}">
                                <option value="${user.username}">${user.username}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <select class="form-input form-control" required="true" id="shop">
                            <option value="">--- магазин ---</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <select class="form-input form-control" id="department">
                            <option value="">--- отдел ---</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <button type="submit" class="btn btn-success blue pull-right"><i class="fa fa-plus"></i> Добавить iBeacon</button>
                    </div>
                </div>
            </form>
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
<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/bootbox/bootbox.min.js"></script>


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
    }
    function edit(id){
        alert(id)
    }
    function delRow(id,title){
        bootbox.confirm("Удалить iBeacon: '"+title+"'?", function(result) {
            if (result==true){
                $.ajax({
                    url: "/ajax/beacon/"+id,
                    type: "DELETE",
                    success: function (data) {
                        if (data["error"]) alert(data["error"])
                        $(".reload").click();
                    },
                    error: function (request, status, error) {
                        alert(request.responseText);
                    }
                });
            }
        });
    }

    function reloadDepartments(shopId,obj, selected){
        obj.empty().append(new Option("--- отдел ---", ""))
        if (!shopId) return
        $.ajax({
            url: "/ajax/beacon/departments.json",
            data: JSON.stringify({id:shopId}),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
                if (data["error"]) alert(data["error"])
                $.each(data, function(i, item) {
                    obj.append(new Option(item['title'], item['id']))
                });
                if(selected)  obj.val(selected)
            }
        })
    }
    function reloadShops(username,obj, selected){
        obj.empty().append(new Option("--- магазин ---", ""))
        if (!username) return;
        $.ajax({
            url: "/ajax/beacon/shops.json",
            data: JSON.stringify({username:username}),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
                if (data["error"]) alert(data["error"])
                $.each(data, function(i, item) {
                    obj.append(new Option(item['title'], item['id']))
                });
                if(selected)  obj.val(selected)
            }
        })
    }

    $(document).ready(function () {
        $("#user").change(function() {
            reloadShops($("#user").val(),$("#shop"))
        });
        $("#shop").change(function() {
            reloadDepartments($("#shop").val(),$("#department"))
        });

       var table= $('#aTable').DataTable({
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
               [5, 10, 15, 25, 50, 100, -1],
               [5, 10, 15, 25, 50, 100, "All"] // change per page values here
           ],
            pageLength: 10,
            order: [
                [0, "desc"]
            ],
           aoColumnDefs: [
               { 'bSortable': false, 'aTargets': [ -1,-2,-3,-4 ] }
           ],
           fnRowCallback: function (nRow, aData, iDisplayIndex) {
               $('td', nRow).eq(7).html('' +
               '<button class="btn btn-primary btn-xs" onclick="editRow('+aData['id']+')"><i class="glyphicon glyphicon-edit"></i> edit</button>' +
               '<button class="btn btn-danger  btn-xs" onclick="delRow('+aData['id']+',\''+aData['title']+'\')"><i class="glyphicon glyphicon-trash"></i> del</button>' +
               '')
               console.log(aData)
               if (aData['department']) $('td', nRow).eq(6).html(aData['department']["title"])
               if (aData['shop']) $('td', nRow).eq(5).html(aData['shop']["title"])
               if (aData['user']) $('td', nRow).eq(4).html(aData['user']["username"])
           }
        });

        $(".reload").click(function () {
            table.ajax.reload( null, false );
        })

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
                    $(".reload").click();
                }
            });
            event.preventDefault();
        });
    });


</script>
<tags:beaconEditModalForm/>

</body>
</html>