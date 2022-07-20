package pOJO;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class RequestBody {
    private String content;
    private Integer post;
    private String title;
    private String status;
}
