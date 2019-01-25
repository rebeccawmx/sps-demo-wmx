# Spring-Security [^1]

## 快速开始[^2]

### 保护一个 Web 程序

## 6.3 Java Configuration and Form Login

You might be wondering where the login form came from when you were prompted to log in, since we made no mention of any HTML files or JSPs. Since Spring Security’s default configuration does not explicitly set a URL for the login page, Spring Security generates one automatically, based on the features that are enabled and using standard values for the URL which processes the submitted login, the default target URL the user will be sent to after logging in and so on.

While the automatically generated log in page is convenient to get up and running quickly, most applications will want to provide their own log in page. To do so we can update our configuration as seen below:

```java
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login") 
            .permitAll();        
}
```

| [![1](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/images/callouts/1.png)](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#CO1-1) | The updated configuration specifies the location of the log in page. |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [![2](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/images/callouts/2.png)](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#CO1-2) | We must grant all users (i.e. unauthenticated users) access to our log in page. The `formLogin().permitAll()` method allows granting access to all users for all URLs associated with form based log in. |





[^1]: 官方参考文档 https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/
[^2]: 快速开始 https://spring.io/guides/gs/securing-web/

