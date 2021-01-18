# bgbilling-servlet-demo-kotlin

## Что это?

bgbilling-servlet-demo-kotlin -- это Kotlin-версия демонстрационной реализации сервлетов и фильтров для использования совместно с сервером [BGBilling](https://bgbilling.ru/).

## Требования

* git
* JDK 8
* [gradle](https://gradle.org/)

## Как это установить?

Выполните команды:

```
git clone https://github.com/alexanderfefelov/bgbilling-servlet-demo-kotlin
cd bgbilling-servlet-demo-kotlin
gradle assemble
```

Скопируйте jar-файл, созданный в результате в каталоге `build/libs`, в каталог `lib/custom` вашего экземпляра BGBilling.

## Привет, мир!

- [HelloWorldKotlin.kt](src/main/kotlin/com/github/alexanderfefelov/bgbilling/servlet/demo/HelloWorldKotlin.kt)
- [TerryPratchettFilterKotlin.kt](src/main/kotlin/com/github/alexanderfefelov/bgbilling/servlet/demo/TerryPratchettFilterKotlin.kt)

В конфигурацию BGBilling добавьте:

```properties
# Servlet: Привет, мир!
#
custom.servlet.keys=HelloWorldKotlin
#                   │              │
#                   └────┬─────────┘
#                        │
#                  Ключ сервлета                               Класс сервлета
#                        │                                            │
#              ┌─────────┴────┐       ┌───────────────────────────────┴─────────────────────────────────┐
#              │              │       │                                                                 │
custom.servlet.HelloWorldKotlin.class=com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldKotlin
custom.servlet.HelloWorldKotlin.mapping=/demo-servlet/hello-world-kotlin
#                                       │                              │
#                                       └───────────────┬──────────────┘
#                                                       │
#                                           Часть URL после контекста
#
custom.servlet.HelloWorldKotlin.filter.keys=TerryPratchettKotlin
#                                           │                  │
#                                           └──────┬───────────┘
#                                                  │
#                                             Ключ фильтра
#                                                  │
#                                      ┌───────────┴──────┐
#                                      │                  │
custom.servlet.HelloWorldKotlin.filter.TerryPratchettKotlin.name=TerryPratchettKotlin
custom.servlet.HelloWorldKotlin.filter.TerryPratchettKotlin.class=com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterKotlin
#                                                                 │                                                                           │
#                                                                 └──────────────────────────────────┬────────────────────────────────────────┘
#                                                                                                    │
#                                                                                              Класс фильтра
```

Перезапустите BGBilling.

Если всё в порядке, в логах можно будет увидеть:

```
01-16/15:48:17  INFO [main] Server - Add custom servlet from setup...
01-16/15:48:17  INFO [main] Server - Custom.servlet.keys => HelloWorldKotlin
01-16/15:48:17  INFO [main] Server - Custom.servlet.class => com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldKotlin
01-16/15:48:17  INFO [main] Server - Custom.servlet.mapping => /demo-servlet/hello-world-kotlin
01-16/15:48:17  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldKotlin to /demo-servlet/hello-world-kotlin
01-16/15:48:17  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterKotlin to /demo-servlet/hello-world-kotlin
```

Теперь выполните:

```
http --verbose --check-status \
  GET http://bgbilling-server.backpack.test:63081/billing/demo-servlet/hello-world-kotlin
```

В результате на запрос:

```
GET /billing/demo-servlet/hello-world-kotlin HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: bgbilling-server.backpack.test:63081
User-Agent: HTTPie/1.0.3
```

будет получен ответ:

```
HTTP/1.1 200 OK
Content-Length: 14
Date: Sat, 16 Jan 2021 12:48:53 GMT
X-Clacks-Overhead: GNU Terry Pratchett

Hello, World!
```

## О системе

- [SysInfoKotlin.kt](src/main/kotlin/com/github/alexanderfefelov/bgbilling/servlet/demo/SysInfoKotlin.kt)
- [UptimePuncherFilterKotlin.kt](src/main/kotlin/com/github/alexanderfefelov/bgbilling/servlet/demo/UptimePuncherFilterKotlin.kt)

Добавьте в конфигурацию BGBilling:

```properties
# Servlet: О системе
#
custom.servlet.keys=SysInfoKotlin
#                   │           │
#                   └───┬───────┘
#                       │
#                 Ключ сервлета                            Класс сервлета
#                       │                                         │
#              ┌────────┴──┐       ┌──────────────────────────────┴───────────────────────────────┐
#              │           │       │                                                              │
custom.servlet.SysInfoKotlin.class=com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoKotlin
custom.servlet.SysInfoKotlin.mapping=/demo-servlet/sys-info-kotlin
#                                    │                           │
#                                    └─────────────┬─────────────┘
#                                                  │
#                                      Часть URL после контекста
#
custom.servlet.SysInfoKotlin.filter.keys=UptimePuncherKotlin,TerryPratchettKotlin,CORS
#                                        │                 │ │                  │ │  │
#                                        └─────┬───────────┘ └────────┬─────────┘ └─┬┘
#                                              │                      │             │
#                                         Ключ фильтра           Ещё фильтр       И ещё один
#                                              │
#                                   ┌──────────┴──────┐
#                                   │                 │
custom.servlet.SysInfoKotlin.filter.UptimePuncherKotlin.name=UptimePuncherKotlin
custom.servlet.SysInfoKotlin.filter.UptimePuncherKotlin.class=com.github.alexanderfefelov.bgbilling.servlet.demo.UptimePuncherFilterKotlin
#                                                             │                                                                          │
#                                                             └──────────────────────────────────┬───────────────────────────────────────┘
#                                                                                                │
#                                                                                          Класс фильтра
custom.servlet.SysInfoKotlin.filter.TerryPratchettKotlin.name=TerryPratchettKotlin
custom.servlet.SysInfoKotlin.filter.TerryPratchettKotlin.class=com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterKotlin
custom.servlet.SysInfoKotlin.filter.CORS.name=CORS
custom.servlet.SysInfoKotlin.filter.CORS.class=org.apache.catalina.filters.CorsFilter
custom.servlet.SysInfoKotlin.filter.CORS.init-param.keys=AllowedOrigins
#                                                        │            │
#                                                        └───┬────────┘
#                                                            │
#                                                      Ключ параметра    Название параметра
#                                                            │                    │
#                                                   ┌────────┴───┐      ┌─────────┴────────┐
#                                                   │            │      │                  │
custom.servlet.SysInfoKotlin.filter.CORS.init-param.AllowedOrigins.name=cors.allowed.origins
custom.servlet.SysInfoKotlin.filter.CORS.init-param.AllowedOrigins.value=*
#                                                                        │
#                                                                        │
#                                                               Значение параметра
```

Перезапустите BGBilling.

Теперь в логах будет так:

```
01-18/11:32:33  INFO [main] Server - Add custom servlet from setup...
01-18/11:32:33  INFO [main] Server - Custom.servlet.keys => SysInfoKotlin
01-18/11:32:33  INFO [main] Server - Custom.servlet.class => com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoKotlin
01-18/11:32:33  INFO [main] Server - Custom.servlet.mapping => /demo-servlet/sys-info-kotlin
01-18/11:32:33  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoKotlin to /demo-servlet/sys-info-kotlin
01-18/11:32:33  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.UptimePuncherFilterKotlin to /demo-servlet/sys-info-kotlin
01-18/11:32:33  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterKotlin to /demo-servlet/sys-info-kotlin
01-18/11:32:33  INFO [main] Server - Add mapping: org.apache.catalina.filters.CorsFilter to /demo-servlet/sys-info-kotlin
```

и в ответ на запрос:

```
http --verbose --check-status \
  GET http://bgbilling-server.backpack.test:63081/billing/demo-servlet/sys-info-kotlin \
    "Origin: http://example.com"
```

```
GET /billing/demo-servlet/sys-info-kotlin HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: bgbilling-server.backpack.test:63081
Origin: http://example.com
User-Agent: HTTPie/1.0.3
```

вы получите:

```
HTTP/1.1 200 OK
Access-Control-Allow-Credentials: true
Access-Control-Allow-Origin: http://example.com
Content-Length: 524
Date: Mon, 18 Jan 2021 08:56:19 GMT
Vary: Origin
X-BGBilling-Server-Uptime: Started: 18.01.2021 11:56:06 Uptime: 0 d 00:00:42
X-Clacks-Overhead: GNU Terry Pratchett

Modules
--------------------------------------------------

0 kernel 8.0.1320 / 16.12.2020 18:10:08
2 inet 8.0.832 / 15.12.2020 17:06:32
1 card 8.0.307 / 06.10.2020 01:52:21
3 npay 8.0.287 / 19.11.2020 18:41:17
5 subscription 8.0.128 / 06.10.2020 01:52:39
4 rscm 8.0.272 / 06.10.2020 01:52:36

Runtime
--------------------------------------------------

Hostname/IP address: bgbilling-server.backpack.test/172.17.0.8
Available processors: 8
Memory free / max / total, MB: 314 / 444 / 345
```

## Логи

Для того, чтобы логи собирались в отдельном файле, необходимо изменить `data/log4j.xml`.

Добавьте новый аппендер:

```xml
<appender name="SERVLET" class="org.apache.log4j.RollingFileAppender">
    <param name="File" value="log/servlet.log"/>
    <param name="MaxFileSize" value="50MB"/>
    <param name="MaxBackupIndex" value="3"/>
    <param name="Append" value="true"/>

    <layout class="org.apache.log4j.PatternLayout">
        <param name="ConversionPattern" value="%d{MM-dd/HH:mm:ss} %5p [%t] %c{1} - %m%n"/>
    </layout>

    <filter class="ru.bitel.common.logging.Log4JMDCFilter">
        <param name="key" value="nestedContext"/>
        <param name="value" value="servlet"/>
    </filter>
</appender>
```

и исправьте имеющийся:

```xml
<appender name="ASYNC" class="ru.bitel.common.logging.Log4jAsyncAppender">
    <appender-ref ref="APPLICATION"/>
    <appender-ref ref="ERROR"/>
    <appender-ref ref="MQ"/>
    <appender-ref ref="SCRIPT"/>
    <appender-ref ref="SERVLET"/>
</appender>
```

В результате после перезапуска BGBilling в файле `log/servlet.log` можно будет увидеть что-то вроде:

```
01-18/11:32:41 TRACE [localhost.localdomain-startStop-1] UptimePuncherFilterKotlin - init
01-18/11:32:41 TRACE [localhost.localdomain-startStop-1] TerryPratchettFilterKotlin - init
01-18/11:33:49 TRACE [http-nio-0.0.0.0-8080-exec-2] SysInfoKotlin - init
01-18/11:33:49 TRACE [http-nio-0.0.0.0-8080-exec-2] UptimePuncherFilterKotlin - doFilter
01-18/11:33:49 TRACE [http-nio-0.0.0.0-8080-exec-2] TerryPratchettFilterKotlin - doFilter
01-18/11:33:49 TRACE [http-nio-0.0.0.0-8080-exec-2] SysInfoKotlin - doGet
```

## Что дальше?

* Ознакомьтесь с [описанием технологии Servlet](https://docs.oracle.com/javaee/7/tutorial/servlets.htm).
* Изучите [список фильтров, встроенных в Tomcat 8.5](https://tomcat.apache.org/tomcat-8.5-doc/config/filter.html).
* Посмотрите аналогичные проекты на других языках:
  * Clojure - https://github.com/alexanderfefelov/bgbilling-servlet-demo-clojure,
  * Groovy - https://github.com/alexanderfefelov/bgbilling-servlet-demo-groovy,
  * Java - https://github.com/alexanderfefelov/bgbilling-servlet-demo,
  * Scala - https://github.com/alexanderfefelov/bgbilling-servlet-demo-scala.
