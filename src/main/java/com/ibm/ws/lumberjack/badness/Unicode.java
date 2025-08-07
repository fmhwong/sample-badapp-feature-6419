package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Chatty servlet writes some trace
 */
@WebServlet("/Unicode")
public class Unicode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Unicode() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int unicodeInt = 65;  // A
        String output = null;

        Logger logger = Logger.getLogger("com.ibm.ws.lumberjack.badness.Unicode");

        String unicodeString = request.getParameter("unicode");
        try {
            if (unicodeString != null) {
                if (unicodeString.equals("all")) {
                    StringBuilder sb = new StringBuilder();
                    for (int i=0; i <=126; i++) {
                        sb.append((char) i);
                    }
                    output = sb.toString() + sb.toString();
                } else {
                    unicodeInt = Integer.valueOf(unicodeString);
                    char character = (char) unicodeInt;
                    output = "Unicode " + unicodeString + ": '" + character + "'";
                }
            } else {
                output = "You can specify an unicode character by adding request parameter `?unicode=65` (in decimal) to the URL\n"
                        + "Or `?unicode=all` for all unicode 0-126 characters";
            }
        } catch (NumberFormatException e) {
        }
        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        logger.info(output);
        pw.print(output);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
