package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Impatient servlet asks for a GC
 */
@WebServlet("/Impatient")
public class Impatient extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Impatient() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.gc();

        PrintWriter pw = response.getWriter();
        pw.print("Requested a GC");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
