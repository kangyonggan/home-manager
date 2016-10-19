<tr>
    <td>${article.id}</td>
    <td>${article.categoryName}</td>
    <td>${article.title}</td>
    <td>${(article.isDeleted==0)?string('未删除', '已删除')}</td>
    <td>${article.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs btn-inverse" href="${ctx}/article/${article.id}/edit">编辑</a>
            <#if article.isDeleted==0>
                <a class="btn btn-xs btn-inverse" href="${ctx}/article/${article.id}/delete">删除</a>
            <#else>
                <a class="btn btn-xs btn-inverse" href="${ctx}/article/${article.id}/recover">恢复</a>
            </#if>
        </div>
    </td>
</tr>