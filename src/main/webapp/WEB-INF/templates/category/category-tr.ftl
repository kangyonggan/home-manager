<tr>
    <td>${category.id}</td>
    <td>${category.code}</td>
    <td>${category.name}</td>
    <td>${(category.isDeleted==0)?string('未删除', '已删除')}</td>
    <td>${category.sort}</td>
    <td>${category.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs btn-inverse" href="${ctx}/category/${category.id}/edit">编辑</a>
            <#if category.isDeleted==0>
                <a class="btn btn-xs btn-inverse" href="${ctx}/category/${category.id}/delete">删除</a>
            <#else>
                <a class="btn btn-xs btn-inverse" href="${ctx}/category/${category.id}/recover">恢复</a>
            </#if>
        </div>
    </td>
</tr>