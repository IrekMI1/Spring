package Library.api;

import lombok.Data;

@Data
public class IssueRequest {
    private Integer readerId;
    private Integer bookId;
}