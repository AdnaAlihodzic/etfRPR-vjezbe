import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest {
    GeografijaDAO geo = GeografijaDAO.getInstance();

    @Test
    void test1() {
        //dodajGrad
        try{
            geo.dodajGrad(new Grad("Venecija", 5000, "Italija"));
            ArrayList<Grad> g = geo.gradovi();
            assertEquals("Venecija", g.get(5).getNaziv());

            geo.dodajGrad(new Grad("London", 8000, "UK"));
            ArrayList<Drzava> d = geo.drzave();
            assertEquals("UK", d.get(4).getNaziv());
        }
        catch (org.sqlite.SQLiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test2() {
        //obrisiDrzavu
        geo.obrisiDrzavu("Turska");
        ArrayList<Drzava> d = geo.drzave();
        assertEquals(4, d.size());

        ArrayList<Grad> g = geo.gradovi();
        assertEquals(6, g.size());
    }

    @Test
    void test3() {
        //izmijeniGrad
        geo.izmijeniGrad(new Grad("Pariz", 12345, "Francuska"));
        Grad g = geo.nadjiGrad("Pariz");
        assertEquals(12345, g.getBrojStanovnika());
    }

    @Test
    void test4() {
        //nadjiDrzavu
        Drzava d = geo.nadjiDrzavu("NekaDrzava");
        assertNull(d);
    }

    @Test
    void testDodajDrzavu() throws SQLException {
        geo.vratiNaDefault();

        geo.dodajDrzavu(new Drzava("BiH", "Sarajevo"));
        geo.dodajDrzavu(new Drzava("Francuska", "Pariz"));
        geo.dodajDrzavu(new Drzava("Turska", "Ankara"));

        ArrayList<Drzava> d = geo.drzave();
        assertEquals(3, d.size());

        ArrayList<Grad> g = geo.gradovi();
        assertEquals(3, g.size());
    }

    @Test
    void testGlavniGrad() throws SQLException {
        geo.vratiNaDefault();

        geo.dodajDrzavu(new Drzava("BiH", "Sarajevo"));
        geo.dodajDrzavu(new Drzava("Francuska", "Pariz"));
        geo.dodajDrzavu(new Drzava("Turska", "Ankara"));

        Drzava g = geo.nadjiDrzavu("Francuska");
        assertEquals("Pariz", g.getGlavni_grad());
    }
}
