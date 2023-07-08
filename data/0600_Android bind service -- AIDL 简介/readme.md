## AIDL（Android 接口定义语言）

AIDL可以利用它定义客户端与服务使进行进程间通信 (IPC) 时都认可的编程接口。 在 Android 上，一个进程通常无法访问另一个进程的内存。 尽管如此，进程需要将其对象分解成操作系统能够识别的原语，并将对象编组成跨越边界的对象。 编写执行这一编组操作的代码是一项繁琐的工作，因此 Android 会使用 AIDL 来处理。

## 数据类型

AIDL 支持的数据类型划分为四类，第一类是 Java 编程语言中的基本类型，第二类包括 String、List、Map 和 CharSequence，第三类是其他 AIDL 生成的 interface，第四类是实现了 Parcelable protocol 的自定义类。

使用第二类时，首先需要明白这些类不需要 import，是内嵌的。其次注意在使用 List 和 Map 此二者容器类时，需注意其元素必须得是 AIDL 支持的数据类型，List 可支持泛型，但是 Map 不支持，同时另外一端负责接收的具体的类里则必须是 ArrayList 和 HashMap。
 
使用第三、四类时，需要留意它们都是需要 import 的，但是前者传递时，传递的是 reference，而后者则是 value。

## 自定义数据类型

要传递自定义类型，首先要让自定义类型支持parcelable协议，实现步骤如下：

1. 自定义类型必须实现Parcelable接口，并且实现Parcelable接口的public void writeToParcel(Parcel dest, int flags)方法 。自定义类型中必须含有一个名称为CREATOR的静态成员， 该成员对象要求实现Parcelable.Creator接口及其方法。 

`````
package cn.jp.domain; 

import android.os.Parcel; 
import android.os.Parcelable; 

public class Person implements Parcelable {

	private Integer id; 
	private String name; 
	
	public Person() {} 
	
	public Person(Integer id, String name) { 
		this.id = id; 
		this.name = name; 
	}
	
	public Integer getId() { 
		return id; 
	}
	
	public void setId(Integer id) { 
		this.id = id; 
	}
	
	public String getName() { 
		return name; 
	} 
	
	public void setName(String name) { 
		this.name = name; 
	} 
	
	@Override 
	public int describeContents() { 
		return 0; 
	} 
	
	@Override 
	public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel 
		dest.writeInt(this.id); 
		dest.writeString(this.name); 
	} 
	
	//添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口 
	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){ 
		@Override 
		public Person createFromParcel(Parcel source) {
			//从Parcel中读取数据，返回person对象 
			return new Person(source.readInt(), source.readString()); 
		} 
	}
	
	@Override 
	public Person[] newArray(int size) { 
		return new Person[size]; 
	} 
}
`````


1. 创建一个aidl文件声明你的自定义类型。 
Parcelable接口的作用：实现了Parcelable接口的实例可以将自身的状态信息（状态信息通常指的是各成员变量的值）写入Parcel，也可以从Parcel中恢复其状态。 Parcel用来完成数据的序列化传递。

`````
package cn.jp.domain; 
parcelable Person; 

`````

## 定向 tag
AIDL中的定向 tag 表示了在跨进程通信中数据的流向。
1. in 表示数据只能由客户端流向服务端；
1. out 表示数据只能由服务端流向客户端；
1. inout 则表示数据可在服务端与客户端之间双向流通。

## oneway

方法定义成oneway代表这个Binder接口是异步调用，不会被阻塞。oneway可以用来修饰在interface之前，这样会造成interface内所有的方法都隐式地带上oneway；oneway也可以修饰在interface里的各个方法之前。被oneway修饰了的方法不可以有返回值，也不可以有带out或inout的参数。


## 参考

[【Android Pro】 AIDL进程间传递自定义类型参数](https://www.cnblogs.com/0616--ataozhijia/p/4952441.html)

[AIDL oneway 以及in、out、inout参数的理解](https://blog.csdn.net/anlian523/article/details/98476033)

[linkToDeath机制了解和使用](https://blog.csdn.net/stven_king/article/details/53783822)

[Android-你真的懂AIDL的oneway嘛？](https://blog.csdn.net/weiqifa0/article/details/104284978)

[android aidl oneway用法](https://blog.csdn.net/u010164190/article/details/73292012)

[你真的理解AIDL中的in，out，inout么？](https://www.jianshu.com/p/ddbb40c7a251)

[AIDL中的in，out，inout](https://www.cnblogs.com/chen-cai/p/9639796.html)

[bindService的BIND_AUTO_CREATE和BIND_WAIVE_PRIORITY要点](https://blog.csdn.net/pan11115111/article/details/70236354)