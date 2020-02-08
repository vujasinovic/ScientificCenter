package rs.ac.ftn.uns.upp.scientificcenter.camunda.type;

import org.camunda.bpm.engine.variable.type.ValueType;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.List;

public class ListValue implements TypedValue {
    private final ValueType valueType = ListValueType.INSTANCE;

    private List<?> value;

    public ListValue(List<?> value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public ValueType getType() {
        return valueType;
    }

    @Override
    public boolean isTransient() {
        return false;
    }
}
