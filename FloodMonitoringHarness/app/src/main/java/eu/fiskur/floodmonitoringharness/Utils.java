package eu.fiskur.floodmonitoringharness;

import android.widget.EditText;

public class Utils {

    public static final String DIV = "-----------------------------------------\n";

    public static void clipOutput(EditText editText, int maxLines){

        int excessLineNumber = editText.getLineCount() - maxLines;
        if (excessLineNumber > 0) {
            int eolIndex = -1;
            CharSequence charSequence = editText.getText();
            for(int i=0; i<excessLineNumber; i++){
                do{
                    eolIndex++;
                }while(eolIndex < charSequence.length() && charSequence.charAt(eolIndex) != '\n');
            }
            if(eolIndex < charSequence.length()){
                editText.getEditableText().delete(0, eolIndex+1);
            }else{
                editText.setText("");
            }
        }
    }
}
