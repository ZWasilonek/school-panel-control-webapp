package pl.codeschool.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlankValidation {

    private static final String EMPTY_FIELD = "this field cannot be empty";

    public static boolean hasBlankErrorsAttributes(Map<String,String> fieldNames, HttpServletRequest request) {
        Set<String> blankFieldNames = getBlankFieldNames(fieldNames);
        if (blankFieldNames.size() != 0) {
            blankFieldNames.forEach(fieldName -> request.setAttribute(fieldName, EMPTY_FIELD));
            return true;
        }
        return false;
    }

    private static Set<String> getBlankFieldNames(Map<String,String> fieldNames) {
        Set<String> blankFields = new HashSet<>();
        for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
            if (entry.getValue().equals("")) {
                String originFieldName = entry.getKey();
                String blankFieldName = "blank" + originFieldName.substring(0, 1).toUpperCase() + originFieldName.substring(1);
                blankFields.add(blankFieldName);
            }
        }
        return blankFields;
    }

}