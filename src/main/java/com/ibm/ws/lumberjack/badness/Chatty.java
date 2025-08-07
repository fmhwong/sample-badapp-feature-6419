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
@WebServlet("/Chatty")
public class Chatty extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Chatty() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numTraces = 5;

        Logger logger = Logger.getLogger("com.ibm.ws.lumberjack.badness.Chatty");

        String numTracesString = request.getParameter("numTraces");
        try {
            if (numTracesString != null) {
                numTraces = Integer.valueOf(numTracesString);
            }
        } catch (NumberFormatException e) {
        }

        for (int i = 0; i < numTraces; i++) {
            logger.fine("hello.  I'm chatty");
        }

        PrintWriter pw = response.getWriter();
        pw.print("Wrote " + numTraces + " trace entries. ");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
