<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="portlet box red-sunglo">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-calendar"></i>Server Stats
        </div>
        <div class="tools">
            <a href="" class="collapse">
            </a>
            <a href="#portlet-config" data-toggle="modal" class="config">
            </a>
            <a href="" class="reload">
            </a>
            <a href="" class="remove">
            </a>
        </div>
    </div>
    <div class="portlet-body">
        <div class="row">
            <div class="col-md-4">
                <div class="sparkline-chart">
                    <div class="number" id="sparkline_bar">
                    </div>
                    <a class="title" href="#">
                        Network <i class="icon-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="margin-bottom-10 visible-sm">
            </div>
            <div class="col-md-4">
                <div class="sparkline-chart">
                    <div class="number" id="sparkline_bar2">
                    </div>
                    <a class="title" href="#">
                        CPU Load <i class="icon-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="margin-bottom-10 visible-sm">
            </div>
            <div class="col-md-4">
                <div class="sparkline-chart">
                    <div class="number" id="sparkline_line">
                    </div>
                    <a class="title" href="#">
                        Load Rate <i class="icon-arrow-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
