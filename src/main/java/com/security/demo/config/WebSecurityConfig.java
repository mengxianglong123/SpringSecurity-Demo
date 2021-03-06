package com.security.demo.config;

import com.security.demo.security.JwtAccessDeniedHandler;
import com.security.demo.security.JwtAuthenticationEntryPoint;
import com.security.demo.security.JwtAuthenticationTokenFilter;
import com.security.demo.security.MyAuthenticationSuccessHandler;
import com.security.demo.service.JwtUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ????????????Jwt Token?????????
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }




    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                //??????????????????????????????
                .successHandler(myAuthenticationSuccessHandler)
                // ?????????????????????URI
                .loginProcessingUrl("/login")
                .and()
                //token??????????????????????????????csrf?????????
                .csrf().disable()
                // ????????????????????????
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // ??????????????????????????????
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                //????????????????????????,????????????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                //?????????????????????????????????
                .anyRequest().authenticated();


        //???????????????jwt???????????????
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}
