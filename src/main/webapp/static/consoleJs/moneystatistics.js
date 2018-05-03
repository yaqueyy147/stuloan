/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#dosearch").click(function () {
        var begindate = $("#begindate").val();
        var enddate = $("#enddate").val();
        var format = "%Y-%m-%d";
        var params = {};

        var type = $("#searchtype").val();
        if(type == 2){
            format = "%Y-%m";
            if($.trim(begindate).length > 7){
                begindate = begindate.substring(0,7);
            }
            if($.trim(enddate).length > 7){
                enddate = enddate.substring(0,7);
            }

        }
        if(type == 3){
            format = "%Y";
            if($.trim(begindate).length > 4){
                begindate = begindate.substring(0,4);
            }
            if($.trim(enddate).length > 4){
                enddate = enddate.substring(0,4);
            }
        }
        params.format = format;
        params.begindate = begindate;
        params.enddate = enddate;

        loadDataGrid(params);
    });

    var params = {};
    params.format = "%Y-%m-%d";
    loadDataGrid(params);

});

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    $("#moneystatisticslist").datagrid({
        url:"/consoles/moneystatisticslist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"moneydate",title:"时间",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return value;
                    }
                    return '';
                }},
            {field:"disburse",title:"支出(放款)",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }},
            {field:"income",title:"收入(还款)",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }}
        ]],
        loadFilter:pagerFilter
    });
}