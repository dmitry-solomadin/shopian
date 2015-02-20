
function reloadDepartmentsPrivate(shopId,obj, url, selected){
    obj.empty().append(new Option("--- отдел ---", ""))
    if (!shopId) return
    $.ajax({
        url: url,
        data: JSON.stringify({id:shopId}),
        type: "POST",
        contentType: 'application/json',
        success: function (data) {
            if (data["error"]) alert(data["error"])
            $.each(data, function(i, item) {
                obj.append(new Option(item['title'], item['id']))
            });
            if(selected)  obj.val(selected)
        }
    })
}
function reloadDepartmentsAdmin(shopId,obj, selected){
    reloadDepartmentsPrivate(shopId,obj,  "/ajax/beacon/departments.json", selected)
}
function reloadDepartmentsUser(shopId,obj, selected){
    reloadDepartmentsPrivate(shopId,obj,  "/ajax/shop/departments.json", selected)
}

function reloadShopsAdmin(username,obj, selected){
    obj.empty().append(new Option("--- магазин ---", ""))
    if (!username) return;
    $.ajax({
        url: "/ajax/beacon/shops.json",
        data: JSON.stringify({username:username}),
        type: "POST",
        contentType: 'application/json',
        success: function (data) {
            if (data["error"]) alert(data["error"])
            $.each(data, function(i, item) {
                obj.append(new Option(item['title'], item['id']))
            });
            if(selected)  obj.val(selected)
        }
    })
}

function reloadShopsUser(obj, selected){
    obj.empty().append(new Option("--- магазин ---", ""))
    $.ajax({
        url: "/ajax/shop/shops.json",
        type: "POST",
        contentType: 'application/json',
        success: function (data) {
            if (data["error"]) alert(data["error"])
            $.each(data, function(i, item) {
                obj.append(new Option(item['title'], item['id']))
            });
            if(selected)  obj.val(selected)
        }
    })
}