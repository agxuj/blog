# Java BigDecimal笔记

<script src="../js/index.js"></script>
<div id="content"></div>


 
## 简单使用
### 例子
``````
String money = "100.00";
BigDecimal dbMoney = new BigDecimal(money);
dbMoney.add(new BigDecimal("20.00"));
String result = dbMoney.toString();
``````
### setScale

BigDecimal.setScale()方法用于格式化小数点

setScale(1)表示保留一位小数，默认用四舍五入方式

``````
BigDecimal data = new BigDecimal("9.655").setScale(2, BigDecimal.ROUND_HALF_UP);
System.out.println("data=" + data);
//结果：data=9.66
``````

### 其他常用方法
1. 加法 add()     
1. 减法 subtract()
1. 乘法 multiply()    
1. 除法 divide()    
1. 绝对值 abs()

## 格式化

### "#" 说明
* 整数部分多了:不会截断,但是排在有效位最前面的0会被删除
* 整数部分少了:不作处理
* 小数部分多了:截断,建议指定RoundingMode,默认为RoundingMode.HALF_EVEN
* 小数部分少了:不作处理
### "0" 说明
* 整数部分多了:不会截断,排在有效位前面的0也不会被删除
* 整数部分少了:补0
* 小数部分多了:截断,建议指定RoundingMode,默认为RoundingMode.HALF_EVEN
* 小数部分少了:补0 

**其他类型字符可查看API文档**
``````
DecimalFormat format = new DecimalFormat("#.##");
BigDecimal val= new BigDecimal("1.23");
format.format(val);
``````
## 舍入模式
1. ROUND_UP 
1. ROUND_DOWN 
1. ROUND_CEILING 
1. ROUND_FLOOR 
1. ROUND_HALF_UP (四舍五入)
1. ROUND_HALF_DOWN
1. ROUND_HALF_EVEN 

``````
BigDecimal result = new BigDecimal("1.23").divide(new BigDecimal("1.23"),BigDecimal.ROUND_HALF_UP);
``````

## 参考
[Java中BigDecimal的8种舍入模式](https://blog.csdn.net/u010575112/article/details/81560275)

[Java中DecimalFormat用法详解](https://www.jb51.net/article/135720.htm)

[DecimalFormat格式化 # 和 0 的区别](https://blog.csdn.net/qq_28988969/article/details/97394848)

[BigDecimal加减乘除计算](https://blog.csdn.net/haiyinshushe/article/details/82721234)

[BigDecimal.setScale 处理java小数点](https://blog.csdn.net/ahwr24/article/details/7048724)