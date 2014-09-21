package parser_analysis.interfaces;

/**
 * Created by xiaohe on 9/17/14.
 */
public interface ResultEntry {
    public String getJavaMOPParserMethodSig();
    public int getJavaMOPParserMethodLineNumber();

    public String getRVMonitorParserMethodSig();
    public int getRVMonitorParserMethodLineNumber();
    public String prettyPrint();
    public boolean equals(ResultEntry entry);
}
