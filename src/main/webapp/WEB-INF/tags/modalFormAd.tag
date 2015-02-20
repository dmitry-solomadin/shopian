<%@tag pageEncoding="UTF-8" %>

<script type="text/javascript" src="http://static.lafox.net/moment-2.8.4/moment-with-locales.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap-datetimepicker-4/bootstrap-datetimepicker.min.css"/>
<script type="text/javascript" src="/resources/bootstrap-datetimepicker-4/bootstrap-datetimepicker.min.js"></script>


<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div id="formUpdate"></div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="basicClose"></button>
                <h4 class="modal-title">Редактор акции #<span class="mAdId"></span></h4>
            </div>
            <div class="modal-body">
                <form id="modalAdForm" name="modalAdForm" class="form-horizontal">
                    <input type="hidden" id="modalId" name="modalId"/>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">название</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="modalTitle" placeholder="Название" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">push text</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="modalPushText" placeholder="push text" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">временной интервал</label>
                        <div class="col-sm-4">
                            <div class='input-group date datetimepickerStart' >
                                <input type='text' class="form-control" id='modalDateStart' required="true"/>
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class='input-group date datetimepickerStop' >
                                <input type='text' class="form-control" id='modalDateStop' required="true"/>
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">магазин/отдел</label>
                        <div class="col-sm-4">
                            <select class="form-input form-control" required="true" id="modalShop">
                                <option value="">--- магазин ---</option>
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select class="form-input form-control" id="modalDepartment">
                                <option value="">--- отдел ---</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                        <textarea class="form-input form-control" id="modalContent" required="true"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" form="modalAdForm" class="btn default" data-dismiss="modal">Закрыть</button>
                <button type="submit" form="modalAdForm" class="btn blue">Записать</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var format="YYYY-MM-DD"
    $(document).ready(function () {

        $("#modalShop").change(function() {
            reloadDepartmentsUser($("#modalShop").val(),$("#modalDepartment"))
        });

        $('#modalAdForm').submit(function (event) {
            var json = {
                id: $('#modalId').val(),
                title: $('#modalTitle').val(),
                pushText: $('#modalPushText').val(),
                content: $('#modalContent').val(),
                dateStart: $('#modalDateStart').val(),
                dateStop: $('#modalDateStop').val(),
                shop: $('#modalShop').val(),
                department : $('#modalDepartment').val()
            }

            $.ajax({
                url: "/ajax/ad/update",
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
    })

    function editRow(id) {
        $.ajax({
            url: "/ajax/ad/info.json?id=" + id,
            type: "GET",
            success: function (data) {
                $("#modalId").val(data['id'])
                $("#modalTitle").val(data['title'])
                $("#modalPushText").val(data['pushText'])
                $("#modalContent").val(data['content'])

                $('.datetimepickerStart').datetimepicker({
                    format: format,
                    useCurrent:false,
                    defaultDate: moment.unix(data['dateStart']/1000).format(format)
                });
                $('.datetimepickerStop').datetimepicker({
                    format: format,
                    useCurrent:false,
                    defaultDate: (data['dateStop']>0)? moment.unix(data['dateStop']/1000).format(format):''
                });

                reloadShopsUser($('#modalShop'),(data['shop'])?data['shop']['id']:false)
                if(data['shop']){
                    reloadDepartmentsUser(data['shop']['id'], $('#modalDepartment'), (data['department'])?data['department']['id']:false)
                }else{
                    $('#modalDepartment').empty().append(new Option("--- отдел ---", ""))
                }

                $(".mAdId").html(data['id'])

                $("#basic").modal()
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });
    }

</script>
