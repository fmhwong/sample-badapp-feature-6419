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
@WebServlet("/Trouble")
public class Trouble extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Trouble() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String output = null;

        Logger logger = Logger.getLogger("com.ibm.ws.lumberjack.badness.Trouble");

        String exceptionNum = request.getParameter("exception");
        if (exceptionNum != null) {
            if ("1".equals(exceptionNum)) {
                output = "NullPointerException";
                String myString = null;
                System.out.println(myString.toString());
            } else if ("2".equals(exceptionNum)) {
                output = "ArithmeticException";
                int i = 10 / 0;
                System.out.println(i);
            } else if ("3".equals(exceptionNum)) {
                output = "ArrayIndexOutOfBoundException";
                int[] a = { 0, 1, 2, 3 };
                System.out.println(a[6]);
            }
        } else {
            output = "Use parameter '?exception=1' to generate a NullPointerException\n"
                   + "              '?exception=2' to generate an ArthmeticException\n"
                   + "              '?exception=3' to generate an ArrayIndexOutOfBoundException";
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
