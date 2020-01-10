import Input from "reactstrap/lib/Input";
import {default as React} from "react";
import FormGroup from "reactstrap/lib/FormGroup";
import Label from "reactstrap/lib/Label";
import Form from "reactstrap/lib/Form";
import {inputType} from "./const/inputType";
import Button from "reactstrap/lib/Button";

export function generate(formFields) {
    let retVal;

    retVal = <Form>
        {formFields.map(formField =>
            <FormGroup key={formField.id}>
                <Label for={formField.id}>{formField.label}</Label>
                {generateInputField(formField)}
            </FormGroup>
        )}
        <Button color="primary">Submit</Button>
    </Form>;

    return retVal;
}

function generateInputField(formField) {
    let retVal;

    const typeName = formField.type.name;
    const id = formField.id;

    if (typeName === inputType.STRING) {
        retVal = <Input type="text" name={id} id={id}/>;
    } else if (typeName === inputType.DATE) {
        retVal = <Input type="date" name={id} id={id}/>;
    } else if (typeName === inputType.LONG) {
        retVal = <Input type="number" name={id} id={id}/>;
    } else if (typeName === inputType.ENUM) {
        let options = Object.values(formField.type.values);
        retVal = <Input type="select" name={id} id={id}>
            {options.map(option => <option key={option}>{option}</option>)}
        </Input>;
    } else {
        retVal = <Input type="text" name={id} id={id}/>;
    }

    return retVal;
}
