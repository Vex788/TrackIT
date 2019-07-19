package trackit.demon.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Message {
    private Long userId;
    private String request;
    private String message;
    private String date;

    public Message(long userId, String request, String message) {
        this.userId = userId;
        this.request = request;
        this.message = message;
        this.date = new Date(System.currentTimeMillis()).toString();
    }

    public Message(String request, String message) {
        this.request = request;
        this.message = message;
        this.date = new Date(System.currentTimeMillis()).toString();
    }
}
