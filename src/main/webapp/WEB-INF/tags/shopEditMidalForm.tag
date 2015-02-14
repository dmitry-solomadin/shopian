<%@tag pageEncoding="UTF-8" %>
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div id="formUpdate"></div>
        <form name="subForm" class="form-horizontal" >
            <input type="hidden" name="id" id="id" class="mShopId"/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Редактор магазина #<span class="mShopId"></span></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">название</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="title" placeholder="Название" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">адрес</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-input form-control" id="address" placeholder="адрес" required="false">
                        </div>
                    </div>
<hr/>
                    <table class="table table-striped table-hover table-bordered" id="departmentTable">
                        <thead>
                        <tr>
                            <th>отдел</th>
                            <th>изменить</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn default" data-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn blue">Записать</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">

</script>