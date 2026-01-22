package vn.scrip.buoi38_bvn.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import vn.scrip.buoi38_bvn.entities.Role;
import vn.scrip.buoi38_bvn.entities.User;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Cho phép login / register
        if (uri.startsWith("/login") || uri.startsWith("/register")
                || uri.startsWith("/css") || uri.startsWith("/js")) {
            return true;
        }

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return false;
        }

        User user = (User) session.getAttribute("user");
        Role role = user.getRole();

        if (uri.startsWith("/admin") && role != Role.ADMIN) {
            response.sendRedirect("/access-denied");
            return false;
        }

        if (uri.startsWith("/librarian") && role != Role.LIBRARIAN) {
            response.sendRedirect("/access-denied");
            return false;
        }

        if (uri.startsWith("/reader") && role != Role.READER) {
            response.sendRedirect("/access-denied");
            return false;
        }

        return true;
    }
}
