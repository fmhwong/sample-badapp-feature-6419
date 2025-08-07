package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Leaky servlet leaks memory
 */
@WebServlet("/Leaky")
public class Leaky extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Vector<byte[]> v = new Vector<byte[]>();
    private static long totalBytes = 0;

    public Leaky() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numBytes = 1000;

        String numBytesString = request.getParameter("numBytes");
        try {
            if (numBytesString != null) {
                numBytes = Integer.valueOf(numBytesString);
            }
        } catch (NumberFormatException e) {
        }

        byte[] bytes = new byte[numBytes];
        v.add(bytes);
        totalBytes += numBytes;

        PrintWriter pw = response.getWriter();
        pw.print("Stashed " + numBytes + " bytes. " + totalBytes + " bytes stashed since startup");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
