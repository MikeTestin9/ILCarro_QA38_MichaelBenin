package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class Search {

    String city;
    String dates;
    String dates1;
    String dates2;
}
