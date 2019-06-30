package xpu.edu.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/bootstrap/**", "/index", "index.html").permitAll() //都可以访问
                .antMatchers("/users/**").hasRole("ADMIN") //需要对应的角色才能访问
                .and()
                .formLogin() //基于Form表单验证
                .loginPage("/login").failureUrl("login-error");//自定义登录界面
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //认证信息存储在内存中
        auth.inMemoryAuthentication().withUser("Tim").password("123456").roles("ADMIN");
    }
}