package parser_analysis;

import parser_analysis.imp.ComparisonResult;
import parser_analysis.imp.ComparisonResultEntry;
import parser_analysis.imp.RawInputEntry;
import parser_analysis.imp.RawInputTable;
import parser_analysis.interfaces.InputEntry;
import parser_analysis.interfaces.ResultEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by xiaohe on 9/17/14.
 */
public class MethodComparison {
    public static final String COMPARISON_RESULT="javamop_vs_rv-monitor.txt";

    private RawInputTable inputTable4JavaMOP;
    private RawInputTable inputTable4RVMonitor;
    private ComparisonResult comparisonResultSet;

    public MethodComparison(String javamopMethodsFilePath, String rvmonitorMethodsFilePath) {
        this.inputTable4JavaMOP=genInputTable4(javamopMethodsFilePath);
        this.inputTable4RVMonitor=genInputTable4(rvmonitorMethodsFilePath);
        this.comparisonResultSet=new ComparisonResult();

        this.genComparisonResultSets();
    }


    public ComparisonResult getComparisonResultSet(){
        return this.comparisonResultSet;
    }

    /**
     * to be impl.
     */
    private void genComparisonResultSets() {
        out:for (int i=0; i<this.inputTable4JavaMOP.size(); i++){

            if(i % 101 == 1)
            System.out.println("Reach the num "+i+" iteration in outer loop!");

            InputEntry inEntry4JMOP=this.inputTable4JavaMOP.getInputEntryAtPosition(i);
            if (inEntry4JMOP==null)
                continue;

           for (int j=0; j<this.inputTable4RVMonitor.size(); j++){

                InputEntry inEntry4RVM=this.inputTable4RVMonitor.getInputEntryAtPosition(j);
                if(inEntry4RVM==null)
                    continue;

                if (inEntry4JMOP.isSimilar(inEntry4RVM)){
                    ResultEntry resultEntry=new ComparisonResultEntry(inEntry4JMOP.getMethodSig(),
                            inEntry4JMOP.getLineNumber(), inEntry4RVM.getMethodSig(), inEntry4RVM.getLineNumber());

                    this.comparisonResultSet.insertResultEntry(resultEntry);
                    this.inputTable4JavaMOP.removeInputEntryAtPosition(i);
                    this.inputTable4RVMonitor.removeInputEntryAtPosition(j);
                    i--;
                    continue out;
                }
            }
        }

        System.out.println("Remove the duplicate parts");

        this.comparisonResultSet.removeDup();

        Utils.writeToFile(this.comparisonResultSet.prettyPrint(), "comparisonOutputInTXT.txt");
    }

//    private ComparisonResult removeDup(ComparisonResult targetSet, ComparisonResult resultSetWithoutDuplicates) {
//        //the tail recursion method has not been transformed to a while loop by the compiler...
////        if (targetSet.size()==0)
////            return resultSetWithoutDuplicates;
////
////        ResultEntry curEntry=targetSet.removeEntryAtPosition(0);
////        if (resultSetWithoutDuplicates.contains(curEntry))
////            return this.removeDup(targetSet, resultSetWithoutDuplicates);
////
////        else
////        {
////            resultSetWithoutDuplicates.insertResultEntry(curEntry);
////            return this.removeDup(targetSet, resultSetWithoutDuplicates);
////        }
//
//    }

    /**
     * Generate the RawInputTable structure for the input file.
     * @param inputFilePath The input file path which contains the methods info.
     */
    private RawInputTable genInputTable4(String inputFilePath) {
        RawInputTable inputTable=new RawInputTable();

        File inputFile=new File(inputFilePath);
        try {
            Scanner scan=new Scanner(inputFile);

            while (scan.hasNextLine()){
                String curLine=scan.nextLine();
                RawInputEntry rawIn=RawInputEntry.parseStrAsInputEntry(curLine);
                if (rawIn != null){
                    inputTable.insertInputEntry(rawIn);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Input table 4 "+inputFilePath+" has been created");
        return inputTable;
    }

}
