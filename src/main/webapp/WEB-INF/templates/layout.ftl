<#assign ctx="${(rca.contextPath)!''}">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>后台管理系统 - ${pageName}</title>
    <link rel="icon" type="image/ico" href="${ctx}/static/app/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-fonts.min.css"/>

    <!-- skins -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-skins.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace.min.css" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="${ctx}/static/app/css/app.css"/>

    <!-- ace settings handler -->
    <script>var ctx = '${ctx}/';</script>
    <script src="${ctx}/static/ace/dist/js/ace-extra.min.js"></script>
    <script src="${ctx}/static/ace/dist/js/jquery.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${ctx}/static/ace/dist/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/ace/dist/js/respond.min.js"></script>
    <![endif]-->
<@block name="style"/>
</head>
<body class="skin-2">

<#include "navbar.ftl">

<div class="main-container" id="main-container">
<#include "sidebar.ftl">
    <div class="main-content">
        <div class="main-content-inner">
        <#include "breadcrumbs.ftl">
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        ${pageName!''}
                        <div class="pull-right">
                        <@block name="buttons"/>
                        </div>
                    </h1>
                </div>
                <div class="row">
                <@block name="content"/>
                </div>
            </div>
        </div>
    </div>

<#include "footer.ftl">

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>

<@block name="script"/>
</body>
</html>
