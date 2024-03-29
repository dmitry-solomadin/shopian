<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- BEGIN PORTLET-->
<div class="portlet solid grey-cararra bordered">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-bullhorn"></i>Revenue
        </div>
        <div class="actions">
            <div class="btn-group pull-right">
                <a href="" class="btn grey-steel btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    Filter <span class="fa fa-angle-down">
									</span>
                </a>
                <ul class="dropdown-menu pull-right">
                    <li>
                        <a href="javascript:;">
                            Q1 2014 <span class="label label-sm label-default">
											past </span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            Q2 2014 <span class="label label-sm label-default">
											past </span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="javascript:;">
                            Q3 2014 <span class="label label-sm label-success">
											current </span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            Q4 2014 <span class="label label-sm label-warning">
											upcoming </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="portlet-body">
        <div id="site_activities_loading">
            <img src="../../assets/admin/layout/img/loading.gif" alt="loading"/>
        </div>
        <div id="site_activities_content" class="display-none">
            <div id="site_activities" style="height: 228px;">
            </div>
        </div>
        <div style="margin: 20px 0 10px 30px">
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-sm label-success">
										Revenue: </span>
                    <h3>$13,234</h3>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-sm label-info">
										Tax: </span>
                    <h3>$134,900</h3>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-sm label-danger">
										Shipment: </span>
                    <h3>$1,134</h3>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-sm label-warning">
										Orders: </span>
                    <h3>235090</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PORTLET-->
