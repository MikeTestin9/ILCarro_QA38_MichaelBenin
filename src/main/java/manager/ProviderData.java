package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
    public Iterator<Object[]> userDto(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withEmail("mikeben@gmail.com")
                .withPassword("Mb12345$")
        });
        list.add(new Object[]{new User()
                .withEmail("mikeben@gmail.com")
                .withPassword("Mb12345$")
        });
        list.add(new Object[]{new User()
                .withEmail("mikeben@gmail.com")
                .withPassword("Mb12345$")
        });
        return list.iterator();

    }


}
