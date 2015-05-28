package com.hubspot.jinjava.lib.filter;

import java.util.ArrayList;
import java.util.List;

import com.hubspot.jinjava.doc.annotations.JinjavaDoc;
import com.hubspot.jinjava.doc.annotations.JinjavaParam;
import com.hubspot.jinjava.doc.annotations.JinjavaSnippet;
import com.hubspot.jinjava.interpret.InterpretException;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.exptest.ExpTest;
import com.hubspot.jinjava.util.ForLoop;
import com.hubspot.jinjava.util.ObjectIterator;


@JinjavaDoc(
    value="Filters a sequence of objects by applying a test to the object and only selecting the ones with the test succeeding.",
    params={
        @JinjavaParam(value="value", type="sequence"),
        @JinjavaParam(value="exp_test", type="name of expression test")
    },
    snippets={
        @JinjavaSnippet(code="{{ numbers|select(\"odd\") }}"),
        @JinjavaSnippet(code="{{ numbers|select(\"odd\") }}")
    })
public class SelectFilter implements Filter {

  @Override
  public String getName() {
    return "select";
  }

  @Override
  public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
    List<Object> result = new ArrayList<>();

    if(args.length == 0) {
      throw new InterpretException(getName() + " requires an exp test to filter on", interpreter.getLineNumber());
    }

    ExpTest expTest = interpreter.getContext().getExpTest(args[0]);
    if(expTest == null) {
      throw new InterpretException("No exp test defined for name '" + args[0] + "'", interpreter.getLineNumber());
    }

    ForLoop loop = ObjectIterator.getLoop(var);
    while(loop.hasNext()) {
      Object val = loop.next();

      if(expTest.evaluate(val, interpreter)) {
        result.add(val);
      }
    }

    return result;
  }

}
