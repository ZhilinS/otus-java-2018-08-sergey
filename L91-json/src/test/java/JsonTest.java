import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import ru.otus.Oson;
import ru.otus.Pojo;
import static org.junit.Assert.assertEquals;

public class JsonTest {

    private Gson gson;
    private Oson oson;

    @Before
    public void setup() {
        final GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();

        oson = new Oson();
    }

    @Test
    public void shouldSerializeLikeGson() {
        final Pojo pojo = new Pojo(
            "simple",
            42,
            new ArrayList<Long>() {{
                add(12L);
                add(32L);
            }}
        );
        assertEquals(gson.toJson(pojo), oson.toJson(pojo));
    }
}
