<%@tag pageEncoding="UTF-8" %>
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div id="formUpdate"></div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="basicClose"></button>
                <h4 class="modal-title">Редактор магазина #<span class="mShopId"></span></h4>
            </div>
            <div class="modal-body">
                <form id="modalShopForm" name="modalShopForm" class="form-horizontal">
                    <input type="hidden" id="modalId" name="modalId"/>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">название</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="modalTitle" placeholder="Название" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">адрес</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="modalAddress" placeholder="адрес">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <form class="form" role="form" id="formAddDepartment">
                    <div class="row">
                        <div class="col-md-9 col-sm-9">
                            <input type="text" class="form-input form-control" id="department" placeholder="отдел" required="true">
                        </div>
                        <div class="col-md-3 col-sm-3">
                            <button type="submit" class="btn btn-default pull-right"><i class="fa fa-plus"> добавить отдел</i></button>
                        </div>
                    </div>
                </form>
                <table class="table table-striped table-hover table-bordered" id="departmentTable">
                    <thead>
                    <tr>
                        <th>отдел</th>
                        <th>изменить</th>
                    </tr>
                    </thead>
                </table>
                <button type="button" form="modalShopForm" class="btn default" data-dismiss="modal">Закрыть</button>
                <button type="submit" form="modalShopForm" class="btn blue">Записать</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var departmentTable
    $(document).ready(function () {
            departmentTable = $('#departmentTable').DataTable({
                serverSide: true,

                ajax: {
                    url: '/ajax/shop/departments.json?id=0',
                    type: 'POST'
                },
                paging: false,
                searching: false,
                aoColumnDefs: [
                    {'bSortable': false, 'aTargets': [-1,-2]}
                ],
                columns: [
                    {"data": "title"},
                    {"data": "id"},
                ],
                order: [
                    [0, "desc"]
                ],
                fnRowCallback: function (nRow, aData, iDisplayIndex) {
                   $('td', nRow).eq(1).html('' +
                    '<button class="btn btn-primary btn-xs" onclick="editDep(' + aData['id'] + ',\'' + aData['title'] + '\')"><i class="glyphicon glyphicon-edit"></i> edit</button>' +
                    '<button class="btn btn-danger  btn-xs" onclick="delDep(' + aData['id'] + ',\'' + aData['title'] + '\')"><i class="glyphicon glyphicon-trash"></i> del</button>' +
                    '')
                }
            });

        $('#modalShopForm').submit(function (event) {
            var id = $('#modalId').val();
            var title = $('#modalTitle').val();
            var address = $('#modalAddress').val();
            var json = {id: id, title: title, address: address};

            $.ajax({
                url: "/ajax/shop/update",
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    if (data["error"]) alert(data["error"])
                    $(".reload").click();
                    $("#basicClose").click()

                }
            });
            event.preventDefault();
        })
        $('#formAddDepartment').submit(function (event) {
            var id = $('#modalId').val();
            var title = $('#department').val();
            var json = {id: id, title: title};

            $.ajax({
                url: "/ajax/department/add",
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    if (data["error"]) alert(data["error"])
                    departmentTable.ajax.reload();
                    $(".reload").click();
                    $('#department').val('')

                }
            });
            event.preventDefault();
        })
    })

    function editRow(id) {
        $.ajax({
            url: "/ajax/shop/info.json?id=" + id,
            type: "GET",
            success: function (data) {
                $("#modalId").val(data['id'])
                $("#modalTitle").val(data['title'])
                $("#modalAddress").val(data['address'])
                $(".mShopId").html(data['id'])

                departmentTable.ajax.url( '/ajax/shop/departments.json?id=' + data['id'])
                departmentTable.ajax.reload();
                $("#basic").modal()
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });
    }
    function delDep(id,title){
        bootbox.confirm("Удалить отдел '"+title+"'?", function(result) {
            if (result==true){
                $.ajax({
                    url: "/ajax/department/"+id,
                    type: "DELETE",
                    success: function (data) {
                        if (data["error"]) alert(data["error"])
                        departmentTable.ajax.reload();
                        $(".reload").click();
                    },
                    error: function (request, status, error) {
                        alert(request.responseText);
                    }
                });
            }
        });
    }
    function editDep(id,title){
        bootbox.prompt({
            title: "изменить название отдела \""+title+"\"",
            value: title,
            callback: function(result) {
                if (result !== null) {
                    $.ajax({
                        url: "/ajax/department/update",
                        data: JSON.stringify({id: id, title: result}),
                        type: "POST",
                        contentType: 'application/json',
                        success: function (data) {
                            if (data["error"]) alert(data["error"])
                            departmentTable.ajax.reload();
                            $(".reload").click();
                        }
                    });
                }
            }
        })


    }
</script>