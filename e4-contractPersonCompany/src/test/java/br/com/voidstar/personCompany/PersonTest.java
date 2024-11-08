package br.com.voidstar.personCompany;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

public class PersonTest {
    private static Person pa, pb, pc;

    @Before
    public void setUp() throws Exception {

        pa = new Person();
        pb = new Person("Ana", "Karenina");
        pc = new Person("Janus", "Kamasarov");

    }

    @After
    public void tearDown() throws Exception {
        // No action necessary.
    }

    @Test
    public void test() {
        // Test construction.
        assertEquals(pa.getName(), "");
        assertEquals(pa.getSurname(), "");
        assertEquals(pa.getSalary(), 0.0, 0.0);

        assertEquals(pb.getName(), "Ana");
        assertEquals(pb.getSurname(), "Karenina");
        assertEquals(pb.getSalary(), 0.0, 0.0);

        assertEquals(pc.getName(), "Janus");
        assertEquals(pc.getSurname(), "Kamasarov");

        pb.setName("Anna");
        pb.setSurname("Koslov");
        assertEquals(pb.getName(), "Anna");
        assertEquals(pb.getSurname(), "Koslov");

    }

}
