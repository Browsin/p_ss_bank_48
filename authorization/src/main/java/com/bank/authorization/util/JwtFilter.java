//package com.bank.authorization.util;
//
//import com.bank.authorization.entity.Users;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Collections;
//
//// Класс, реализующий фильтр для аутентификации и авторизации с использованием JWT
//public class JwtFilter extends GenericFilterBean {
//
//    // Константа для имени заголовка, в котором передается JWT
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    // Метод для фильтрации запросов
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // Приводим запрос к типу HttpServletRequest
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        // Получаем значение заголовка AUTHORIZATION_HEADER
//        String token = httpRequest.getHeader(AUTHORIZATION_HEADER);
//        // Если токен не пустой и начинается с "Bearer "
//        if (token != null && token.startsWith("Bearer ")) {
//            // Удаляем префикс "Bearer " из токена
//            token = token.substring(7);
//            // Проверяем валидность токена и получаем объект User
//            Users user = JwtUtil.validateToken(token);
//            // Создаем объект Authentication, содержащий данные о пользователе
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    user.getId().toString(), // устанавливаем id пользователя в качестве имени пользователя
//                    user.
//
//                            getPassword(), // устанавливаем пароль пользователя
//                    Collections.singleton(new SimpleGrantedAuthority(user.getRole())) // устанавливаем роль пользователя в качестве авторитета
//            );
//            // Устанавливаем объект Authentication в SecurityContextHolder
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        // Продолжаем цепочку фильтров
//        chain.doFilter(request, response);
//    }
//}
