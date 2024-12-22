<h1 style="font-size: 2.5em;"> J2ee JDBC</h1>
 


## 简单使用

`````
public static void main(String[] args) throws Exception {
    //1.加载驱动程序
    Class.forName("com.mysql.jdbc.Driver");
    //2. 获得数据库连接
    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/imooc", "root", "root");
    //3.操作数据库，实现增删改查
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM imooc_goddess");
    //如果有数据，rs.next()返回true
    while(rs.next()){
        System.out.println(rs.getString("user_name")+" 年龄："+rs.getInt("age"));
    }
}
`````

## 增删改查
`````

public class GoddessDao {
    //增加
    public void addGoddess(Goddess g) throws SQLException {
        Connection conn = getConnection();
        String sql = "INSERT INTO imooc_goddess(user_name, sex, age, birthday, email, mobile,"+
                "create_user, create_date, update_user, update_date, isdel)"+
                "values(?,?,?,?,?,?,?,CURRENT_DATE(),?,CURRENT_DATE(),?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setString(1, g.getUser_name());
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3, g.getAge());
        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
        ptmt.setString(5, g.getEmail());
        ptmt.setString(6, g.getMobile());
        ptmt.setString(7, g.getCreate_user());
        ptmt.setString(8, g.getUpdate_user());
        ptmt.setInt(9, g.getIsDel());

        //执行
        ptmt.execute();
    }

    //修改
    public void updateGoddess() {
        Connection conn = getConnection();
        String sql = "UPDATE imooc_goddess" +
                " set user_name=?, sex=?, age=?, birthday=?, email=?, mobile=?,"+
                " update_user=?, update_date=CURRENT_DATE(), isdel=? "+
                " where id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, g.getUser_name());
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3, g.getAge());
        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
        ptmt.setString(5, g.getEmail());
        ptmt.setString(6, g.getMobile());
        ptmt.setString(7, g.getUpdate_user());
        ptmt.setInt(8, g.getIsDel());
        ptmt.setInt(9, g.getId());

        ptmt.execute();
    }

    //删除
    public void delGoddess(){
        Connection conn = getConnection();
        String sql = "delete from imooc_goddess where id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setInt(1, id);

        ptmt.execute();
    }

    //查询 多行
    public List<Goddess> query() throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM imooc_goddess");

        List<Goddess> gs = new ArrayList<Goddess>();
        Goddess g = null;
        while(rs.next()){
            g = new Goddess();
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));

            gs.add(g);
        }
        return gs;
    }

    //查询 单行
    public Goddess get(){
        Goddess g = null;
        Connection conn = getConnection();
        String sql = "select * from  imooc_goddess where id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setUpdate_user(rs.getString("update_user"));
            g.setIsDel(rs.getInt("isdel"));
        }
        return g;
    }

    public void getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/imooc", "root", "root");
        return conn;
    }
}
`````

## 常见接口

### Driver接口
Driver接口由数据库厂家提供，作为java开发人员，只需要使用Driver接口就可以了。在编程中要连接数据库，必须先装载特定厂商的数据库驱动程序，不同的数据库有不同的装载方法。如：

    装载MySql驱动：Class.forName("com.mysql.jdbc.Driver");
    装载Oracle驱动：Class.forName("oracle.jdbc.driver.OracleDriver");

### Connection接口

Connection与特定数据库的连接（会话），在连接上下文中执行sql语句并返回结果。DriverManager.getConnection(url, user, password)方法建立在JDBC URL中定义的数据库Connection连接上。

    连接MySql数据库：Connection conn = DriverManager.getConnection("jdbc:mysql://host:port/database", "user", "password");

    连接Oracle数据库：Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@host:port:database", "user", "password");

    连接SqlServer数据库：Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://host:port; DatabaseName=database", "user", "password");

常用方法：

    createStatement()：创建向数据库发送sql的statement对象。
    prepareStatement(sql) ：创建向数据库发送预编译sql的PrepareSatement对象。
    prepareCall(sql)：创建执行存储过程的callableStatement对象。
    setAutoCommit(boolean autoCommit)：设置事务是否自动提交。
    commit() ：在链接上提交事务。
    rollback() ：在此链接上回滚事务。
### Statement接口

用于执行静态SQL语句并返回它所生成结果的对象。

三种Statement类：

    Statement：由createStatement创建，用于发送简单的SQL语句（不带参数）。
    PreparedStatement ：继承自Statement接口，由preparedStatement创建，用于发送含有一个或多个参数的SQL语句。PreparedStatement对象比Statement对象的效率更高，并且可以防止SQL注入，所以我们一般都使用PreparedStatement。
    CallableStatement：继承自PreparedStatement接口，由方法prepareCall创建，用于调用存储过程。

常用Statement方法：

    execute(String sql):运行语句，返回是否有结果集
    executeQuery(String sql)：运行select语句，返回ResultSet结果集。
    executeUpdate(String sql)：运行insert/update/delete操作，返回更新的行数。
    addBatch(String sql) ：把多条sql语句放到一个批处理中。
    executeBatch()：向数据库发送一批sql语句执行。
### ResultSet接口

ResultSet提供检索不同类型字段的方法，常用的有：

    getString(int index)、getString(String columnName)：获得在数据库里是varchar、char等类型的数据对象。
    getFloat(int index)、getFloat(String columnName)：获得在数据库里是Float类型的数据对象。
    getDate(int index)、getDate(String columnName)：获得在数据库里是Date类型的数据。
    getBoolean(int index)、getBoolean(String columnName)：获得在数据库里是Boolean类型的数据。
    getObject(int index)、getObject(String columnName)：获取在数据库里任意类型的数据。

ResultSet还提供了对结果集进行滚动的方法：

    next()：移动到下一行
    Previous()：移动到前一行
    absolute(int row)：移动到指定行
    beforeFirst()：移动resultSet的最前面。
    afterLast() ：移动到resultSet的最后面。
    
使用后依次关闭对象及连接：ResultSet → Statement → Connection

## 事务

开启事务：

    setAutoCommit(boolean) 设置是否为自动提交事务，如果true（默认值为true）表示自动提交，也就是每条执行的SQL语句都是一个单独的事务，如果设置为false，那么相当于开启了事务了；con.setAutoCommit(false) 表示开启事务。

结束事务：

    commit 提交结束事务。
    rollback 回滚结束事务。

设置隔离级别：
    setTransactionIsolation(int level)

    Connection.TRANSACTION_READ_UNCOMMITTED；可能出现任何事物并发问题，什么都不处理；性能最好。
    Connection.TRANSACTION_READ_COMMITTED；防止脏读，不能处理不可重复读和幻读；性能比REPEATABLE READ好。
    Connection.TRANSACTION_REPEATABLE_READ；防止脏读和不可重复读，不能处理幻读；性能比SERIALIZABLE好。
    Connection.TRANSACTION_READ_SERIALIZABLE；不会出现任何并发问题，因为它是对同一数据的访问是串行的，非并发访问的；性能最差。

## 参考
[JDBC 使用说明](https://www.runoob.com/w3cnote/jdbc-use-guide.html)
[JDBC详解](https://www.cnblogs.com/erbing/p/5805727.html)
[JDBC处理事务](https://www.cnblogs.com/gdwkong/p/7633016.html)