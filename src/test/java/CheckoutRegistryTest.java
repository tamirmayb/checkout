import com.tamirm.checkout.CheckoutRegistry;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckoutRegistryTest {
    private static final String RULES_FILE_NAME = "src/main/java/config/registry-rules.json";
    CheckoutRegistry registry = new CheckoutRegistry();

    @SneakyThrows
    @Before
    public void init() {
        registry.initRegistry(RULES_FILE_NAME);
    }

    @Test
    public void testTotals() {
        assertEquals(0f, registry.checkout(""), 0);
        assertEquals(50f, registry.checkout("A"), 0);
        assertEquals(80f, registry.checkout("AB"), 0);
        assertEquals(110.6f, registry.checkout("CDBA"), 0);
        assertEquals(110.6f, registry.checkout("ABCD"), 0);
        assertEquals(100f, registry.checkout("AA"), 0);
        assertEquals(130.0f, registry.checkout("AAA"), 0);
        assertEquals(180f, registry.checkout("AAAA"), 0);
        assertEquals(230f, registry.checkout("AAAAA"), 0);
        assertEquals(260f, registry.checkout("AAAAAA"), 0);
        assertEquals(160.0f, registry.checkout("AAAB"), 0);
        assertEquals(170f, registry.checkout("AAABB"), 0);
        assertEquals(180.5f, registry.checkout("AAABBD"), 0);
        assertEquals(180.5f, registry.checkout("DABABA"), 0);
    }
}
