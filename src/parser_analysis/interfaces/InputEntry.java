package parser_analysis.interfaces;

/**
 * Created by xiaohe on 9/17/14.
 */
public interface InputEntry {
    public String getReturnType();
    public String getMethodName();
    public String getMethodSig();
    public int getLineNumber();

    public boolean isSimilar(InputEntry in);
}
