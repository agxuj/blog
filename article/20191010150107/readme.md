<h1 style="font-size: 2.5em;"> J2ee JSP 页面中引入文件</h1>
 


 

## 闈欐?佸寘鍚?
 
`````
<%@ include file="inlayingJsp.jsp" %>
`````
## 鍔ㄦ?佸寘鍚?
`````
<jsp:include page="inlayingJsp.jsp" flush="true"/>
`````
## jstl import 

<c:import>鏍囩鎻愪緵浜嗘墍鏈?<jsp:include>琛屼负鏍囩鎵?鍏锋湁鐨勫姛鑳斤紝鍚屾椂涔熷厑璁稿寘鍚粷瀵筓RL銆?

`````
<c:import url="inlayingJsp.jsp"></c:import> 
`````

References:

[JSP涓璱nclude鐨勫姩鎬佸紩鍏ュ拰闈欐?佸紩鍏(https://baijiahao.baidu.com/s?id=1590373998823758796&wfr=spider&for=pc)

[涓?涓猨sp椤甸潰寮曞叆鍙︿竴涓猨sp椤甸潰鐨勪笁绉嶆柟寮? 鍙婇潤鎬佸紩鍏ュ拰鍔ㄦ?佸紩鍏ョ殑鍖哄埆](https://blog.csdn.net/fn_2015/article/details/70311495)

