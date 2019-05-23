package com.friendtimes.auth.service;

import com.alibaba.fastjson.JSON;
import com.friendtimes.utils.MD5Util;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

	@Autowired
	RestTemplate restTemplate;

	// 创建jwt令牌
	@Test
	public void testCreateJwt() {

		// 密钥库文件
		String keystore = "bsp.keystore";
		// 密钥库的密码
		String keystore_password = "bojoykeystore";

		// 密钥库文件路径
		ClassPathResource classPathResource = new ClassPathResource(keystore);
		// 密钥别名
		String alias = "bojoykey";
		// 密钥的访问密码
		String key_password = "bojoy_bsp";
		// 密钥工厂
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,
				keystore_password.toCharArray());
		// 密钥对（公钥和私钥）
		KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
		// 获取私钥
		RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
		// jwt令牌的内容
		Map<String, String> body = new HashMap<>();
		body.put("test", "002");
		String bodyString = JSON.toJSONString(body);
		System.out.println("0.===================================================" + bodyString);
		// 生成jwt令牌
		Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
		// 生成jwt令牌编码
		String encoded = jwt.getEncoded();
		System.out.println("1.===================================================");
		System.out.println(encoded);

	}

	// 校验jwt令牌
	@Test
	public void testVerify() {
		// 公钥
		String publickey = "-----BEGIN PUBLIC KEY-----"
				+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxauZ3zZxddZRjeTc+pLtgOTQY4wKGLPi7Ujb9+L9FDyzJzlFYjiyWNHloUUa1IehDoB4XFyc38Qo+D+IXnoMArXw+vHRyRR+tjGPpRjLbCZfka90EsOYtY4mR7UQiMt1PAFCvUN1Q1+9PX5FNEN7PfHaXcw64hEuXAJRvZcApSkgszKFszCGybnkBXQr+QKRwYaOvvthEFCve5kKb/gLwFEUu9NJYzeNuogtX4TrctIVwLet3n7T5jPoi7JY/+SV96SBN9N2PRuEVylQNoLJ6usky0XsvJFoyNw3QeiMvMbVjXZw36VpdKSxDesuUgQ/s3jSSPG/DS6L4HTWhBsJGQIDAQAB"
				+ "-----END PUBLIC KEY-----";
		// jwt令牌
		String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZXN0IjoiMDAxIn0.D7F3mbA6N1hF20c8alE7otHr_ZyLtmDlrrhgWCAfSHHc2267f8cuuLEkyjkLbLkp6-L-kmtMG8SE6awCDMlqsh6z9VbhX1TD_vVdOQjSaNqHByI1MgybRINPZLXmOeeznvLGBg1U5bDgzuPd4SoryF485Nl6Gbglv0lTUCuJo3TSBIHk2HEjmAyGtpNPCkOhoe4yTX9BtIYyt5QfIu52PIgTBVVaA6FpsEOCY_BVwYep5tDBnnRjO2VCk6JxxyjRLlDAlyymrvQ99o7MSrmACRPDWYgLLco8WpEcvoR7yz9_7NA1S3tL4LYtOaWnt-A2i7SQMmnLr1WwKUz12I_FmA";
		// 校验jwt令牌
		Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
		// 拿到jwt令牌中自定义的内容
		String claims = jwt.getClaims();
		System.out.println("2.===================================================");
		System.out.println(claims);
	}

	@Test
	public void testAccount() {

		String authUrl = "http://nam.bojoy.net/api/ldap/checkusersz?format=json";
		String secret_id = "FTIT";
		String secret_key = "513e919ba7b0b2391cf1e1fbef527ede";
		String adname = "zhushunchang";
		String passwd = "test";
		long time = System.currentTimeMillis() / 1000;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adname", adname);
		map.put("passwd", passwd);
		map.put("timestamp", time);
		map.put("secretid", secret_id);
		map = sortMapByKey(map);
		// 构造签名字符串
		String str = map.toString().replaceAll("\\,| |\\{|\\}", "");

		// 计算签名
		String sign = MD5Util.getStringMD5(str + secret_key);

		// 定义header
		LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
		// String httpBasic = getHttpBasic("te", "te");
		// header.add("Authorization", httpBasic);

		// 定义body
		LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("adname", adname);
		body.add("passwd", passwd);
		body.add("timestamp", time + "");
		body.add("secretid", secret_id);
		body.add("sign", sign);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

		System.out.println(exchange.getBody());
	}

	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>();
		sortMap.putAll(map);

		return sortMap;
	}
	
	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("admin"));
		//声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/bsp";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = (Connection) DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = (Statement) con.createStatement();
            //要执行的SQL语句
            String sql = "select role_name,(select 6 from dual) role_code from sys_role";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  
            System.out.println("姓名" + "\t" + "职称");  
            System.out.println("-----------------");  
             
            String job = null;
            String id = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("role_name");
                //获取stuid这列数据
                id = rs.getString("role_code");

                //输出结果
                System.out.println(id + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }
	

}
