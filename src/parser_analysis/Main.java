package parser_analysis;

/**
 * Created by xiaohe on 9/17/14.
 */
public class Main {
    public static void main(String[] args){
        String htmlFileName="compareTwoParsers.html";
        String jmopFilePath=null;
        String rvmFilePath=null;

        if (args.length==2){
            jmopFilePath=args[0];
            rvmFilePath=args[1];

            System.out.println("The two files are '"+jmopFilePath+"' and '"+rvmFilePath+"'");
        }

        else{
            System.out.println("Not enough args!");
            System.exit(0);
        }

        MethodComparison mc=new MethodComparison(jmopFilePath, rvmFilePath);

        HTMLGen htmlGen=new HTMLGen(htmlFileName, mc.getComparisonResultSet());
        htmlGen.writeToHTMLFile();

        System.out.println("Succeed!");
    }
}
