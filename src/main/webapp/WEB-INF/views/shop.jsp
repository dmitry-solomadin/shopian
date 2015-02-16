<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="metronicUrl" value="http://static.lafox.net/metronic-3.3.1" scope="request"/>
<c:set var="title" value="Магазины" scope="request"/>

<tags:metronicHead title="${title}"/>


<body class="page-header-fixed page-quick-sidebar-over-content">


<c:import url="/WEB-INF/inc/header.jsp"/>
<div class="clearfix"></div>

<%-- BEGIN CONTAINER --%>
<div class="page-container">
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <tags:sidebarMenu selectedItem="shop"/></div>
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
                                <i class="icon-basket"></i> Магазины</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
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
                                    <th>название</th>
                                    <th>адрес</th>
                                    <th>отделы</th>
                                    <th>изменить</th>
                                </tr>
                                </thead>
<%--                                <tfoot>
                                <tr>
                                    <th>id</th>
                                    <th>title</th>
                                    <th>address</th>
                                    <th>departments</th>
                                    <th>edit/delete</th>
                                </tr>
                                </tfoot>--%>
                            </table>
                            <hr/>
                            <%--<div class="table-toolbar">--%>
                            <form class="form" role="form" id="formAddShop">
                                <div class="row">
                                    <div class="col-md-4 col-sm-4">
                                        <input type="text" class="form-input form-control" id="title" placeholder="Название" required="true">
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <input type="text" class="form-input form-control" id="address" placeholder="адрес" >
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <button type="submit" class="btn btn-success blue pull-right">Добавить <i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                            </form>
                            <%--</div>--%>

                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
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

<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/bootbox/bootbox.min.js"></script>

<link rel="stylesheet" type="text/css" href="${metronicUrl}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${metronicUrl}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>

<%--<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css"/>--%>
<%--<script type="text/javascript" src="//cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>--%>
<%--<script type="text/javascript" src="//cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js"></script>--%>


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


    function delRow(id,title){
        bootbox.confirm("Удалить магазин '"+title+"'?", function(result) {
            if (result==true){
                $.ajax({
                    url: "/ajax/shop/"+id,
                    type: "DELETE",
                    success: function (data) {
                        if (data["error"]) alert(data["error"]) // TODO out error to label and pretty alert
                        $(".reload").click();
                    },
                    error: function (request, status, error) {
                        alert(request.responseText);
                    }
                });
            }
        });
    }
    var table
    $(document).ready(function () {
       table= $('#aTable').DataTable({
           serverSide: true,

           ajax: {
               url: '/ajax/shop/list.json',
               type: 'POST'
           },
            columns: [
                {"data": "id"},
                {"data": "title"},
                {"data": "address"},
                {"data": "id"},
                {"data": "id"},
            ],
            lengthMenu: [
                [5, 10, 15, 25, 50, 100, -1],
                [5, 10, 15, 25, 50, 100, "All"] // change per page values here
            ],
           aoColumnDefs: [
               { 'bSortable': false, 'aTargets': [ -1 ] }
           ],
            pageLength: 10,
            order: [
                [0, "desc"]
            ],
           fnRowCallback: function (nRow, aData, iDisplayIndex) {
               $('td', nRow).eq(4).html('' +
               '<button class="btn btn-primary btn-xs" onclick="editRow('+aData['id']+')"><i class="glyphicon glyphicon-edit"></i> edit</button>' +
               '<button class="btn btn-danger  btn-xs" onclick="delRow('+aData['id']+',\''+aData['title']+'\')"><i class="glyphicon glyphicon-trash"></i> del</button>' +
               '')
               var deps=""
               $.each(aData['departments'], function(i, item){
                   if (i>0) deps+=", "
                   deps+=item['title']
               });
               $('td', nRow).eq(3).html(deps)
           }
        });

        $(".reload").click(function () {
            table.ajax.reload( null, false );
        })


    })
    $(document).ready(function () {
        $('#formAddShop').submit(function (event) {

            var title = $('#title').val();
            var address = $('#address').val();
            var json = {"title": title, "address": address};

            $.ajax({
                url: "/ajax/shop/add",
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    if (data["error"]) alert(data["error"]) // TODO out error to label and pretty alert
                    $(".reload").click();
                    $('#title').val('')
                    $('#address').val('')
                }
            });
            event.preventDefault();
        });
    });


</script>

<%-- END JAVASCRIPTS --%>



<tags:shopEditMidalForm/>



</body>
</html>