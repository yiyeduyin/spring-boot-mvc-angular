package com.cmiracle.config;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cmiracle.service.AdminUserDetailService;
import com.cmiracle.util.Util;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	
	@Value("${encryptKey}")
	private String encryptKey;


    @Autowired
    private AdminUserDetailService adminUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN") //拦截后台请求
	        .antMatchers("/**").permitAll().and() //不拦截
	        .formLogin().loginPage("/login").defaultSuccessUrl("/admin").and() //登录成功后跳转到后台首页
	        .logout().logoutSuccessUrl("/login").clearAuthentication(true).deleteCookies("remove").invalidateHttpSession(true).and()
            .httpBasic().and()
			.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService).passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				if(Util.isNotNull(rawPassword)){
					String encode_rawPassword = encodePassword(rawPassword.toString());
					if(encode_rawPassword.equals(encodedPassword)){
						return true;
					}
				}
				return false;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				try {
					byte[] data = encryptKey.getBytes(ENCODING);
					// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
					SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
					// 生成一个指定 Mac 算法 的 Mac 对象
					Mac mac = Mac.getInstance(MAC_NAME);
					// 用给定密钥初始化 Mac 对象
					mac.init(secretKey);
					byte[] text = rawPassword.toString().getBytes(ENCODING);
					// 完成 Mac 操作
					return Hex.encodeHexString(mac.doFinal(text));
				} catch (Exception e) {
					e.printStackTrace();
					throw new SecurityException("encode rawPassword error: " + e.toString());
				}
			}
		});
    }
    
    private String encodePassword(String rawPassword){
    	try {
			byte[] data = encryptKey.getBytes(ENCODING);
			// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
			SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
			// 生成一个指定 Mac 算法 的 Mac 对象
			Mac mac = Mac.getInstance(MAC_NAME);
			// 用给定密钥初始化 Mac 对象
			mac.init(secretKey);
			byte[] text = rawPassword.getBytes(ENCODING);
			// 完成 Mac 操作
			return Hex.encodeHexString(mac.doFinal(text));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("encode rawPassword error: " + e.toString());
		}
    }

}