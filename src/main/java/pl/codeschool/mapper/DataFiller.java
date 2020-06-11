package pl.codeschool.mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DataFiller {

    public static void modelAttributesFiller(Map<String,String> fieldNames, HttpServletRequest request) {
        for (Map.Entry<String,String> entry : fieldNames.entrySet()) {
            String providedValue = entry.getValue();
            String fieldName = entry.getKey();
            if (providedValue != null && fieldName != null && !"".equals(providedValue) && !"".equals(fieldName)) {
                request.setAttribute(fieldName + "Val", providedValue);
            }
        }
    }

}