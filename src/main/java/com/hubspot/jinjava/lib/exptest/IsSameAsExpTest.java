package com.hubspot.jinjava.lib.exptest;

import com.hubspot.jinjava.doc.annotations.JinjavaDoc;
import com.hubspot.jinjava.doc.annotations.JinjavaParam;
import com.hubspot.jinjava.interpret.InterpretException;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;


@JinjavaDoc(value="Return true if variable is pointing at same object as other variable",
    params=@JinjavaParam(value="other", type="object") )
public class IsSameAsExpTest implements ExpTest {

  @Override
  public String getName() {
    return "sameas";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter,
      Object... args) {
    if(args.length == 0) {
      throw new InterpretException(getName() + " test requires 1 argument");
    }

    return var == args[0];
  }

}
