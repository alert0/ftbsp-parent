## 一.  协议规范

为进一步确保数据交互安全。正式地址必须遵循HTTPS协议。

## 二.  域名规范

​	每个项目要有且仅有一个自己唯一的域名+端口。格式规范如下：

```json
https://domain.name/
```

## 三.  API路径规范

作为接口路径，为了和其他路径完美区分，必须在路径中添加api目录

格式规范如下：api/

## 四.  版本控制规范

项目正式上线后，正式版本要确定接口版本、并备份接口代码。为方便管理，需要在接口路径中加入版本号信息。
格式规范如下：v1/
必须以字母开头，并以“/”结尾。更新版本后可以使用v2 v3等、依次递加。

## 五.  API命名规范

域名规范/子系统/API路径规范/版本控制规范。

格式规范如下：

```json
https://domain.name/hr/api/v1/
```

根据业务需求，可以在v1版本文件夹里创建，一个或者多个接口文件。user文件，里面存放用户级别的操作：如登陆、注册、修改密码等等。sms文件，里面存放对短信的接口操作：如发送验证码、验证手机号等等。

```json
https://domain.name/hr/api/v1/home/getAddress
https://domain.name/hr/api/v1/user/userLogin
```

接口方法文件命名必须统一使用驼峰命名法，所有接口命名方式，必须遵循如下规范。
（1）新增方法：如新增一个地址
命名规范：必须以“add”为前缀。例如addAddress

```json
https://domain.name/hr/api/v1/addAddress
```

（2）删除方法：如删除一个地址。
命名规范：必须以“delete”为前缀。例如deleteAddress

```json
https://domain.name/hr/api/v1/deleteAddress
```

（3）修改方法：如修改一个地址。
命名规范：必须以“updata”为前缀。例如updataAddress

```json
https://domain.name/hr/api/v1/updataAddress
```

（4）获取方法：如获取一个地址。
命名规范：必须以“get”为前缀。例如getAddress

```json
https://domain.name/hr/api/v1/getAddress
```

（5）获取列表方法：如获取一个地址列表。
命名规范：必须以“get”为前缀、“List”为后缀。例如getAddressList

```json
https://domain.name/hr/api/v1/getAddressList
```

其他规范：

发送验证码使用‘send’为前缀、保存一个数据以‘save’为前缀、上传图片以‘uploadImage’为名称等等。

## 六 .  请求响应规范

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



