package parser_analysis.imp;

import parser_analysis.interfaces.ResultEntry;
import java.util.LinkedList;

/**
 * Created by xiaohe on 9/17/14.
 */
public class ComparisonResult implements parser_analysis.interfaces.ComparisonResult {
    private LinkedList<ResultEntry> entries;

    public ComparisonResult() {
        this.entries = new LinkedList<ResultEntry>();
    }

    @Override
    public boolean hasNextResultEntry() {
        return !this.entries.isEmpty();
    }

    @Override
    public ResultEntry getResultEntryAtPosition(int index) {
        if (index < 0 || index >= this.entries.size())
            return null;

        return this.entries.get(index);
    }


    @Override
    public void insertResultEntry(ResultEntry entry) {
        this.entries.addLast(entry);
    }

    @Override
    public int size() {
        return this.entries.size();
    }

    @Override
    public String prettyPrint() {
        StringBuilder out=new StringBuilder();
        for (int i=0; i<this.entries.size(); i++){
            out.append("No."+i+" pair of similar methods:\n");
            out.append(this.entries.get(i).prettyPrint());
            out.append("\n");
        }

        return out.toString();
    }

    @Override
    public ResultEntry removeEntryAtPosition(int index) {
        if (index < 0 || index >= this.entries.size())
            return null;

        return this.entries.remove(index);
    }

    @Override
    public boolean contains(ResultEntry entry) {
        if (entry==null)
            return false;

        for (int i=0; i<this.entries.size(); i++){
            if (this.entries.get(i).equals(entry))
                return true;
        }

        return false;
    }

    @Override
    public void removeDup() {
        ComparisonResult cr=new ComparisonResult();

        for (int i=0; i<this.entries.size(); i++){
            ResultEntry re=this.entries.get(i);
            if (cr.contains(re)){
                this.entries.remove(i);
                i--;
            } else{
                cr.insertResultEntry(re);
            }
        }
    }


}
