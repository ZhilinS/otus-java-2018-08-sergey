package ru.otus.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.dao.AdminDao;
import ru.otus.dao.UserDao;
import ru.otus.dataset.AdminDataSet;
import ru.otus.dataset.HiberUserDataSet;

public final class Login extends HttpServlet {

    private static final String AUTHORIZED = "sporijgisdfg";

    private final UserDao userService;
    private final AdminDao adminService;

    public Login() {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
            "SpringBeans.xml"
        );
        this.userService = context.getBean("userService", UserDao.class);
        this.adminService = context.getBean("adminService", AdminDao.class);
    }

    @Override
    protected void doPost(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException {
        try {
            final HiberUserDataSet user = this.userService
                .byName(req.getParameter("uname"));
            final AdminDataSet admin = this.adminService.byUser(user);
            if (!admin.password().equals(req.getParameter("psw"))) {
                throw new RuntimeException("Wrong password");
            }
            resp.addCookie(new Cookie("session_id", Login.AUTHORIZED));
            if (admin.admin()) {
                req.getSession()
                    .setAttribute("admin", true);
                resp.sendRedirect("/admin");
            }
        } catch (final Exception error) {
            error.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendRedirect("/error");
        }
    }
}
