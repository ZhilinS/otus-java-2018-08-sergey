/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import java.util.ArrayList;
import org.cactoos.list.ListOf;
import org.junit.Before;
import org.junit.Test;
import ru.otus.dao.AdminDao;
import ru.otus.dao.UserDao;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.AdminDataSet;
import ru.otus.dataset.HiberUserDataSet;
import ru.otus.dataset.PhoneDataSet;
import static org.junit.Assert.*;

public class BaseTest {

    @Test
    public void userSaveLoad() {
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
        final UserDao service = new UserDao();
        service.save(user);
        final HiberUserDataSet fetched = service.get(1L);
        assertNotNull(fetched);
        assertEquals("Some address", fetched.address().address());
    }

    @Test
    public void adminCreated() {
        final UserDao service = new UserDao();
        final AdminDao adminService = new AdminDao();
        final HiberUserDataSet fetched = service.get(1L);
        adminService.save(
            new AdminDataSet(
                true,
                "strongPassword",
                fetched
            )
        );
    }

//    @Test
    public void byName() {
        final UserDao service = new UserDao();
        final HiberUserDataSet user = service.byName("Sergey");
        assertSame(1L, user.getId());
    }
}
