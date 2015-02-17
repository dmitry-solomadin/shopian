<%@ attribute name="selectedItem" required="true" type="java.lang.String" description="dashboard | shop | beacon | ad" %>
<%--<%@ attribute name="selectedSubItem" required="false" type="java.lang.String" description="" %>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@tag pageEncoding="UTF-8" %>


<%-- BEGIN SIDEBAR MENU --%>
<%--

DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders)
DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode
DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode
DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing
DOC: Set data-keep-expand="true" to keep the submenues expanded
DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed

--%>

<%
//    List<String> list = Arrays.asList("stat_beacon", "stat_client", "stat_query");
//    request.setAttribute("item", request.getContextPath());
%>
<%--==${item}==--%>
<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
    <%--DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element--%>
    <li class="sidebar-toggler-wrapper">
        <div class="sidebar-toggler"></div>
    </li>
    <%--
    <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
    <li class="sidebar-search-wrapper">
        <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
        <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
        <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
        <form class="sidebar-search " action="extra_search.html" method="POST">
            <a href="javascript:;" class="remove">
                <i class="icon-close"></i>
            </a>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
            </div>
        </form>
        <!-- END RESPONSIVE QUICK SEARCH FORM -->
    </li>
    --%>
    <br/>
    <sec:authorize url="/">
        <li class="start ${'dashboard'.equals(selectedItem)?' active':''}">
            <a href="/">
                <i class="icon-home"></i>
                <span class="title">Главная</span>
                <span class="selected"></span>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize url="/shop">
        <li class="${'shop'.equals(selectedItem)?' active':''}">
            <a href="/shop">
                <i class="icon-basket"></i>
                <span class="title">Магазины</span>
                <span class="selected"></span>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize url="/beacon">
        <li class="${'beacon'.equals(selectedItem)?' active':''}">
            <a href="/beacon">
                <i class="icon-pointer"></i>
                <span class="title">Маячки</span>
                <span class="selected"></span>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize url="/ad">
        <li class="${'ad'.equals(selectedItem)?' active':''}">
            <a href="/ad">
                <i class="icon-rocket"></i>
                <span class="title">Акции</span>
                <span class="selected"></span>
            </a>
        </li>
    </sec:authorize>
</ul>


<%--

    <%
        List<String> list = Arrays.asList("stat_beacon", "stat_client", "stat_query");
        request.setAttribute("mStat", list.contains(selectedItem));
    %>
    <li class="${mStat?'active open':''}">
        <a href="javascript:;"><i class="icon-bar-chart"></i><span class="title">Статистика</span><span
                class="arrow ${mStat?'open':''}"></span>${mStat?'<span class="selected"></span>':''}</a>
        <ul class="sub-menu">
            <li class="${'stat_beacon'.equals(selectedItem)?' active':''}"><a href="/aa.do">Статистика по маячкам</a></li>
            <li class="${'stat_client'.equals(selectedItem)?' active':''}"><a href="/stat/client.do">Статистика по клиентам</a></li>
            <li class="${'stat_query'.equals(selectedItem)?' active':''}"><a href="/stat/query.do">Статистика по запросам</a></li>
        </ul>
    </li>


--%>