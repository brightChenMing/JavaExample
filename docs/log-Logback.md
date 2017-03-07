# Logback
======  
用于测试logback的使用

## 引入依赖
```xml
        <dependency>
               <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>1.2.1</version>
        </dependency>
```
logback-access提供对tomcat或jetty的日志记录
```xml
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-access</artifactId>
  <version>1.2.1</version>
</dependency>
```

## 基础知识
### 优先级
logback-test.xml>logback.groovy>logback.xml
\>META-INF\services\ch.qos.logback.classic.spi.Configurator>BasicConfigurator  
常用的配置举例如下
```xml
<configuration>

  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>myApp.log</file>

    <encoder>
      <pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n</pattern>
    </encoder>
  </appender>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="FILE" />
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

### appender
通过pattern指定日志的样式，输出地(控制台，文件等)  
属性和元素如下图  
![](https://logback.qos.ch/manual/images/chapters/configuration/appenderSyntax.png)  
layout一般直接使用encoder替代，其实使用的是 PatternLayoutEncoder  

1. 按输出类型
  * 文件  
   ch.qos.logback.core.FileAppender  
   ch.qos.logback.core.rolling.RollingFileAppender
   文件类型可以指定日志的收割策略
```xml
   <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
   	   <FileNamePattern>xxxx.log.%i.bak</FileNamePattern>
   	   <MinIndex>1</MinIndex>
   	   <MaxIndex>12</MaxIndex>
   </rollingPolicy>
```
   	
  * 控制台  
    ch.qos.logback.core.ConsoleAppender
    
  * socket
  ch.qos.logback.classic.net.SocketAppender
  ```xml
  <appender name="SOCKET" class="ch.qos.logback.classic.net.SocketAppender">
      <remoteHost>${host}</remoteHost>
      <port>${port}</port>
      <reconnectionDelay>10000</reconnectionDelay>
      <includeCallerData>${includeCallerData}</includeCallerData>
    </appender>
```

  * SMTP
  ```xml
  <appender name="EMAIL" class="ch.qos.logback.classic.net.SMTPAppender">
      <smtpHost>ADDRESS-OF-YOUR-SMTP-HOST</smtpHost>
      <to>EMAIL-DESTINATION</to>
      <to>ANOTHER_EMAIL_DESTINATION</to> <!-- additional destinations are possible -->
      <from>SENDER-EMAIL</from>
      <subject>TESTING: %logger{20} - %m</subject>
      <layout class="ch.qos.logback.classic.PatternLayout">
        <pattern>%date %-5level %logger{35} - %message%n</pattern>
      </layout>       
    </appender>
```

### Logger
记录器，通过名称来匹配到包和类，通过appender-ref指定日志记录形式
<logger name="" [level]="">
  <append-ref ref="append_name"/>
</logger>

#### root logger
```java
Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
```
#### Logger层次
logger通过名称来区分层次，如下  
Logger parent=LoggerFactory.getLogger("parent");  
Logger child=LoggerFactory.getLogger("parent.child");  
这样parent是child的父logger，子logger在没有设置level的情况下，会继承父logger的level  
一般的使用方式是获取package的logger，然后配置其输出

#### 格式化输出
支持{}作为占位符，如  
logger.info("this is {} and {}","first","second");


