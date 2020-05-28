package com.company.test;

import com.company.app.Calc;
import org.junit.*;
import java.io.ByteArrayInputStream;


public class CalcTest {


    @Test
    public void InputPossitiveValue(){
        ByteArrayInputStream in = new ByteArrayInputStream("100".getBytes());
        System.setIn(in);
        Calc.checkInputvalue();
    }
    @Test
    public void InputPossitiveValue2(){
        ByteArrayInputStream in = new ByteArrayInputStream("9999,9999".getBytes());
        System.setIn(in);
        Calc.checkInputvalue();
    }
    @Test
    public void CheckEndvalue(){
        Double value = Calc.calculationOftheAmount(2.555555,200.0);
        Assert.assertEquals("511.111",value.toString());
    }



}
