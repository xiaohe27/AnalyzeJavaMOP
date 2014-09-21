package parser_analysis.interfaces;

/**
 * Created by xiaohe on 9/17/14.
 */
public interface ComparisonResult {
    public boolean hasNextResultEntry();
    public ResultEntry getResultEntryAtPosition(int index);
    public void insertResultEntry(ResultEntry entry);
    public int size();
    public String prettyPrint();
    public ResultEntry removeEntryAtPosition(int index);
    public boolean contains(ResultEntry entry);

    public void removeDup();
}
