import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import ru.otus.Oson;
import ru.otus.pojos.OtherPojo;
import ru.otus.pojos.Pojo;
import static org.junit.Assert.assertEquals;

public class JsonTest {

    private Gson gson;
    private Oson oson;

    @Before
    public void setup() {
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

        oson = new Oson();
    }

    @Test
    public void shouldSerializeLikeGson() throws IllegalAccessException {
        final Pojo pojo = new Pojo<>(
            "simple",
            42,
            new ArrayList<Long>() {{
                add(12L);
                add(32L);
            }},
            new double[] {12.2, 13.3, 14.4},
            new OtherPojo[] { new OtherPojo("other", 11) }
        );
        assertEquals(gson.toJson(pojo), oson.toJson(pojo));
    }
}
