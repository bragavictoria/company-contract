package br.com.voidstar.personCompany;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class CompanyPersonTest {
    private static Company ca, cb, cc, cd, ce;

    private static Person pa, pb,pc, pd;

    @Before
    public void setUp() throws Exception {

        ca = new Company();
        cb = new Company("CB");
        cc = new Company("CC");
        cd = new Company("CD");
        ce = new Company("CE");

        pa = new Person("A", "AA");
        pb = new Person("B", "BB");
        pc = new Person("C", "CC");
        pd = new Person("C", "CD");

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
        assertEquals(cc.payroll(), 0.0, 0.0);

        cc.setName("Sucupira");
        assertEquals(cc.getName(), "Sucupira");
        assertEquals(cc.getNumberOfEmployees(), 0);
        assertEquals(cc.payroll(), 0.0, 0.0);

        // CC hires PA, PB, and PC
        cc.hire(pa, 1000.0);
        assertEquals(cc.getNumberOfEmployees(), 1);
        assertEquals(cc.payroll(), 1000.0, 0.0);
        cc.hire(pb, 1100.0);
        assertEquals(cc.payroll(), 2100.00, 0.0);
        cc.hire(pc, 1200.0);
        assertEquals(cc.payroll(), 3300.0, 0.0);
        assertEquals(cc.getNumberOfEmployees(), 3);
        // Test the relationship from the perspective of the employees
        assertEquals(pa.getCompany(), cc);
        assertEquals(pb.getCompany(), cc);
        assertEquals(pc.getCompany(), cc);


        // CA hires pa
        ca.hire(pa, 1400.0);
        assertEquals(ca.payroll(), 1400.0, 0.0);
        assertEquals(ca.getNumberOfEmployees(), 1);
        // What happens to CA's salary
        assertEquals(pa.getSalary(), 1400.00, 0.0);
        assertEquals(pa.getCompany(), ca);
        // Check PA is no longer employed by CC
        assertFalse(cc.employed(pa));
        // PA is employed by CA
        assertTrue(ca.employed(pa));

        // a person can still hire and dismiss itself from a company, if not employed already
        // if already employed selfHire is a no-op.
        pa.selfHire(ca, 2000.00);
        assertTrue(ca.employed(pa));
        assertEquals(pa.getSalary(), 1400.00, 0.0);
        // If hired, self dismissal works as expected
        pa.selfDismiss(ca);
        assertFalse(ca.employed(pa));
        // After its voluntary dismissal, PA is hired again with a better salary
        ca.hire(pa, 3000.00);
        assertEquals(ca.payroll(), 3000.00, 0.0);

        ca.hire(pb, 2000.00);
        assertEquals(ca.payroll(), 5000.00, 0.0);

        // a person cannot be hired twice in the same Company
        ca.hire(pb, 2500.00);
        assertEquals(ca.payroll(), 5000.00, 0.0);
        ca.hire(pc, 5000.00);
        assertEquals(ca.payroll(), 10000.00, 0.0);
        assertEquals(pa.getCompany(), ca);
        assertEquals(pb.getCompany(), ca);
        assertEquals(pc.getCompany(), ca);

        // at this point in the test, CC lost all employees
        assertEquals(cc.getNumberOfEmployees(), 0);

        // a company can dismiss a person
        ca.dismiss(pa);
        // a person can dismiss itself, if employed.
        pa.selfDismiss(ca);

        // a person can dismiss itself, if employed
        pb.selfDismiss(ca);
        assertNull(pb.getCompany());
        // a company can dismiss a person, if the person is employed at the company
        // otherwise dismissal is a no-op.
        ca.dismiss(pb);
        // Dismissal of a non-employee is a no-op.
        ca.dismiss(pb);
        assertFalse(ca.employed(pb));
        ca.hire(pb, 1000.00);
        assertEquals(pb.getSalary(), 1000.00, 0.0);
        assertEquals(ca.payroll(), 6000.00, 0.0);

        // If a person tries to be hired by more than one company,
        // only the most recent contract remains valid
        cd.hire(pd, 550.00);
        ce.hire(pd, 450.00);
        assertEquals(pd.getSalary(), 450.0, 0.0);
        assertFalse(cd.employed(pd));
        // The same is true for the methods seflHire, selfDismiss
        ce.dismiss(pd);
        pd.selfHire(cd, 600.00);
        pd.selfHire(ce, 400.00);
        assertEquals(pd.getSalary(), 400.00, 0.0);
        pd.selfDismiss(ce);
        assertFalse(ce.employed(pd));

        // CB hires PA
        cb.hire(pa, 10000.0);
        assertEquals(pa.getCompany(), cb);
        // PA dismisses herself from CB
        pa.selfDismiss(cb);
        assertEquals(pa.getCompany(), null);
        assertFalse(cb.employed(pa));
        // PA hires herself at CE
        pa.selfHire(ce, 11000.0);
        assertTrue(ce.employed(pa));
        // CE dismisses PA
        ce.dismiss(pa);
        assertFalse(ce.employed(pa));
        // PA returns to CB
        pa.selfHire(cb, 10001.00);
        assertTrue(cb.employed(pa));
        assertEquals(pa.getCompany(), cb);

    }
}
