<h1 style="font-size: 2.5em;"> Java Oval˵���ĵ�</h1>
 


## 注解说明

### @Assert

Check if evaluating the expression in thespecified expression language returns true.

�?查指定语�?的表达式返回值是否为true；这里表达式是groovy�?
 
 | 参数 | 说明|
 | - | - |
 | expr | 表达�? |
 | lang | 指明脚本语言 | 
 | errorCode | 错误编码（共有属性）<br/>（可以修改成自己的异常编码串�?<br/>net.sf.oval.constraint.Assert（默认�?�）
 | message | 错误描述（共有属性） | 
 | when | 前置条件（共有属性） |  

### @AssertFalse
Check if the value is false. 
    
�?查�?�是否为假或�?
    
Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

主要参数when,errorCode,message

### @AssertTrue
Check if the value is true.
    
### @AssertNull
Check if null. 

与@NotNull相反;

### @AssertURL
Check if the value is a valid URL. 

�?查�?�是否为有效的URL

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

| 参数 | 说明 | 
| - | - |
| connect(boolean) | Specifies if a connection to the URL should be attempted to verify its validity.<br/>是否发起连接进行尝试. | 


### @DateRange
Check if the date is within the a daterange. 

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

@DateRange可以验证字符串类型，请查看源码验�?
`````
示例�?
@DateRange(min="2010-10-01",max="now",message="dateis error.")
private String birthday;
`````

 | 参数 | 说明 |
 | - | - |
 | min | The upper date compared against in the format specified with the dateFormat parameter.If not specified then no upper boundary check is performed.<br>Special values are:<br/>1. now<br/>2. today<br/>3. yesterday<br/>4. tomorrow | 
 | max | The lower date compared against in the format specified with the dateFormat parameter.if not specified then no lower boundary check is performed.<br>Special values are:<br/>1. now<br/>2. today<br/>3. yesterday<br/>4. tomorrow | 

### @Future
`````
示例�?
@Future(message="date isfuture.") //不能验证字符�?
`````
### @Past
`````
示例�?
@Past(message="date is past.") //不能验证字符�?
`````
### @Email
Check if the value is a valid e-mailaddress. The check is performed based on a regular expression.

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

    
`````
示例�?
@Email(message="email is error.")
private String email;
`````

### @EqualToField
Check if value equals the value of thereferenced field.

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

`````
示例�?
//�?查userCode是否和userName相等；使用get方法�?
@EqualToField(value="userName" message="mustequals userName",useGetter=true)
private String userCode;
`````
@NotEqualToField
`````
示例�?
与@EqualToField相反
@NotNull(message="not null")
@NotEqualToField(value="userCode" message="can'tequals userCode")
private String userName;
`````

### @HasSubstring
Check if the string contains a certainsubstring.
    
Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull


 | 参数 | 说明 | 
 | - | - |
 | value | �?要给的子�? | 
 | ignoreCase（boolean�? | ignoreCase default false | 

`````
示例�?
@HasSubstring(value="san",ignoreCase=true,message="mustcontains 'san'")
private String userCode;
`````
### @Length
Check if the string representation has certain length. 

�?查字符串的长�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

 | 参数 | 说明 | 
 | - | - |
 | max | �?大长度，默认为：Integer.MAX_VALUE | 
 | min | 默认值为0 | 

### @MaxLength
Check if the string representation has certain length. 

�?查字符串的长�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

有value属�?�；表示和value进行比较

### @MinLength
Check if the string representation has certain length. 

�?查字符串的长�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull
    
有value属�?�；表示和value进行比较
    

### @Size
Check if the array,map, or collection has the given size. For objects of other types thelength of their String representation will be checked. 

�?查array、map或集合的大小；其他类型的对象�?查对应字符串的长度；建议字符长度使用@Length验证�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull


 | 参数 | 说明 | 
 | - | - |
 | max |  | 
 | min |  | 


### @MinSize
Check if the array,map, or collection has the given size. For objects of other types thelength of their String representation will be checked. 

�?查array、map或集合的大小；其他类型的对象�?查对应字符串的长度；建议字符长度使用@Length验证�?

### @MaxSize
Check if the array,map, or collection has the given size. For objects of other types thelength of their String representation will be checked. 

�?查array、map或集合的大小；其他类型的对象�?查对应字符串的长度；建议字符长度使用@Length验证�?


### @MemberOf, @NotMemberOf
Check if the string representation iscontained in the given string array.

�?查�?�是否包含在给定的数组中；@NotMemberOf实现相反效果�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull

 | 参数 | 说明 | 
 | - | - |
 | value 字符串数�? |  | 
 | ignoreCase | 默认值false | 

### @NotBlank, @NotNull, @NotEmpty

 | 参数 | 说明 | 
 | - | - |
 | NotBlank | Check if the string representation is not empty and does not only contain white spaces. | 
 | NotNull | Check if not null. | 
 | NotEmpty | Check if the string representation is not empty(""). | 

### @NotNegative
Check if the number is greater or equalzero. 

�?查�?�是否为非负�?

Note: This constraint is also satisfied when the value to validate is null, therefore you might also need to specified @NotNull
 

### @Range
Check if the number is in the given range.

@Range 有max和min 属�?�，�?查数值类型的范围�?

### @Min
Check if the number is greater than orequal to X.

### @Max
Check if the number is smaller than orequal to X.

### @Digits
Check if the String representation has thegiven max/min number of integral and fractional digits.

�?查字符串形式的数字范围，对应属�?�如�?

maxFraction = Integer.MAX_VALUE;
maxInteger = Integer.MAX_VALUE;
minFraction = 0;
minInteger = 0;

### @NotMatchPatternCheck, @MatchPatternCheck
Check if the specified regular expressionpattern is or not matched. 

正则表达式验�?

 | 参数 | 说明 | 
 | pattern | The regular expression(s) that must match or not match | 

### @ValidateWithMethod
Check the value by a method of the sameclass that takes the value as argument and returns true if valid and false ifinvalid.

验证值作为参数，使用同一个类的某个方法返回�?�的布尔值进行验证；

方法必须是和验证值在同一类中�? 

 | 参数 | 说明 | 
 | - | - | 
 | methodName String | name a the single parameter method to use for validation | 
 | Class<?> parameterType | type of the method parameter 方法的参数，及被验证值的类型 | 

### @CheckWith
Check the value by a method of the sameclass that takes the value as argument and returns true if valid and false ifinvalid.

使用net.sf.oval.constraint.CheckWithCheck.SimpleCheck实现该接口的类中isSatisfied方法来判断，返回true有效，false无效；如果实现类是内部的，必须为静�?�类�?


 | 参数 | 说明 | 
 | - | - | 
 | Class<? extendsCheckWithCheck.SimpleCheck> | value -- Check class to use for validation.<br/>指明验证�? | 
 | ignoreIfNull | this constraint will be ignored if the value to check is null | 


`````
示例：验证User类中的age字段
@CheckWith(value=CheckAge.class,message="agemust in (18~65)")
private int age;
`````
验证类如下：
`````
public class CheckAge implements CheckWithCheck.SimpleCheck {

    private static final long serialVersionUID =1L;

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        User user = (User)validatedObject;
        int age = user.getAge();
        if(age <18 || age > 65)
            return false;
        else
            return true;
    }
}
`````

## 自定义注�?
@Past和@Future不能验证字符串类型的日期；自定义@CPast和@CFuture，都提供要给参数指定日期格式，默认为：yyyy-MM-dd;

### 定义注解
`````
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Constraint(checkWith = CPastCheck.class)
public @interface CPast {
    Stringmessage() default "日期必须小于现在.";
    StringdateFormat() default "yyyy-MM-dd";
} 
`````

### 定义实现
`````
public class CPastCheck extends AbstractAnnotationCheck<CPast> {
    private static final longserialVersionUID = 1L;
    private StringdateFormat;

    public voidconfigure(final CPast constraintAnnotation) {
        super.configure(constraintAnnotation);
        setDateFormat(constraintAnnotation.dateFormat());
    }

    public booleanisSatisfied(Object validatedObject, 
            Object valueToValidate,
            OValContextcontext, 
            Validator validator) throws OValException {
        SimpleDateFormatsdf = new SimpleDateFormat(dateFormat);
        
        if(valueToValidate instanceof String) {
            try {
                Datedate = sdf.parse((String) valueToValidate);
                returndate.before(new Date());
            } catch (ParseException e) {
                e.printStackTrace();
                super.setMessage("日期格式错误,无法验证,请修改成正确格式.");
                return false;
            }
        }
        return false;
    }

    public StringgetDateFormat() {
        returndateFormat;
    }

    public voidsetDateFormat(String dateFormat) {
        this.dateFormat= dateFormat;
    }
}
`````

## Referencd
[java�?源验证框架OVAL帮助文档](https://blog.csdn.net/neweastsun/article/details/49154337/)