package rs.ac.ftn.uns.upp.scientificcenter.camunda.type;

import org.camunda.bpm.engine.variable.impl.type.AbstractValueTypeImpl;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.HashMap;
import java.util.Map;

public class ListValueType extends AbstractValueTypeImpl {
    public static final ListValueType INSTANCE = new ListValueType();

    public ListValueType() {
        super("list");
    }

    @Override
    public boolean isPrimitiveValueType() {
        return false;
    }

    @Override
    public Map<String, Object> getValueInfo(TypedValue typedValue) {
        if (!(typedValue instanceof ListValue)) {
            throw new IllegalArgumentException("ListValue expected");
        }

        return new HashMap<>();
    }

    @Override
    public TypedValue createValue(Object o, Map<String, Object> map) {
        throw new IllegalArgumentException("Not implemented");
    }
}
