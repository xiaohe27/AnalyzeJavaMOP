package parser_analysis.imp;

import parser_analysis.interfaces.ResultEntry;

/**
 * Created by xiaohe on 9/17/14.
 */
public class ComparisonResultEntry implements ResultEntry {
    private String javaMOPParserMethodSig;
    private int javaMOPParserMethodLineNum;
    private String rvmonitorParserMethodSig;
    private int rvmonitorParserMethodLineNum;

    public ComparisonResultEntry(String javaMOPParserMethodSig, int javaMOPParserMethodLineNum,
                                 String rvmonitorParserMethodSig, int rvmonitorParserMethodLineNum) {
        this.javaMOPParserMethodSig = javaMOPParserMethodSig;
        this.javaMOPParserMethodLineNum = javaMOPParserMethodLineNum;
        this.rvmonitorParserMethodSig = rvmonitorParserMethodSig;
        this.rvmonitorParserMethodLineNum = rvmonitorParserMethodLineNum;
    }

    @Override
    public String getJavaMOPParserMethodSig() {
        return this.javaMOPParserMethodSig;
    }

    @Override
    public int getJavaMOPParserMethodLineNumber() {
        return this.javaMOPParserMethodLineNum;
    }

    @Override
    public String getRVMonitorParserMethodSig() {
        return this.rvmonitorParserMethodSig;
    }

    @Override
    public int getRVMonitorParserMethodLineNumber() {
        return this.rvmonitorParserMethodLineNum;
    }

    @Override
    public String prettyPrint() {
        String out=this.getJavaMOPParserMethodSig()+" at line "+this.getJavaMOPParserMethodLineNumber()+"\n";
        out+=this.getRVMonitorParserMethodSig()+" at line "+this.getRVMonitorParserMethodLineNumber()+"\n";

        return out;
    }

    @Override
    public boolean equals(ResultEntry entry) {
        if (entry==null)
            return false;

        return this.javaMOPParserMethodSig.equals(entry.getJavaMOPParserMethodSig());
    }
}
