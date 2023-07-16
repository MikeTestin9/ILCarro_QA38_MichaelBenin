package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
    public Iterator<Object[]> userDto() {
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

    @DataProvider
    public Iterator<Object[]> userDtoCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/reg_dataset.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .withName(split[0])
                    .withLastName(split[1])
                    .withEmail(split[2])
                    .withPassword(split[3])
            });
            line = reader.readLine();
        }
        return list.iterator();
    }


}
