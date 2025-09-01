package co.com.loadapplication.model.gateway;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Gateway {
    private Long userId;
    private String name;
    private String email;
}
