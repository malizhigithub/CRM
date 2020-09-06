## 客户关系管理系统

- 系统开发环境以及版本
  - 操作系统： Windows_7
  - 集成开发工具： Eclipse EE_4.7
  - 编译环境：JDK_1.8
  - Web服务器：Tomcat_9.0
  - 数据库：MySQL_5.7.23

- 系统框架
  - spring框架
  - springmvc框架
  - mybatis框架
  - Logback日志框架
  - 安全验证框架
  - maven框架
  - layui前端框架
  - shiro安全框架

- 系统关键性技术
  - 基于角色的权限访问控制RBCA（Role-Based Access Control）
  - Spring+Springmvc+Mybatis三大框架
  - Ajax技术
  - springmvc文件上传
  - shiro安全框架
  - Redis缓存
  - JavaMail邮件
  - 基于aop切面的日志管理
  - Layui前端框架
  - 登录验证码
  - 富文本输入框
  - md5加密加盐

- 注意事项
  - 项目数据库在一级目录中，命名为CRM.sql，其中‘user’表为账户表
  - 部署项目前，需要配置好MqSQL数据库，Redis数据库、mail邮箱，这三个配置文件都在crm/src/main/resources/properties
  - 项目登录帐号：malizhi(管理员级别)，密码123456，部署项目后，可以到测试类中（test包下的TestUserService）进行添加账户，密码经过md5加密加盐
  - 登录页：如果是本地部署 http://localhost:8080/crm2/pages/login.jsp ,端口号以及项目名要与部署的环境一致
  - 订单可以在客户流失（客户是否流失由Spring定时器定时检测）模块中，点击客户详情，可以查看到此客户的历史订单，关于订单的数据问题，因为在企业模式中，订单数据是从销售系统中获取的，但由于没有外接销售系统，所以订单数据以及产品定价的数据是自个插入数据库的。
  
- 部署过程异常错误解决方法
  - 权限，菜单都会缓存到redis中，如果redis无法连接，将会报空指针错误或登陆后首页会显示404，请确保能连接上redis数据库
  - 如果有报此异常org/hyperic/sigar/SigarException，可以将WEB-INF/lib下的文件（根据你的系统以及位数选择）放在你的JDK/bin目录下
  - 在发布出来前，由于隐私关系删除了部分登录帐号（客户经理），如果出现此客户找不到对应的客户经理，删掉此客户即可

- 部分页面
  - 登录页
![](https://malizhi-blog-1252037601.cos.ap-guangzhou.myqcloud.com/crm-README/%E7%99%BB%E5%BD%95%E9%A1%B5.png)
  - 首页
![](https://malizhi-blog-1252037601.cos.ap-guangzhou.myqcloud.com/crm-README/%E9%A6%96%E9%A1%B5.png)
  - 权限管理
![](https://malizhi-blog-1252037601.cos.ap-guangzhou.myqcloud.com/crm-README/%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86.png)
  - 销售管理
![](https://malizhi-blog-1252037601.cos.ap-guangzhou.myqcloud.com/crm-README/%E9%94%80%E5%94%AE%E7%AE%A1%E7%90%86.png)
