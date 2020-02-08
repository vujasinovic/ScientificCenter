package rs.ac.ftn.uns.upp.scientificcenter.camunda.type;

import org.camunda.bpm.engine.impl.form.type.SimpleFormFieldType;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.List;

import static java.util.Objects.isNull;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.convertArray;

public class ListFormFieldType extends SimpleFormFieldType {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    protected TypedValue convertValue(TypedValue propertyValue) {
        TypedValue response;

        Object value = propertyValue.getValue();

        if (isNull(value)) {
            response = new ListValue(null);
        } else if (value instanceof List) {
            response = new ListValue((List<?>) value);
        } else {
            try {
                List<String> list = convertArray(value.toString());
                response = new ListValue(list);
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot convert value to list: " + value);
            }
        }
        return response;
    }

    @Override
    public Object convertFormValueToModelValue(Object propertyValue) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        throw new IllegalArgumentException("Not implemented");
    }
}
