<h1 style="font-size: 2.5em;"> J2ee JSP ҳ���������ļ�</h1>
 


 

## 静�?�包�?
 
`````
<%@ include file="inlayingJsp.jsp" %>
`````
## 动�?�包�?
`````
<jsp:include page="inlayingJsp.jsp" flush="true"/>
`````
## jstl import 

<c:import>标签提供了所�?<jsp:include>行为标签�?具有的功能，同时也允许包含绝对URL�?

`````
<c:import url="inlayingJsp.jsp"></c:import> 
`````

References:

[JSP中include的动态引入和静�?�引入](https://baijiahao.baidu.com/s?id=1590373998823758796&wfr=spider&for=pc)

[�?个jsp页面引入另一个jsp页面的三种方�? 及静态引入和动�?�引入的区别](https://blog.csdn.net/fn_2015/article/details/70311495)

