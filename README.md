# common-validation -- 复杂类型参数校验工具

##### 反射遍历字段，递归校验，支持自定义校验规则

## Installation
```xml
<dependency>
    <groupId>com.lyw</groupId>
    <artifactId>common-validation</artifactId>
    <version>1.0.5</version>
</dependency>
```

## Use
##### 简单的配置如下，可以嵌套对象，但对象的类上也都要有@Validated注解：
```java
@Validated
public class TestClass {

    @RegexParam(regex = "^1([3456789])\\d{9}$", message = "非法的手机号")
    private String mobile;

    @NumberString(message = "非法的数字")
    private String money;

}
```
##### 然后调用校验即可，配合AOP食用更佳
```java
TestClass test = new TestClass();
test.setMobile("123456");
test.setMoney("abc123");
CheckResult result = ValidationUtils.check(testClass);
```
##### result里能拿到不合法的字段签名和值，以及注解配置的message

##### 可以参照annotation和validator包，自定义自己的注解和校验规则

###### 搞Spring就是逊啦
