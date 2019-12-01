<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>


<@c.page>


    <div>
        <@l.logout/>

    </div>
    <div><form method="post" enctype="multipart/form-data">
            <input type="text" name="object" placeholder="введите текст">
            <input type="number" name="price" placeholder="введите цену">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Отправить</button>
        </form></div>



</@c.page>