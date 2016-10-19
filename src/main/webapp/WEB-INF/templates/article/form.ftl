<#assign action="${article.id???string('update','save')}"/>
<#assign pageName="${article.id???string('编辑文章','添加文章')}"/>

<@override name="content">
<div class="col-xs-12">
    <form method="post" class="form-horizontal" action="${ctx}/article/${action}">
        <#if article.id??>
            <input type="hidden" name="id" value="${article.id}"/>
        </#if>
        <div class="form-group">
            <label for="categoryCode" class="col-sm-3 control-label no-padding-right">栏目<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <select name="categoryCode" class="width-100">
                    <#list categories as category>
                        <option value="${category.code}" <#if article.id?? && article.categoryCode=='${category.code}'>selected</#if>>${category.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-3 control-label no-padding-right">标题<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <input type="text" name="title" value="${article.title!''}" class="width-100"/>
            </div>
        </div>
        <div class="form-group">
            <label for="summary" class="col-sm-3 control-label no-padding-right">摘要</label>
            <div class="col-xs-12 col-sm-5">
                <input type="text" name="summary" value="${article.summary!''}" class="width-100"/>
            </div>
        </div>
        <div class="form-group">
            <label for="body" class="col-sm-3 control-label no-padding-right">内容<span class="red">*</span></label>
            <div class="col-xs-12 col-sm-5">
                <textarea name="body" rows="10" class="width-100">${article.body!''}</textarea>
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
<script src="${ctx}/static/app/js/article/form.js"></script>
</@override>

<@extends name="../layout.ftl"/>