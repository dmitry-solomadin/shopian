<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- BEGIN PORTLET-->
<div class="portlet solid bordered grey-cararra">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-bar-chart-o"></i>Site Visits
        </div>
        <div class="actions">
            <div class="btn-group" data-toggle="buttons">
                <label class="btn grey-steel btn-sm active">
                    <input type="radio" name="options" class="toggle" id="option1">New</label>
                <label class="btn grey-steel btn-sm">
                    <input type="radio" name="options" class="toggle" id="option2">Returning</label>
            </div>
        </div>
    </div>
    <div class="portlet-body">
        <div id="site_statistics_loading">
            <img src="../../assets/admin/layout/img/loading.gif" alt="loading"/>
        </div>
        <div id="site_statistics_content" class="display-none">
            <div id="site_statistics" class="chart">
            </div>
        </div>
    </div>
</div>
<!-- END PORTLET-->