<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <div class='input-group date datetimepickerStart'>
                                <input type='text' class="form-control" id='modalDateStart' required="true"/>
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class='input-group date datetimepickerStop'>
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
                <div class="row">
                    <div class="col-sm-4">
                        <div class="btn-group-vertical margin-right-10">
<span class="btn btn-success fileinput-button">
    <i class="glyphicon glyphicon-image"></i>
    <span>загрузить</span>
    <input id="fileupload" type="file" name="file" accept="image/*">
</span>
                            <button type="button" class="btn btn-default red">удалить</button>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <img src="http://lafox.net/i/244-w352-h198-c.jpg?v=1424252568710" class="pull-right"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" form="modalAdForm" class="btn default" data-dismiss="modal">Закрыть</button>
                <button type="submit" form="modalAdForm" class="btn blue">Записать</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var format = "YYYY-MM-DD"
    $(document).ready(function () {

        $("#modalShop").change(function () {
            reloadDepartmentsUser($("#modalShop").val(), $("#modalDepartment"))
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
                department: $('#modalDepartment').val()
            }

            $.ajax({
                url: "/ajax/campaign/update",
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
            url: "/ajax/campaign/info.json?id=" + id,
            type: "GET",
            success: function (data) {
                $("#modalId").val(data['id'])
                $("#modalTitle").val(data['title'])
                $("#modalPushText").val(data['pushText'])
                $("#modalContent").val(data['content'])

                $('.datetimepickerStart').datetimepicker({
                    format: format,
                    useCurrent: false,
                    defaultDate: moment.unix(data['dateStart'] / 1000).format(format)
                });
                $('.datetimepickerStop').datetimepicker({
                    format: format,
                    useCurrent: false,
                    defaultDate: (data['dateStop'] > 0) ? moment.unix(data['dateStop'] / 1000).format(format) : ''
                });

                reloadShopsUser($('#modalShop'), (data['shop']) ? data['shop']['id'] : false)
                if (data['shop']) {
                    reloadDepartmentsUser(data['shop']['id'], $('#modalDepartment'), (data['department']) ? data['department']['id'] : false)
                } else {
                    $('#modalDepartment').empty().append(new Option("--- отдел ---", ""))
                }

                $(".mAdId").html(data['id'])
                $("#modalId").html(data['id'])

                $("#basic").modal()
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });
    }

</script>


<c:set var="fupath" value="http://static.lafox.net/jQuery-File-Upload-9.9.3"/>
<link rel="stylesheet" href="${fupath}/css/jquery.fileupload.css">
<script src="${fupath}/js/vendor/jquery.ui.widget.js"></script>
<script src="${fupath}/js/jquery.iframe-transport.js"></script>
<script src="${fupath}/js/jquery.fileupload.js"></script>
<script>
    $(document).ready(function () {
        'use strict';
        $('#fileupload').fileupload({
            url: '/ajax/img/upload',
            add: function(e, data) {
                data.url = '/ajax/campaign/uploadImg?id=' + $('#modalId').val()
                data.submit();
            },
            dataType: 'json',
            done: function (e, data) {
                console.log(data.result)
                $("#upRes").html(data.result['statusText'])
                if (data.result['error']) {
                    alert(data.result['error'])
                } else {
                    alert("ok")
                }
//                reloadStatus()

                $('#progress .progress-bar').css('width', '0%');
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css('width', progress + '%');
            }
        }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled')
                .bind('fileuploadstart', function (e, data) {
                    console.log(e)
                    console.log(data)
                    console.log($(this))
//                    var that = $(this).data('fileupload');
//                    var that = $(this).data('blueimpFileupload');
//                    that.options.url = '/ajax/img/upload?id=' + $('#modalId').val()
//
                });
    });

</script>
