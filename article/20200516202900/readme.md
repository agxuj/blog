<h1 style="font-size: 2.5em;"> Spring 控制反转,依赖注入</h1>
 



## 参考
[Java Spring框架入门教程](http://c.biancheng.net/spring/)

[Java Web开发系列课程：Spring框架入门](https://edu.aliyun.com/course/1202?utm_content=m_1000012634)


## 什么是控制反转(ioc)和依赖注入(ip) 

    控制反转 ( Inversion of Control )
    依赖注入 ( Dependency Injection ) 

请看下面代码A，留意注释。

`````
package com.example.dal;

public interface UserDal {
    public List getList();
}
`````

`````
package com.example.dal.mysql;

public class UserDalMySQL implements UserDal {
    @Override
    public List getList() {
        // get list from mysql
        return null;
    }
}

`````

`````
package com.example.dal.oracle;

public class UserDalOracle implements UserDal {
    @Override
    public List getList() {
        // get list from oracle
        return null;
    }
}

`````

`````
package com.example.service;

public class UserService {

    // 此处直接声明 UserDalMySQL 对象，如果使用 UserDalOracle 则声明之， 这里是 service 直接调用 dal 层.
    private UserDal userDal = new UserDalMySQL();
    public List getList(){
        return userDal.getList();
    }
}

`````

`````
package com.example.controller;

public class UserController {   
    public void showList(){
        UserService service = new UserService();
        show(service.getList());
    }
}
`````

再看下面代码B，留意注释。

`````
package com.example.dal;

public interface UserDal {
    public List getList();
}
`````

`````
package com.example.dal.mysql;

public class UserDalMySQL implements UserDal {
    @Override
    public List getList() {
        // get list from mysql
        return null;
    }
}

`````

`````
package com.example.dal.oracle;

public class UserDalOracle implements UserDal {
    @Override
    public List getList() {
        // get list from oracle
        return null;
    }
}

`````


`````
package com.example.service; 

public class UserService {

    //这里与代码A不同，UserDal是由外界传入，而非 UserService 自己声明，则 Service 与 DAL 解耦。
    private UserDal userDal;

    public void setUserDal(UserDal userDal) {
        this.userDal = userDal;
    }

    public List getList(){
        return userDal.getList();
    }
}
`````

`````
package com.example.controller;

public class UserController {

    public void showList(){
        UserService service = new UserService();
        // UserDal 在 Controller 声明并传给 Service.
        service.setUserDal(new UserDalOracle());
        show(service.getList());
    }
}
`````
小结1：控制 UserDal 对象生成的权限由 Service 转达 Controller， 这就是<span style="color:red">控制反转</span>。

小结2: Service 的 UserDal 依赖与 Controller 的传入，Service 是被动接收 Controller 传入的 UserDal 对象，这就是<span style="color:red">依赖注入</span>

## spring 的控制反转和依赖注入


再看下面代码C，留意注释。

`````
package com.example.dal;

public interface UserDal {
    public List getList();
}
`````

`````
package com.example.dal.mysql;

public class UserDalMySQL implements UserDal {
    @Override
    public List getList() {
        // get list from mysql
        return null;
    }
}

`````

`````
package com.example.dal.oracle;

public class UserDalOracle implements UserDal {
    @Override
    public List getList() {
        // get list from oracle
        return null;
    }
}

`````


`````
package com.example.service; 

public class UserService {

    private UserDal userDal;

    public void setUserDal(UserDal userDal) {
        this.userDal = userDal;
    }

    public List getList(){
        return userDal.getList();
    }
}
`````

`````
package com.example.controller;

public class UserController {

    public void showList(){

        UserService service = new UserService();
        //与代码B不同的是，UserDal 对象生成的控制权交到 beans.xml文件中
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")
        UserDal userDal = context.getBean("userDal");
        service.setUserDal(userDal);
        show(service.getList());
    }
}
`````

`````
beans.xml 文件

<beans>
    <bean name="userDal" class="com.example.dal.oracle.UserDalOracle">
    </bean>
</beans>

`````


小结1：控制 UserDal 对象生成的权限由 Service 转至 xml 文件，由 xml 文件配置，这里的 xml 称为 ioc 容器，这就是 spring 中的<span style="color:red">控制反转</span>。

小结2: Service 的 UserDal 依赖与 xml 文件的配置，Service 是被动接收 xml 文件的配置的对象，这就是 spring 中的<span style="color:red">依赖注入</span>。




