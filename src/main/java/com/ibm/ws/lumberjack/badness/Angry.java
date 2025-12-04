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
    private static final String[] msgs = { "BADAP0001W: quit bugging me.", "BADAP0002W: go away. 20251204",
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

        // Get the count parameter, default to 1 if not provided
        String countParam = request.getParameter("count");
        int count = 1;
        if (countParam != null && !countParam.isEmpty()) {
            try {
                count = Integer.parseInt(countParam);
                // Ensure count is at least 1
                if (count < 1) {
                    count = 1;
                }
            } catch (NumberFormatException e) {
                count = 1;
            }
        }

        int index = nextMsg++;
        String msg = msgs[index % numMsgs];

        // Log the message 'count' times
        for (int i = 0; i < count; i++) {
            if (msg.charAt(9) == 'W')
                logger.warning(msg);
            else
                logger.severe(msg);
        }

        PrintWriter pw = response.getWriter();
        pw.print("Printed message to logs " + count + " time(s): " + msg);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
