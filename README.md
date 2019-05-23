

# 系统设计

## 系统架构

![1554867700206](/doc/assets/1554867700206.png)

## 部署结构

![1554867556706](/doc/assets/1554867556706.png)

## 项目结构

![1554867624664](/doc/assets/1554867624664.png)

1. 父工程，所有工程继承parent父工程。
2. 基础工程，所有微服务都可以依赖基础工程。
3. 微服务工程

## 内网环境

| 名称        | 地址                                | 说明 |
| ----------- | ----------------------------------- | ---- |
| eureka      | http://192.168.8.28:6001            |      |
| dockerHost  | http://192.168.8.28:2375            |      |
| registryUrl | http://192.168.8.28:5000            |      |
| redis       | 192.168.8.28:6379,192.168.8.29:6379 |      |
| mysql       | 192.168.8.28:3300,192.168.8.29:3300 |      |
|             |                                     |      |
|             |                                     |      |

网关 80
注册中心 6001-6002
认证服务 6100 <http://test.bojoy.net:6100/auth/login?username=test&password=123>
用户服务 6200
权限服务 6300

docker run -d -p 9090:9090 --name ftbsp-sso 192.168.8.28:5000/ftbsp-sso:1.01

# 公共服务组件

## 基础服务

- 提供单点登录，只需要登录一次就可以访问所有子系统；
- 提供公司各子系统公用服务，避免重复开发；
- 支持外网部署，按应用接口分配权限，用户安全保障可开启动态码方式验证，启用https；

## 单点登录

### 1.用户认证需求分析

用户认证与授权
目前运维基于LDAP提供了域控接口，RTX、人力资源系统（运维域控）、财务系统(独立权限)，后期采购系统、项目管理、资产管理、客户管理系统等都需要身份认证，按目前的方式对接复杂，各系统身份不互通，要多次登录；所以基础服务平台首先要解决用户的身份认证。
用户身份认证？
用户身份认证即用户去访问系统资源时系统要求验证用户的身份信息，身份合法方可继续访问。常见的用户身份认证表现形式有：用户名密码登录，指纹打卡等方式。
用户授权？
用户认证通过后去访问系统的资源，系统会判断用户是否拥有访问资源的权限，只允许访问有权限的系统资源，没有权限的资源将无法访问，这个过程叫用户授权。

### 2.用户认证

#### 单点登录技术方案

分布式系统要实现单点登录，通常将认证系统独立抽取出来，并且将用户身份信息存储在单独的存储介质，比如：MySQL、Redis，考虑性能要求，通常存储在Redis中，如下图： 

![1554703597561](/doc/assets/1554703597561.png)

##### 单点登录的特点

1、认证系统为独立的系统。
2、各子系统通过Http或其它协议与认证系统通信，完成用户认证。
3、用户身份信息存储在Redis集群。

Java中有很多用户认证的框架都可以实现单点登录：
1、Apache Shiro
2、CAS
3、Spring security CAS 

##### Oauth2认证流程

第三方认证技术方案最主要是解决认证协议的通用标准问题，因为要实现跨系统认证，各系统之间要遵循一定的接口协议。OAUTH协议为用户资源的授权提供了一个安全的、开放而又简易的标准。同时，任何第三方都可以使用OAUTH认证服务，任何服务提供商都可以实现自身的OAUTH认证服务，因而OAUTH是开放的。业界提供了OAUTH的多种实现如PHP、JavaScript，Java，Ruby等各种语言开发包，大大节约了程序员的时间，因而OAUTH是简易的。互联网很多服务如Open API，很多大公司如Google，Yahoo，Microsoft等都提供了OAUTH认证服务，这些都足以说明OAUTH标准逐渐成为开放资源授权的标准。Oauth协议目前发展到2.0版本，1.0版本过于复杂，2.0版本已得到广泛应用。
参考：https://baike.baidu.com/item/oAuth/7153134?fr=aladdin
Oauth协议：https://tools.ietf.org/html/rfc6749 

项目采用 Spring security + Oauth2完成用户认证及用户授权，Spring security 可定制的身份验证和访问控制，Spring security 框架集成了Oauth2协议，下图是项目认证架构图： 

![1554706191033](/doc/assets/1554706191033.png)

1、用户请求认证服务完成认证。
2、认证服务下发用户身份令牌，拥有身份令牌表示身份合法。
3、用户携带令牌请求资源服务，请求资源服务必先经过网关。
4、网关校验用户身份令牌的合法，不合法表示用户没有登录，如果合法则放行继续访问。
5、资源服务获取令牌，根据令牌完成授权。
6、资源服务完成授权则响应资源信息。 

项目认证服务基于Spring Security Oauth2进行构建，并在其基础上作了一些扩展，采用JWT令牌机制，并自定义了用户身份信息的内容。

```
	 +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+
Oauth2包括以下角色：
1、客户端
本身不存储资源，需要通过资源拥有者的授权去请求资源服务器的资源，比如：Android客户端、Web客户端（浏览器端）、微信客户端等。
2、资源拥有者
通常为用户，也可以是应用程序，即该资源的拥有者。
3、授权服务器（也称认证服务器）
用来对资源拥有的身份进行认证、对访问资源进行授权。客户端要想访问资源需要通过认证服务器由资源拥有者授权后方可访问。
4、资源服务器
存储资源的服务器，比如，用户管理服务器存储了用户信息，人资服务器存储了人员信息，微信的资源服务存储了微信的用户信息等。客户端最终访问资源服务器获取资源信息。

```

### 3.用户服务

##### 注册

- ~~域控接口：元数据从运维系统创建，服务平台提供API，代理域控服务用户相关功能（创建、查询、资料修改、重置密码、禁用）；~~

##### 登录

- 子系统按服务平台登录接口规范改造，认证中心可提供SDK方便接入，代理域控服务；

  ```java
  		String authUrl = "http://nam.bojoy.net/api/ldap/checkusersz?format=json";
  		String secret_id = "FTIT";
  ```

## 权限管理

### 用户权限

一个灵活、通用、方便的权限管理系统，需要对系统的所有资源进行权限控制；资源简单概括为静态资源（功能操作、数据列）和动态资源（数据），也分别称为对象资源和数据资源。

目标就是对应用系统的所有对象资源和数据资源进行权限控制，比如应用系统的功能菜单、各个界面的按钮、数据显示的列以及各种行级数据进行权限的操控。

权限的过程可以抽象概括为：判断【Who是否可以对What进行How的访问操作（Operator）】这个逻辑表达式的值是否为True的求解过程，即将权限问题转换为Who、What、How的问题。who、what、how构成了访问权限三元组。

安全原则：最小特权原则、责任分离原则和数据抽象原则

![1555376890128](/doc/assets/1555376890128.png)

用户：根据用户ID查询用户角色表，找到对应的角色ID；

角色：根据角色ID查询角色权限表，找到应用的权限ID；

权限：根据权限ID查询权限表，找到权限。

```sql
select
  id,
  code,
  p_id pid,
  menu_name menuname,
  url,
  is_menu ismenu,
  level,
  sort,
  status,
  icon,
  create_time createtime,
  update_time updatetiem
from sys_permission
where id in(
  select menu_id from sys_role_permission where role_id in (
	select role_id from sys_user_role where user_id = 1)
)  
```

平台使用接口方式返回基础权限列表，子系统配置或直接使用基础权限，返回[超级管理员,管理员,普通用户]

权限接口：

- 请求方式 POST

- 请求地址：https://domain.name/api/v1/user/permission

- 请求协议(Http Header)     

    键：Content-Type

    值：application/json;charset=UTF-8

- 安全协议(oAuth2 Http Header)

    键：Authorization

    值：Basic + 空格 + d2ViX2FwcDp3ZWJfYXBw

- 请求参数

    {

  ​      "token":"708edcad-53a5-4efc-a279-cfa117e48cd8",

  ​      "appId":"hr"

   }

| 参数     | 类型   | 必须 | 说明                  |
| -------- | ------ | ---- | --------------------- |
| token    | String | 是   | 账密认证后获取的token |
| appId    | String | 是   | 子系统ID              |
| username | String |      | 账号                  |

- 成功返回 

  {
      **"success"**:**true**,
      **"code"**:**"1"**,
      **"msg"**:**""**,
      **"name"**:**"系统管理员"**,
      **"data"**:[
          {
              **"id"**:**"001"**,
              **"code"**:**"sysmanager"**,
              **"menuName"**:**"系统管理"**
          }
      ],
      **"companyId"**:**null**
  }

- 错误返回

  {
      **"success"**:**false**,
      **"code"**:**99999**,
      **"msg"**:**"抱歉，系统繁忙，请稍后重试！"**
  }

## 业务服务

定义接口规范，子系统注册功能到平台

提供者：资产系统，维护采购数据编号、名称等，保存后需要通知人资系统存编号+名称；

平台：支持提供者发布功能，appid、url、参数（请求，响应）

消费者：人资系统，需要获取资产系统编号+名称

### 请求响应

请求方式：公共数据使用get方式请求，私有数据使用post方式请求。尽量全部是用post。
请求头：请求头根据项目需求添加配置参数。如：accept=‘application/json’等。请求头根据项目需求可以要求传入用户token、app名称版本、唯一验签码等加密数据。例如:

- 请求方式 POST

- 请求地址：https://domain.name/hr/api/v1/user/permission

- 请求头(Http Header)  可选 

  ```json
  Content-Type:application/json;charset=UTF-8
  token:*****************
  ```

- 安全协议(oAuth2 Http Header)  可选

  ```
  Authorization:Basic + 空格 + d2ViX2FwcDp3ZWJfYXBw
  ```

  ##### 请求参数

  ```json
  {
    "reqid":"12345",
    "userid":"111",
    "params":""
  }
  ```

  | 参数   | 类型   | 必填 | 说明   |
  | ------ | ------ | ---- | ------ |
  | reqid  | String | 是   | 请求ID |
  | userid | String | 是   | 用户ID |
  | params | String | 否   | 其它   |

  ##### 返回参数

  ###### 成功响应

  ```json
  {
      "isOk":true,
      "code":"200",
      "msg":"成功",
      "cause":"",
      "data":{
          "field1":"string",
          "field2":true,
          "field3":8.8,
          "field4":"1970-01-01 01:00:00"
      }
  }
  ```

  | 返回值      | 类型   | 说明                                              |
  | ----------- | ------ | ------------------------------------------------- |
  | isOk        | String | 本次调用是否成功（true:执行成功  false:执行失败） |
  | code        | String | 状态码                                            |
  | msg         | String | 操作提示信息                                      |
  | cause       | String | 异常堆栈信息                                      |
  | data        | Object | 结果数据                                          |
  | data.field1 | String | 明细数据                                          |

  ###### 失败响应

  ```json
  {
      "isOk":false,
      "code":"500",
      "msg":"失败",
      "cause":"cause xxx",
      "data":null
  }
  ```

## 消息组件

1.基础服务定义消息类型，子系统订阅对应的类型，提供方通知消息变化，客户端收到消息并做业务处理；

如开服消息，服务平台提供消息编号（01-开服），子系统订阅01消息，通过kafka协议进行监听；运维系统（消息提供方）触发开服动作，通知服务平台-》服务平台操作kafka->子系统接收消息。

基础服务平台提供短信、RTX、微信支持