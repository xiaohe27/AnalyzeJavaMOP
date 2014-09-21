package parser_analysis.imp;

import parser_analysis.interfaces.InputEntry;
import parser_analysis.interfaces.InputTable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaohe on 9/17/14.
 */
public class RawInputTable implements InputTable {
    private LinkedList<InputEntry> inputList;

    public RawInputTable(){
        this.inputList=new LinkedList<InputEntry>();
    }

    @Override
    public boolean hasNextInputEntry() {
        return !this.inputList.isEmpty();
    }

    @Override
    public InputEntry getInputEntryAtPosition(int index) {
        if(index < 0 || index >= this.inputList.size())
            return null;

        return this.inputList.get(index);
    }


    @Override
    public void insertInputEntry(InputEntry ie) {
        this.inputList.addLast(ie);
    }

    @Override
    public void removeInputEntryAtPosition(int index) {
        if(index < 0 || index >= this.inputList.size())
            return;

        else
        this.inputList.remove(index);
    }

    @Override
    public int size() {
        return this.inputList.size();
    }
}
