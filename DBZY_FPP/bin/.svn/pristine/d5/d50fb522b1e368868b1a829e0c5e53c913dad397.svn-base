package com.nepharm.apps.fpp.util;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
 
 /**
  * 数学公式简单计算
  * @author lizhen
  *
  */
public class MathUtil {
    public static void main(String[] args) throws Exception {
       System.out.println(runExpression("-9/(3+1)"));
       System.out.println(runExpression("-9"));
    }
    
    /**
     * 输入公式，返回值
     * @param expression   "(1-5)/3*(1-5)"
     * @return
     * @throws ScriptException
     */
    public static double runExpression(String expression) throws ScriptException{
    	 ScriptEngineManager manager=new ScriptEngineManager();
         ScriptEngine engine=manager.getEngineByExtension("js");
         Bindings bindings=engine.createBindings();
         //String expression="(1-5)/3*(1-5)";
         bindings.put("expression", expression);
         Double value=(Double) engine.eval("eval(expression)", bindings);
         //System.out.println(value);
         return value;
    }
    
}