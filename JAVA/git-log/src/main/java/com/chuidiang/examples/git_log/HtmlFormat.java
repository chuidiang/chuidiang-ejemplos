package com.chuidiang.examples.git_log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JAVIER on 25/02/2017.
 */
public class HtmlFormat {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MMM-YYYY HH:mm:ss");
    private PrintWriter printWriter;

    public void open(String file) throws FileNotFoundException {
        printWriter = new PrintWriter(new FileOutputStream(file));
        printWriter.println("<html>");
        printWriter.println("<head><title>Registro de Cambios</title></head>");
        printWriter.println("<body>");
        printWriter.println("<h1>Registro de Cambios</h1>");
    }

    public void addIssue (String issue){
        printWriter.println("<h2>"+issue+"</h2>");
        printWriter.println("<table border=\"1\">");
        printWriter.println("<tr><th>Revision</th><th>Fecha</th><th>Autor</th><th>Mensaje</th></tr>");
    }

    public void addChange(String revision, Date date, String user, String message, String [] files) {
        printWriter.println(MessageFormat.format(
                "<tr><td rowspan=\"2\">{0}</td><td rowspan=\"2\">{1}</td><td rowspan=\"2\">{2}</td><td>{3}</td></tr>",
                revision, DATE_FORMAT.format(date), user, message.replaceAll("\n","<br/>")+"<br/>"));
        String filesText = "";
        for (String file : files){
            filesText = filesText + file + "<br/>";
        }
        printWriter.println("<tr><td>"+filesText+"</td></tr>");
    }

    public void endIssue(){
        printWriter.println("</table>");
    }

    public void close(){
        printWriter.println("</body>");
        printWriter.println("</html>");
        printWriter.close();
    }
}
