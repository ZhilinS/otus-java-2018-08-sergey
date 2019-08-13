/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.otus.dao.AdminDao;
import ru.otus.dao.UserDao;
import ru.otus.dataset.AdminDataSet;
import ru.otus.dataset.HiberUserDataSet;

public final class Login extends HttpServlet {

    private static final String AUTHORIZED = "sporijgisdfg";

    private final UserDao userService;
    private final AdminDao adminService;

    public Login() {
        this(new UserDao(), new AdminDao());
    }

    public Login(final UserDao userService, final AdminDao adminService) {
        this.userService = userService;
        this.adminService = adminService;
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
