package DataTransfer;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DataRequest {
    private String title;
    private String body;
    private String userId;
    private String baseUri;
    private  String endPoint;

}
