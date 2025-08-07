package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AngryJson servlet to write json messages to SystemOut/SystemErr
 */
@WebServlet("/AngryJson")
public class AngryJson extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] msgs = { "{\"ibm_messageId\":\"BADAP0001W\",\"message\":\"quit bugging me.\",\"loglevel\":\"WARNING\"}",
                                           "{\"ibm_messageId\":\"BADAP0002W\",\"message\":\"go away.\", \"loglevel\":\"WARNING\"}",
                                           "{\"ibm_messageId\":\"BADAP0003W\",\"message\":\"why don't you go find an app that likes you?\",\"loglevel\":\"WARNING\"}",
                                           "{\"ibm_messageId\":\"BADAP0010E\",\"message\":\"you should have gone to the other presentation.\",\"loglevel\":\"SEVERE\"}",
                                           "{\"ibm_messageId\":\"BADAP0011E\",\"message\":\"if you leave now you can still get cookies in the hallway.\",\"loglevel\":\"SEVERE\"}" };

    private static final int numMsgs = msgs.length;
    private static volatile int nextMsg = 0;

    public AngryJson() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int index = nextMsg++;
        String msg = msgs[index % numMsgs];

        String printStream = null;

        // print W to SystemOut, print E to SystemErr

        if (msg.charAt(27) == 'W') {
            System.out.println(msg);
            printStream = "SystemOut";
        } else {
            System.err.println(msg);
            printStream = "SystemErr";
        }

        PrintWriter pw = response.getWriter();
        pw.print("Printed message to " + printStream + ": " + msg);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}