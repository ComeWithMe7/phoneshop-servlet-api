package com.es.phoneshop.web;

import com.es.phoneshop.command.ActionFactory;
import com.es.phoneshop.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = ActionFactory.defineCommand(request);
        String url = command.execute(request);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
