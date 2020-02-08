package rs.ac.ftn.uns.upp.scientificcenter.camunda.type;

import org.camunda.bpm.engine.impl.variable.serializer.AbstractTypedValueSerializer;
import org.camunda.bpm.engine.impl.variable.serializer.ValueFields;
import org.camunda.bpm.engine.variable.impl.value.UntypedValueImpl;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.convertArray;

public class ListValueSerializer extends AbstractTypedValueSerializer<ListValue> {

    public ListValueSerializer() {
        super(ListValueType.INSTANCE);
    }

    @Override
    protected boolean canWriteValue(TypedValue value) {
        return value instanceof ListValue;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void writeValue(ListValue value, ValueFields valueFields) {
        List<String> list = (List<String>) value.getValue();
        valueFields.setTextValue(convertArray(list));
    }

    @Override
    public ListValue readValue(ValueFields valueFields, boolean deserializeValue) {
        List<String> list = convertArray(valueFields.getTextValue());
        return new ListValue(list);
    }

    @Override
    public ListValue convertToTypedValue(UntypedValueImpl untypedValue) {
        throw new IllegalArgumentException("Not implemented");
    }
}
