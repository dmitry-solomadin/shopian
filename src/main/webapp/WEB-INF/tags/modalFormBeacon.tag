<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@tag pageEncoding="UTF-8" %>
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div id="formUpdate"></div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="basicClose"></button>
                <h4 class="modal-title">iBeacon edit #<span class="mBeaconId"></span></h4>
            </div>
            <div class="modal-body">
                <form id="modalShopForm" name="modalShopForm" class="form-horizontal">
                    <input type="hidden" id="modalId" name="modalId"/>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">uuid</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="modalUuid" placeholder="uuid" required="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">major</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-input form-control" id="modalMajor" placeholder="major" required="true" min="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">minor</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-input form-control" id="modalMinor" placeholder="minor" required="true" min="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">user</label>
                        <div class="col-sm-8">
                            <select class="form-input form-control" required="true" id="modalUser">
                                <option value="">--- user ---</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.username}">${user.username}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">магазин</label>
                        <div class="col-sm-8">
                            <select class="form-input form-control" required="true" id="modalShop">
                                <option value="">--- магазин ---</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">отдел</label>
                        <div class="col-sm-8">
                            <select class="form-input form-control" id="modalDepartment">
                                <option value="">--- отдел ---</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" form="modalShopForm" class="btn default" data-dismiss="modal">Закрыть</button>
                <button type="submit" form="modalShopForm" class="btn blue">Записать</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#modalShopForm').submit(function (event) {
            var json = {
                id:  $('#modalId').val(),
                uuid: $("#modalUuid").val(),
                major: $("#modalMajor").val(),
                minor: $("#modalMinor").val(),
                user:$("#modalUser").val(),
                shop: $('#modalShop').val(),
                department : $('#modalDepartment').val()
            };
            $.ajax({
                url: "/ajax/beacon/update",
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
        $("#modalUser").change(function() {
            reloadShopsAdmin($("#modalUser").val(),$("#modalShop"))
        });
        $("#modalShop").change(function() {
            reloadDepartmentsAdmin($("#modalShop").val(),$("#modalDepartment"))
        });
    })

    function editRow(id) {
        $.ajax({
            url: "/ajax/beacon/info.json?id=" + id,
            type: "GET",
            success: function (data) {
                $("#modalId").val(data['id'])
                $("#modalUuid").val(data['uuid'])
                $("#modalMajor").val(data['major'])
                $("#modalMinor").val(data['minor'])
                $("#modalUser").val(data['user']['username'])
                reloadShopsAdmin(data['user']['username'],$('#modalShop'),data['shop']['id'])
                if (data['department']){
                    reloadDepartmentsAdmin(data['shop']['id'],$('#modalDepartment'),data['department']['id'])
                }else{
                    reloadDepartmentsAdmin(data['shop']['id'],$('#modalDepartment'))
                }

                $(".mBeaconId").html(data['id'])
                $("#basic").modal()
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });
    }
</script>