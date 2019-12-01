<#import "parts/common.ftl" as c>

<@c.page>

    <div>заявки</div>
    <form method="get" action="/bids">
        <input type="text" name="filter" value="${filter!""}">
        <button type="submit">Поиск</button>
    </form>
    <div>

            <#list bids as bid>
        <table class="id" border="1" width="100%" cellpadding="5">
            <tr> <td style="width: 10%">Номер закупки </td>
                <td style="width: 10%">Заказчик</td>
                <td style="width: 10%">Документ </td>
                <td style="width: 60%">Объект закупки </td>
                <td style="width: 10%">Цена</td>
            </tr>

                <tr><td style="width: 10%">${bid.id}</td>
                    <td style="width: 10%"><strong>${bid.authorName}</strong></td>
                    <td style="width: 10%"><a href="${bid.filename!''}" download>документ</a></td>
                    <td style="width: 60%">${bid.object}</td>
                    <td style="width: 10%">${bid.price},Руб</td> </tr>
        </table>
            <#else>
                нет заявок

            </#list>

    </div>



</@c.page>