package pl.codeschool.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CapacityValidation {

    private static final String CAPACITY_EXCEEDED = "allowed number of characters exceeded: ";

    public static boolean hasCapacityErrorAttributes(Map<String, Map<Integer,String>> fieldNames, HttpServletRequest request) {
        Map<String,String> capacityExceededFieldNames = getCapacityExceededFieldNames(fieldNames);
        if (capacityExceededFieldNames.size() != 0) {
            for (Map.Entry<String,String> entry : capacityExceededFieldNames.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return true;
        }
        return false;
    }

    private static Map<String,String> getCapacityExceededFieldNames(Map<String, Map<Integer, String>> fieldNamesWithCapacities) {
        Map<String,String> capacityExceededFields = new HashMap<>();

        for (Map.Entry<String, Map<Integer, String>> entry : fieldNamesWithCapacities.entrySet()) {

            String fieldName = entry.getKey();
            Map<Integer, String> fields = entry.getValue();

            for (Map.Entry<Integer, String> field : fields.entrySet()) {
                int maxCapacity = field.getKey();
                int providedCapacity = field.getValue().length();

                if (providedCapacity > maxCapacity) {
                    StringBuilder builderFieldName = new StringBuilder();
                    String capacityExceededFieldName =
                            builderFieldName.append("capacityExceeded")
                                    .append(fieldName.substring(0, 1).toUpperCase())
                                    .append(fieldName.substring(1))
                                    .toString();

                    StringBuilder builderError = new StringBuilder();
                    String errorInfo =
                            builderError.append(CAPACITY_EXCEEDED)
                                    .append(maxCapacity)
                                    .append(", provided: ")
                                    .append(providedCapacity)
                                    .toString();
                    capacityExceededFields.put(capacityExceededFieldName, errorInfo);
                }
            }
        }
        return capacityExceededFields;
    }

}