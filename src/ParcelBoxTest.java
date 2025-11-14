import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    @Test
    void addParcel_WithinWeight_LetterAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("Document", 3, "Office", 1);

        boolean added = box.addParcel(parcel);

        assertTrue(added);
        assertEquals(1, box.getAllParcels().size());
        assertEquals(3, box.getAllParcels().get(0).weight);
    }

    @Test
    void addParcel_ExactWeight_LetterAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(5);
        StandardParcel parcel = new StandardParcel("Book", 5, "Home", 1);

        boolean added = box.addParcel(parcel);

        assertTrue(added);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addParcel_OverWeight_LetterNotAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(7);
        StandardParcel parcel = new StandardParcel("Box", 8, "Warehouse", 1);

        boolean added = box.addParcel(parcel);

        assertFalse(added);
        assertTrue(box.getAllParcels().isEmpty());
    }

    @Test
    void addParcel_AddTwoParcelsWithinLimit_SuccessiveAdd() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel p1 = new StandardParcel("P1", 4, "A", 1);
        StandardParcel p2 = new StandardParcel("P2", 3, "B", 1);

        assertTrue(box.addParcel(p1));
        assertTrue(box.addParcel(p2));
        assertEquals(2, box.getAllParcels().size());
        assertEquals(7, box.getAllParcels().stream().mapToInt(p -> p.weight).sum());
    }

    @Test
    void addParcel_SecondParcelOverWeight_FirstStillThere() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(6);
        StandardParcel p1 = new StandardParcel("P1", 4, "A", 1);
        StandardParcel p2 = new StandardParcel("P2", 3, "B", 1);

        assertTrue(box.addParcel(p1));
        assertFalse(box.addParcel(p2));

        assertEquals(1, box.getAllParcels().size());
        assertEquals("P1", box.getAllParcels().get(0).description);
    }
}
