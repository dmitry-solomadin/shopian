<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- BEGIN DASHBOARD STATS -->
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat blue-madison">
            <div class="visual"><i class="fa fa-users"></i></div>
            <div class="details">
                <div class="number" id="countClient"></div>
                <div class="desc">Клиенты</div>
            </div>
            <a class="more" href="/client">перейти<i class="m-icon-swapright m-icon-white"></i></a>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat red-intense">
            <div class="visual"><i class="icon-pointer"></i></div>
            <div class="details">
                <div class="number" id="countBeacon"></div>
                <div class="desc">Маячки</div>
            </div>
            <a class="more" href="/beacon">перейти<i class="m-icon-swapright m-icon-white"></i></a>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat green-haze">
            <div class="visual"><i class="fa fa-shopping-cart"></i></div>
            <div class="details">
                <div class="number" id="countShop"></div>
                <div class="desc">Магазины</div>
            </div>
            <a class="more" href="/shop">перейти<i class="m-icon-swapright m-icon-white"></i></a>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat purple-plum">
            <div class="visual">
                <i class="icon-rocket"></i>
                <%--<i class="fa fa-globe"></i>--%>
            </div>
            <div class="details">
                <div class="number" id="countApi"></div>
                <div class="desc">Обращений к API</div>
            </div>
            <a class="more" href="/action">подробнее<i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>
</div>

<!-- END DASHBOARD STATS -->