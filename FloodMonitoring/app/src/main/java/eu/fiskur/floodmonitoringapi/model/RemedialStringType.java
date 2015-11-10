package eu.fiskur.floodmonitoringapi.model;

public class RemedialStringType {
    String label = null;
    String[] labelArray = null;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getLabelArray() {
        return labelArray;
    }

    public void setLabelArray(String[] labelArray) {
        this.labelArray = labelArray;
    }

    @Override
    public String toString() {
        if(label != null){
            return label;
        }else if(labelArray != null){
            String arrStr = "";
            for(String str : labelArray){
                arrStr+= str + ", ";
            }
            return arrStr;
        }else{
            return "No label";
        }
    }
}
