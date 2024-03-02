//package com.bank.authorization.util;
//
//
//import com.bank.authorization.entity.Users;
//import io.jsonwebtoken.*;
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
//import java.util.Date;
//
//// Класс, генерирующий и проверяющий JWT
//public class JwtUtil {
//
//    private static final String SECRET_KEY = "secret"; // секретный ключ для шифрования
//    private static final String ISSUER = "copilot"; // имя издателя токена
//    private static final long EXPIRATION_TIME = 86400000; // время жизни токена в миллисекундах (24 часа)
//
//    // Метод для генерации JWT
//    public static String generateToken(Users user) {
//        // Создаем объект JwtBuilder, используя библиотеку JJWT
//        JwtBuilder builder = Jwts.builder()
//                .setSubject(user.getId().toString()) // устанавливаем id пользователя в качестве субъекта токена
//                .setIssuer(ISSUER) // устанавливаем имя издателя токена
//                .setIssuedAt(new Date()) // устанавливаем текущее время в качестве времени выдачи токена
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // устанавливаем время истечения токена
//                .claim("role", user.getRole()) // добавляем роль пользователя в качестве дополнительного параметра токена
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY); // подписываем токен секретным ключом и алгоритмом HS256
//        // Возвращаем токен в виде строки
//        return builder.compact();
//    }
//
//    // Метод для проверки JWT
//    public static Users validateToken(String token) {
//        // Пытаемся распарсить токен, используя библиотеку JJWT
//        try {
//            // Получаем объект Jws, который содержит заголовок, тело и подпись токена
//            Jws<Claims> jws = Jwts.parser()
//                    .setSigningKey(SECRET_KEY) // устанавливаем секретный ключ для проверки подписи
//                    .requireIssuer(ISSUER) // требуем, чтобы имя издателя токена совпадало с нашим
//                    .parseClaimsJws(token); // парсим токен
//            // Получаем тело токена, которое содержит параметры
//            Claims claims = jws.getBody();
//            // Извлекаем id, пароль и роль пользователя из параметров
//            Byte id = Byte.parseByte(claims.getSubject());
//            String password = claims.get("password", String.class);
//            String role = claims.get("role", String.class);
//            // Создаем и возвращаем объект User с этими данными
//            return new Users(id, role, id, password);
//        } catch (Exception e) {
//            // Если токен невалидный, выбрасываем исключение
//            throw new JwtException("Invalid token");
//        }
//    }
//}
//
//
////
////// Класс, настраивающий Spring Security
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////    // Метод для настройки фильтров
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        // Отключаем CSRF защиту, так как не используем формы
////        http.csrf().disable();
////        // Добавляем наш фильтр для всех защищенных URL
////        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
////        // Настраиваем правила доступа к URL
////        http.authorizeRequests()
////                .antMatchers("/login").permitAll() // разрешаем доступ к /login всем
////                .antMatchers("/admin/**").hasRole("ADMIN") // разрешаем доступ к /admin/** только роли ADMIN
////                .anyRequest().authenticated(); // требуем аутентификацию для всех остальных URL
////    }
////}
//
