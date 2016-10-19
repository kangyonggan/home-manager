<#assign ctx="${(rca.contextPath)!''}">

<#macro pagination url param="">
    <#if (page.list)??>
    <div class="pull-left">
        <ul class="pagination">
            <#if page.hasPreviousPage>
                <li>
                    <a href="${ctx}/${url}?p=${page.prePage}<#if param?has_content>&${param}</#if>">
                        <i class="ace-icon fa fa-angle-double-left"></i>
                    </a>
                </li>
            </#if>
            <#list page.navigatepageNums as nav>
                <li <#if nav == page.pageNum>class="active"</#if>>
                    <a href="${ctx}/${url}?p=${nav}<#if param?has_content>&${param}</#if>">${nav}</a>
                </li>
            </#list>
            <#if page.hasNextPage>
                <li>
                    <a href="${ctx}/${url}?p=${page.nextPage}<#if param?has_content>&${param}</#if>">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                    </a>
                </li>
            </#if>
        </ul>
    </div>
    </#if>
</#macro>