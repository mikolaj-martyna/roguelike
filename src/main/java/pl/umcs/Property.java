package pl.umcs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Property {
    int baseline;
    int current;
    int min;
    int max;
}
