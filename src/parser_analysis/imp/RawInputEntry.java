package parser_analysis.imp;

import parser_analysis.interfaces.InputEntry;

/**
 * Created by xiaohe on 9/17/14.
 */
public class RawInputEntry implements InputEntry {
    private String returnType;
    private String methodName;
    private String methodSig;
    private int lineNumber;

    public RawInputEntry(String returnType, String methodName, String methodSig, int lineNumber) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.methodSig = methodSig;
        this.lineNumber = lineNumber;
    }

    public static RawInputEntry parseStrAsInputEntry(String line){
        String[] items=line.split(" ");
        if (items.length != 4 || !items[2].equals("Line"))
            return null;

        String retT=items[0];
        String methSiganature=retT +" "+items[1];
        String methName=items[1].substring(items[1].lastIndexOf(".")+1);
        int lineNum=Integer.parseInt(items[3]);

        return new RawInputEntry(retT, methName, methSiganature, lineNum);
    }

    @Override
    public String getReturnType() {
        return this.returnType;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public String getMethodSig() {
        return this.methodSig;
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public boolean isSimilar(InputEntry in) {
        if (in==null)
            return false;

        return this.getReturnType().equals(in.getReturnType()) && this.getMethodName().equals(in.getMethodName());
    }

    public boolean equals(RawInputEntry rie){
        if (rie==null)
            return false;

        return this.methodSig.equals(rie.methodSig) && this.lineNumber==rie.lineNumber;

    }
}
