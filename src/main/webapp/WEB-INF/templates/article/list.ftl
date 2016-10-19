<#assign pageName="文章管理"/>
<#assign code = RequestParameters.code!'' />
<#assign title = RequestParameters.title!'' />

<@override name="buttons">
<a href="${ctx}/article/create" class="btn btn-pink btn-sm">添加</a>
</@override>

<@override name="content">
<div class="col-xs-12">
    <form class="form-inline" method="get" novalidate>
        <div class="form-group">
            <select class="form-control" name="code">
                <option value="">--全部栏目--</option>
                <#list categories as category>
                    <option value="${category.code}" <#if code=='${category.code}'>selected</#if>>${category.name}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <input type="text" class="form-control" name="title" placeholder="标题" value="${title}"/>
        </div>

        <button class="btn btn-pink btn-sm">
            搜索
            <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
        </button>
    </form>
</div>

<div class="space-24"></div>

<div class="col-xs-12">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>栏目</th>
            <th>标题</th>
            <th>是否删除</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#if (page.list)?size gt 0>
                <#list page.list as article>
                    <#include "article-tr.ftl"/>
                </#list>
            <#else>
            <tr>
                <td colspan="20">
                    <div class="empty">暂无查询记录</div>
                </td>
            </tr>
            </#if>
        </tbody>
    </table>
    <@c.pagination url="article" param="code=${code}&title=${title}"/>
</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/article/list.js"></script>
</@override>

<@extends name="../layout.ftl"/>