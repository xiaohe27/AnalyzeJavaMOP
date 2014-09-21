package parser_analysis;

import parser_analysis.imp.ComparisonResult;
import parser_analysis.imp.ComparisonResultEntry;
import parser_analysis.interfaces.ResultEntry;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by xiaohe on 9/17/14.
 * This class is just used to make the output of comparison result more user-friendly.
 */
public class HTMLGen
{
    private String htmlFileName;
    private ComparisonResult cr;

    public HTMLGen(String fName, ComparisonResult cr) {
        this.htmlFileName=fName;
        this.cr=cr;
    }

    /**
     * Gen the str representation of the html file.
     * @return
     */
    private String genHTML(){
        StringBuilder sb=new StringBuilder();
        sb.append("<html>\n");
        sb.append(this.genHead());
        sb.append(this.genBody());
        sb.append("</html>\n");

        return sb.toString();
    }

    /**
     * Gen the body of html.
     * @return
     */
    private String genBody() {
        StringBuilder body=new StringBuilder();
        body.append("<body>\n");
        body.append("The table below shows the methods invoked from javamop and their counterparts in the rv-monitor\n");
        //read the input entries produced by MethodComparison
        body.append(this.genTable());

        body.append("</body>\n");
        return body.toString();
    }

    /**
     * Gen the head of html.
     * @return
     */
    private String genHead(){
        String head="<head>\n";
//        head+="\tVersion=0.1\n";
        head+="</head>\n";

        return head;
    }


    /**
     * Gen the table of results from the ComparisonResult class.
     * @return
     */
    private String genTable(){
        StringBuilder table=new StringBuilder();
        table.append("<table border=\"1\" style=\"width:100%\">\n");

        table.append("<tr> <td> Method Calls in JAVAMOP's parser </td> <td> Line Number </td> " +
                "<td> Counterparts in RV-Monitor </td> <td> Line Number </td> </tr>");

        for (int i=0; i<this.cr.size(); i++){
            ResultEntry cre=this.cr.getResultEntryAtPosition(i);
            table.append("<tr> <td>"+cre.getJavaMOPParserMethodSig()+"</td>\n"+
                        "<td>"+cre.getJavaMOPParserMethodLineNumber()+"</td>\n"+
                        "<td>"+cre.getRVMonitorParserMethodSig()+"</td>\n"+
                        "<td>"+cre.getRVMonitorParserMethodLineNumber()+"</td> </tr>\n\n");

        }


        table.append("</table>\n");
        return table.toString();
    }


    public void writeToHTMLFile(){
        Path p= Paths.get(this.htmlFileName);
        try {
            Files.write(p, this.genHTML().getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
