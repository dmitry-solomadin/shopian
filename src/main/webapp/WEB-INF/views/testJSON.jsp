<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tsyma
  Date: 2/5/15
  Time: 8:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>

<c:set var="jsonURL" value="/ajax/test.json" />

<script>
    $(document).ready(function () {
        $("#bb").click(function (event) {
            $.getJSON('${jsonURL}', function (data) {
                console.log(data)
                alert("OK");
            }).error(function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR)
                alert("ERROR: " + jqXHR.status + " (" + jqXHR.statusText + ")")
            });
        });
    });
</script>

<h3>Проблема: <a href="${jsonURL}" target="_blank">${jsonURL}</a> возвращает ошибку 406</h3>
<p>

Указание в head: "Accept: application/json" не помогает ни с момошью CURL
<pre><code>    curl -v -X GET -I -H "Accept: application/json" http://localhost:8080/ajax/test.json ;
    curl -v -X GET -I -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/ajax/test.json ;
</code></pre>
ни с помошью $.getJSON <button id="bb">send $.getJSON to ${jsonURL}</button>
</p>

Для генерации JSON использую jackson. Подключаю:
pom.xml:
<pre><code class="xml">
    &lt;properties&gt;
        &lt;jackson.version&gt;2.5.0&lt;/jackson.version&gt;
    &lt;/properties&gt;

    &lt;dependencies&gt;
        &lt;!-- Jackson JSON --&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
            &lt;artifactId&gt;jackson-core&lt;/artifactId&gt;
            &lt;version&gt;\${jackson.version}&lt;/version&gt;
        &lt;/dependency&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
            &lt;artifactId&gt;jackson-databind&lt;/artifactId&gt;
            &lt;version&gt;\${jackson.version}&lt;/version&gt;
        &lt;/dependency&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
            &lt;artifactId&gt;jackson-annotations&lt;/artifactId&gt;
            &lt;version&gt;\${jackson.version}&lt;/version&gt;
        &lt;/dependency&gt;
    &lt;/dependencies&gt;
</code></pre>

Контроллер выглядит так:
<pre><code class="java">    import me.shopian.shopian3.service.ForTestingJson;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.ResponseBody;

    @Controller
    @RequestMapping("/ajax")
    public class StatisticController {

        @RequestMapping(value = "/test.json",  produces="application/json")
        @ResponseBody
        public ForTestingJson c() {
            return new ForTestingJson("aaa");
        }
    }
</code></pre>
<pre><code class="java">    public class ForTestingJson {
        private String a;

        public ForTestingJson(String a) {this.a = a;}
        public String getA()            {return a;  }
        public void setA(String a)      {this.a = a;}
    }
</code></pre>

statisticController.c() диспатчером правильно вызывается и возвращает не null экземпляр ForTestingJson.
<p></p>
в springmvc-context.xml тоже вроде все что нужно вставил:
<pre><code class="xml">
&lt;!-- Enables the Spring MVC @Controller annotations --&gt;
&lt;mvc:annotation-driven/&gt;
&lt;!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory --&gt;
&lt;bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"&gt;
    &lt;property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/&gt;
    &lt;property name="prefix" value="/WEB-INF/views/" /&gt;
    &lt;property name="suffix" value=".jsp" /&gt;
&lt;/bean&gt;
</code></pre>

<script>$(document).ready(function() {
    $('pre code').each(function(i, block) {
        hljs.highlightBlock(block);
    });
});</script>
</body>
</html>
