TODO
-----------------------------------------
1、当前用户是否可以登录
2、用户登录后展示所拥有权限的菜单
3、用户点击菜单展示当前用户所拥有的操作按钮
-----------------------------------------
1、用shiro完成用户登录认证
	在userRealm中执行doGetAuthenticationInfo方法进行登录认证
2、用shiro完成用户菜单权限认证
	shiro不对菜单进行处理，需要根据当前用户的角色获取其菜单资源
3、用shiro标签完成用户按钮权限认证
	jsp页面使用shiro标签对是否拥有权限进行判断，同时在后台controller层的方法加shiro注解，
	在添加shiro注解时，必须开启spring aop注解才能生效,详见spring-mvc-shiro.xml配置

-------------------------------------------
1、使用spring aop注解进行对程序执行日志进行记录
	注解，参考 LogAnnotationAspect类	
2、如何在切面获取当前用户信息？	
	* shiro通过Subject subject = SecurityUtils.getSubject();
	* 若不使用shiro,需在web.xml 中先配置
	  <listener>  
            <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>  
      </listener>  后，在service方法利用
      HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
      可获取。详见 http://my.oschina.net/u/2007041/blog/420956
      二者的原理，都是利用ThreadLocal将 request相关对象 绑定到当前线程。

---------------------------------------------
1、shiro subject 中携带用户的登录名外还可以携带更多信息
	定义ShiroUser 静态内部类。静态内部类特点参考：http://www.cnblogs.com/end/archive/2012/12/21/2827672.html
	具体使用：参考UserRealm类与LogAnnotationAspect类
---------------------------------------------
1、将proxool数据库连接池更换为druid，proxool.xml需配置在web-info下，同时，需要在web.xml配置Listener，在封闭proxool listener方法。
	proxool不方便进行junit测试，同时效率更优些	

	
	
	
	
	

	
