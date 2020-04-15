# 20191010150107
 


 

## 静态包含
 
`````
<%@ include file="inlayingJsp.jsp" %>
`````
## 动态包含
`````
<jsp:include page="inlayingJsp.jsp" flush="true"/>
`````
## jstl import 

<c:import>标签提供了所有<jsp:include>行为标签所具有的功能，同时也允许包含绝对URL。

`````
<c:import url="inlayingJsp.jsp"></c:import> 
`````

References:

[JSP中include的动态引入和静态引入](https://baijiahao.baidu.com/s?id=1590373998823758796&wfr=spider&for=pc)

[一个jsp页面引入另一个jsp页面的三种方式 及静态引入和动态引入的区别](https://blog.csdn.net/fn_2015/article/details/70311495)

