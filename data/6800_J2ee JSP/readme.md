
## JSP表达式
JSP表达式的语法格式:
`````
<%= 表达式 %>
`````
等价的XML语句:
`````
<jsp:expression>
   表达式
</jsp:expression>
`````

## JSP 标准标签库（JSTL）

### 核心标签 
引用核心标签库的语法如下:
`````
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
`````

### 格式化标签
JSTL格式化标签用来格式化并输出文本、日期、时间、数字。引用格式化标签库的语法如下:
`````
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
`````

### JSTL函数
JSTL包含一系列标准函数，大部分是通用的字符串处理函数。引用JSTL函数库的语法如下:
`````
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
`````

## JSP 表达式语言（EL）
JSP EL允许您指定一个表达式来表示属性值。一个简单的表达式语法如下:
`````
${expr}
`````


## 参考
[JSP 教程](https://www.runoob.com/jsp/jsp-tutorial.html)