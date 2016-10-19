<#assign action="${category.id???string('update','save')}"/>
<#assign pageName="${category.id???string('编辑栏目','添加栏目')}"/>

<@override name="content">
<div class="col-xs-12">
    <form method="post" class="form-horizontal" action="${ctx}/category/${action}">
        <#if category.id??>
            <input type="hidden" name="id" value="${category.id}"/>
        </#if>
        <div class="form-group">
            <label for="code" class="col-sm-3 control-label no-padding-right">栏目代码<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <input type="text" name="code" value="${category.code!''}" class="width-100"/>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label no-padding-right">栏目名称<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <input type="text" name="name" value="${category.name!''}" class="width-100"/>
            </div>
        </div>
        <div class="form-group">
            <label for="sort" class="col-sm-3 control-label no-padding-right">排序<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <input type="text" name="sort" value="${category.sort!''}" class="width-100"/>
            </div>
        </div>

        <div class="clearfix form-actions">
            <div class="text-center">
                <button class="btn btn-pink" data-toggle="form-submit" data-loading-text="正在保存...">
                    <i class="ace-icon fa fa-check bigger-110"></i>
                    提交
                </button>
            </div>
        </div>
    </form>
</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/category/form.js"></script>
</@override>

<@extends name="../layout.ftl"/>