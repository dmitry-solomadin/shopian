<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" description="title" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- BEGIN HEAD --%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><html lang="en" class="no-js"><![endif]-->
<head>
    <meta charset="UTF-8">
    <title>Shopian3 ${title}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <%-- BEGIN GLOBAL MANDATORY STYLES --%>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <%-- END GLOBAL MANDATORY STYLES --%>
    <%-- BEGIN PAGE LEVEL PLUGIN STYLES --%>
    <link href="${metronicUrl}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
    <%-- END PAGE LEVEL PLUGIN STYLES --%>
    <%-- BEGIN PAGE STYLES --%>
    <link href="${metronicUrl}/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
    <%-- END PAGE STYLES --%>
    <%-- BEGIN THEME STYLES --%>
    <link href="${metronicUrl}/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${metronicUrl}/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${metronicUrl}/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <%-- END THEME STYLES --%>
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<%-- END HEAD --%>