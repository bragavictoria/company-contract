package br.com.voidstar.personCompany;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
public class CompanyTest {
    private static Company ca, cb, cc;

    @Before
    public void setUp() throws Exception {

        ca = new Company();
        cb = new Company("CB");
        cc = new Company("CC");

    }

    @After
    public void tearDown() throws Exception {
        // No action necessary here.
    }

    @Test
    public void test() {
        // Test the state of ca after its construction.
        assertEquals(ca.getName(), "");
        assertEquals(ca.getNumberOfEmployees(), 0);
        assertEquals(ca.payroll(), 0.0, 0.0);

        // Test the state of cb after its construction.
        assertEquals(cb.getName(), "CB");
        assertEquals(cb.getNumberOfEmployees(), 0);
        assertEquals(cb.payroll(), 0.0, 0.0);

        // Test construction of cc
        assertEquals(cc.getName(), "CC");
        assertEquals(cc.getNumberOfEmployees(), 0);

        cc.setName("Sucupira");
        assertEquals(cc.getName(), "Sucupira");
        assertEquals(cc.payroll(), 0.0, 0.0);

    }
}
