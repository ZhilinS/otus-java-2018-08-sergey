/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import java.util.ArrayList;
import models.AddressDataSet;
import models.HiberUserDataSet;
import models.PhoneDataSet;
import org.cactoos.list.ListOf;
import org.cactoos.set.SetOf;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BaseTest {

    @Test
    public void userSaveLoad() {
        final UserService service = new UserService();
        final HiberUserDataSet user = new HiberUserDataSet(
            "Sergey",
            27,
            new ArrayList<>(),
            new AddressDataSet("Some address")
        );
        final PhoneDataSet first = new PhoneDataSet("8-800-555-35-35", user);
        final PhoneDataSet second = new PhoneDataSet("8-345-543-23-21", user);
        user.setPhone(
            new ListOf<>(first, second)
        );
        service.save(user);
        final HiberUserDataSet fetched = service.get(1L);
        assertNotNull(fetched);
        assertEquals("Some address", fetched.address().address());
    }
}
