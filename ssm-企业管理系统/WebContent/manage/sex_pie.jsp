<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<!-- 导入jquery的核心依赖库 -->
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<!-- 导入echarts的核心依赖库 -->
<script type="text/javascript" src="js/echarts.common.min.js"></script>
<title>饼状图表</title>
</head>
<body>
 
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 50%; height: 500px; margin-top: 20px; display: inline-block; margin-left: 1%;"></div>
    <div id="main1" style="width: 40%; height: 500px;  display: inline-block; margin-left:2%;"></div>
	
</body>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById("main"));
//获取前5天每天的日期
var now = new Date()
var date = now.getDate();
var month = now.getMonth()+1;
var day = month+"月"+(date-1)+"号";
var day2 = month+"月"+(date-2)+"号";
var day3 = month+"月"+(date-3)+"号";
var day4 = month+"月"+(date-4)+"号";
var day5 = month+"月"+(date-5)+"号";
var option = {
	    title : {
	        text: '近5天支出收入情况',
	        x:'left'
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    legend: {
	        data: ['支出', '收入', '净收入']
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: [day5,day4,day3,day2,day],
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '支出收入/元',
	            min: 0,
	            max: 50000,
	            interval: 5000,
	            axisLabel: {
	                formatter: '{value}'
	            }
	        },
	        {
	            type: 'value',
	            name: '净收入/元',
	            min: 0,
	            max: 50000,
	            interval: 5000,
	            axisLabel: {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series: [
	        {
	            name: '支出',
	            type: 'bar',
	            data: []
	        },
	        {
	            name: '收入',
	            type: 'bar',
	            data: []
	        },
	        {
	            name: '净收入',
	            type: 'line',
	            yAxisIndex: 1,
	            data: []
	        }
	    ]
	};
	$.ajax({
	    type: "POST",
	  	//当发送json数据时添加：
	    url: "${pageContext.request.contextPath }/zhu",
	    error: function (data) {
	        alert("出错了！" );
	    },
	    success: function (data) {
	    	var a = data.split(",");
	    	var day1 = a[0]; 
	    	var day2 = a[1];
	    	var day3 = a[2];
	    	var day4 = a[3];
	    	var day5 = a[4];
	    	var rev1 = a[5];
	    	var rev2 = a[6];
	    	var rev3 = a[7];
	    	var rev4 = a[8];
	    	var rev5 = a[9];
	    	var sub1 = a[10];
	    	var sub2 = a[11];
	    	var sub3 = a[12];
	    	var sub4 = a[13];
	    	var sub5 = a[14];
	    	//对echarts模板进行数据填充 
	        option.series[0].data = [day5,day4,day3,day2,day1]
	        option.series[1].data = [rev5,rev4,rev3,rev2,rev1]
	        option.series[2].data = [sub5,sub4,sub3,sub2,sub1]
	      	//重新加载图表显示
	        myChart.setOption(option);
	    }
	});
</script>
<script type="text/javascript">
var myChart1 = echarts.init(document.getElementById("main1"));
/* 核心显示设置 */
var option1 = {
		/* 标题设置 */
	    title : {
	        text: '公司员工男女比例',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    /* 颜色和选项的对应关系 */
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data:['男','女']			//待填充数据项1
	    },
        color : [ 'blue', 'red' ],
	    /* 内容显示 */
	    series : [
	        {
	            name: '男女比例',  	//鼠标移至不同扇形区提示信息的标题
	            type: 'pie',	//指定数据生成的图表类型
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[], 		//待填充数据项2
	            /* 阴影区域设置 */
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

$.ajax({
    type: "POST",
  	//当发送json数据时添加：
    url: "${pageContext.request.contextPath }/sex_pie",
    error: function (data) {
        alert("出错了！" );
    },
    success: function (data) {
    	var a = data.split(",");
    	var man = a[0]; 
    	var women = a[1];
    	//对echarts模板进行数据填充 
        option1.series[0].data = [{value:man, name:'男'},{value:women, name:'女'}]
      	//重新加载图表显示
        myChart1.setOption(option1);
    }
});
</script>
</html>