package Task5.Classes;

import lombok.Data;

@Data
public class Company {
    String name;
    String catchPhrase;
    String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}
