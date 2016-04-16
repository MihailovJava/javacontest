package com.atc.javacontest;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CompareRowTest extends TestCase {
	ICompareRow compareRow;
	
    public static Test suite()
    {
        return new TestSuite( CompareRowTest.class );
    }
    
    @Override
    protected void setUp() throws Exception {
    	compareRow  = new CompareRowImpl();
    	super.setUp();
    }
    
    public void test0() {
    	CorrelationResultIndex result = compareRow.executeTest0(getClass().getResource("/test0/rows.csv"));
    	assertEquals("слишком малая корреляция", true, 0.999999 < result.correlation);
                    
     }   
    
    public void test1() {
    	CorrelationResultIndex result = compareRow.executeTest1(getClass().getResource("/test1/rows.csv"));
     }   
    
    public void test2() {
    	CorrelationResultIndex result = compareRow.executeTest2(getClass().getResource("/test2/rows.csv"));
     }   
    
    public void test3() {
    	CorrelationResultIndex result = compareRow.executeTest3(getClass().getResource("/test3/rows.csv"));
    }   
 
    public void test4() {
    	CorrelationResultIndex result = compareRow.executeTest4(getClass().getResource("/test4/rows.csv"));
    }   
 
    public void test5() {
    	CorrelationResultIndex result = compareRow.executeTest5(getClass().getResource("/test5/rows.csv"));
    }   

    public void test6() {
    	CorrelationResultIndex result = compareRow.executeTest6(getClass().getResource("/test6/rows.csv"));
    }   

    public void test7() {
    	CorrelationResultIndex result = compareRow.executeTest7(getClass().getResource("/test7/rows.csv"));
    }   

    public void test8() {
    	CorrelationResultIndex result = compareRow.executeTest8(getClass().getResource("/test8/rows.csv"));
    }   

    public void test9() {
    	CorrelationResultIndex result = compareRow.executeTest9(getClass().getResource("/test9/rows.csv"));
    }   

    public void test10() {
    	List<CorrelationResultIndex> result = compareRow.executeTest10(getClass().getResource("/test10/rows.csv"));
    }   

    public void test11() {
    	List<CorrelationResultIndex> result = compareRow.executeTest11(getClass().getResource("/test11/rows.csv"));
    }   
}
