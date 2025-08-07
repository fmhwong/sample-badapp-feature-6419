package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Steady servlet sleeps briefly
 */
@WebServlet("/Steady")
public class Steady extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int delayConstant = 100;
    private static final int delayRandomness = 50;

    public Steady() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int delay = delayConstant + (int) (Math.random() * delayRandomness);
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
