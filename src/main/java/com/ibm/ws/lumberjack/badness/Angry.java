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
 * Angry servlet creates warnings and errors
 */
@WebServlet("/Angry")
public class Angry extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] msgs = { "BADAP0001W: quit bugging me.", "BADAP0002W: go away.",
            "BADAP0003W: why don't you go find an app that likes you?",
            "BADAP0010E: you should have gone to the other presentation.",
            "BADAP0011E: if you leave now you can still get cookies in the hallway." };

    private static final int numMsgs = msgs.length;
    private static volatile int nextMsg = 0;

    public Angry() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Logger logger = Logger.getLogger("com.ibm.ws.lumberjack.badness.Angry");

        int index = nextMsg++;
        String msg = msgs[index % numMsgs];

        if (msg.charAt(9) == 'W')
            logger.warning(msg);
        else
            logger.severe(msg);

        PrintWriter pw = response.getWriter();
        pw.print("Printed message to logs: " + msg);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
