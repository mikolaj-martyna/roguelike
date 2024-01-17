package pl.umcs.entities;

import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Property {
    int baseline;
    int current;

    double multiplier;

    public Property(int baseline) {
        this.baseline = baseline;
        this.current = baseline;
        this.multiplier = 1;
    }
}
