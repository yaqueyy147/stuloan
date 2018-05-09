/**
 * Created by suyx on 2017/6/12 0012.
 */

function ajaxErrorToLogin() {
    if (window != top){
        top.location.href = projectUrl + "/consoles/login?loginCode=-2";
    }
    location.href =  projectUrl + "/consoles/login?loginCode=-2";
}

function querydatagrid(obj,url,pageNumber,pageSize) {
    var handler = url + "?pageNumber=" + escape(pageNumber)+ "&pageSize=" + escape(pageSize);
    $(obj).datagrid('options').url = handler; //设置表格数据的来源URL
    $(obj).datagrid('reload'); //重新加载表格
}

function datagridpager(obj,url) {
    $(obj).datagrid('getPager').pagination({
        pageSize:10,
        pageList: [10, 20, 30,40,50],
        onSelectPage: function (pageNumber, pageSize) {
            querydatagrid(obj,url,pageNumber,pageSize);//分页查询
        }
    });
}