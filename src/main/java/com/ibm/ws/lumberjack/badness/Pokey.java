package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Pokey servlet sleeps
 */
@WebServlet("/Pokey")
public class Pokey extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Pokey() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int delay = 5000;

        String delayString = request.getParameter("delay");
        try {
            if (delayString != null) {
                delay = Integer.valueOf(delayString);
            }
        } catch (NumberFormatException e) {
        }

        delay = (int) (Math.random() * delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter pw = response.getWriter();
        pw.print("Slept for " + delay + " ms.");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
