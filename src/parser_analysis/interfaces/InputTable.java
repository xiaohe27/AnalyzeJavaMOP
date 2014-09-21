package parser_analysis.interfaces;

/**
 * Created by xiaohe on 9/17/14.
 */
public interface InputTable {
    public boolean hasNextInputEntry();
    public InputEntry getInputEntryAtPosition(int index);
    public void insertInputEntry(InputEntry ie);
    public void removeInputEntryAtPosition(int index);
    public int size();
}
