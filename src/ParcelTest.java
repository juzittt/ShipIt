import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelTest {

    @Test
    void calculateDeliveryCost_StandardParcel_NormalWeight_CorrectCost() {
        StandardParcel parcel = new StandardParcel("Book", 5, "Moscow", 1);
        int cost = parcel.calculateDeliveryCost(parcel);
        assertEquals(10, cost);
    }

    @Test
    void calculateDeliveryCost_FragileParcel_NormalWeight_CorrectCost() {
        FragileParcel parcel = new FragileParcel("Vase", 3, "SPb", 2);
        int cost = parcel.calculateDeliveryCost(parcel);
        assertEquals(12, cost);
    }

    @Test
    void calculateDeliveryCost_PerishableParcel_NormalWeight_CorrectCost() {
        PerishableParcel parcel = new PerishableParcel("Cheese", 4,
                "Kazan", 3, 5);
        int cost = parcel.calculateDeliveryCost(parcel);
        assertEquals(12, cost);
    }

    @Test
    void calculateDeliveryCost_StandardParcel_ZeroWeight_ZeroCost() {
        StandardParcel parcel = new StandardParcel("Empty box", 0, "Klin", 1);
        int cost = parcel.calculateDeliveryCost(parcel);
        assertEquals(0, cost);
    }

    @Test
    void isExpired_PerishableParcel_NotExpired() {
        PerishableParcel parcel = new PerishableParcel("Milk", 1,
                "Kaliningrad", 1, 5);
        boolean expired = parcel.isExpired(5);
        assertFalse(expired);
    }

    @Test
    void isExpired_PerishableParcel_Expired() {
        PerishableParcel parcel = new PerishableParcel("Ice cream", 1,
                "Tver", 2, 3);
        boolean expired = parcel.isExpired(6);
        assertTrue(expired);
    }

    @Test
    void isExpired_PerishableParcel_ExpiresToday() {
        PerishableParcel parcel = new PerishableParcel("Yoghurt", 1,
                "Mytishi", 1, 4);
        boolean expired = parcel.isExpired(5);
        assertFalse(expired);
    }
}
