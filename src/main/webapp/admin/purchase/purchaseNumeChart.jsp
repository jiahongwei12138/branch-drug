<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>总店采购报表</title>
    <!--引入echarts.js-->
    <script src="../../js/echarts.js"></script>
    <script src="../../js/jquery-3.4.1.min.js"></script>
</head>
<body>
    <!--为ECharts准备一个具备大小（宽高）的Dom-->
    <div id = "main" style = "width:600px;height:400px;margin-left: 150px;margin-top: 70px;"></div>
    <script type = "text/javascript">
        //基于准备好的dom，初始化echarts实例
        var myChar = echarts.init(document.getElementById('main'));
        var dataArray = ['product', '吗啡', '敌敌畏', '阿莫西林','青霉素'];
 /*        //指定图表的配置项和数据
        var option ={
            title:{
                text:'采购数量报表',
            },
            tooltip:{},
            legend:{
                data:['数量']
            	,itemGap:20
            },
            xAxis:{
            	name:'时间',
                data:["第1周","第2周","第3周","第4周","第5周"]
            },
            yAxis:{
            	name:'收入'
            	
            },
            series:[{
                name:'收入',
                type:'bar',
                data:[1080,1802,2543,3076,4086]
            }]
        }; */
        
         var    option = {
        	    title:{
                    text:'采购数量报表',
                },
        		legend: {},
    		    tooltip: {},
    		    dataset: {
    		        dimensions: ['product', '吗啡', '敌敌畏', '阿莫西林','青霉素'],
    		        source: [
    		            {product: '2019-08', '吗啡': 43.3, '敌敌畏': 85.8, '阿莫西林': 93.7, '青霉素': 93.7},
    		            {product: '2019-09', '吗啡': 83.1, '敌敌畏': 73.4, '阿莫西林': 55.1, '青霉素': 93.7},
    		            {product: '2019-10', '吗啡': 86.4, '敌敌畏': 65.2, '阿莫西林': 82.5, '青霉素': 93.7},
    		            {product: '2019-11', '吗啡': 72.4, '敌敌畏': 53.9, '阿莫西林': 39.1, '青霉素': 93.7}
    		        ]
    		    },
    		    xAxis: {type: 'category',name:'时间'},
    		    yAxis: {name:'数量'},
    		    series: [
    		        {type: 'bar'},
    		        {type: 'bar'},
    		        {type: 'bar'},
    		        {type: 'bar'}
    		    ]
    		};
        // 异步加载数据
          $.post('${APP_PATH}/purchase/reportForms.do',{}).done(function(back) {//jQuery触发ajax 从后台异步获取数据
            //var str = eval('(' + data + ')'); //解析json
            //var jsonObj =  JSON.parse(data);//转换为json对象
            var jsonObj=back.pnlist;
            var amountArray=[];
            var nameArray=[];
          //6.ajax发起数据请求
    	/* 	$.ajax({
    			type : "post",
    			async : true, //异步请求（同步请求将会锁住浏览器，其他操作须等请求完成才可执行）
    			url : "${APP_PATH}/purchase/reportForms.do",
    			dataType : "json", //返回数据形式为json
    			success : function(back) { */	
    			//dataArray.push("product");
    		    for(var i in jsonObj.length){
    		    	 amountArray.push(jsonObj[i].ilist);
	                 nameArray.push(jsonObj[i]. month);
    		    	/* if (result[i].proName != proName) {
    		    		dataArray.push(proName);
    		    		proName = result[i].proName;
    		    	} */
	            }
	            for(var j=0;j<nameArray.length;j++){
	            	amountArray[j].unshift(nameArray[j]);
	            	
	            }
	            amountArray.unshift(dataArray);
	            myChar.hideLoading();
	            // 填入数据
	            option =({
	                title:{
	                    text:'采购数量报表',
	                },
	            	legend: {},
	                tooltip: {},
	                dataset: {
	                	//dimensions: dataArray,
	                    // 提供一份数据。 source对应的是个二元数组
	                    source:    amountArray
	                },
	                // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
	                xAxis: {
	                    type: 'category',
	                    name:'月份',
	                    axisLabel: {
	                        show: true,
	                        interval: 0
	                    }},
	                // 声明一个 Y 轴，数值轴。
	                yAxis: {name:'数量'},
	                // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
	                series: [
	                    {type: 'bar'},
	                    {type: 'bar'},
	                    {type: 'bar'},
	                    {type: 'bar'}
	                ]
	            });
       
         myChar.setOption(option);
     /*  },
		error : function(errorMsg) {
			//请求失败时执行该函数
			alert("图表请求数据失败!");
			myChar.hideLoading();
		}*/ 
   });  
    </script>
 
</body>
</html>