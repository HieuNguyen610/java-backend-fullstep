package hieu.javabackendfullstep.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPagingResponse implements Serializable {

    private int page;
    private int pageSize;
    private int totalElements;
    private List<UserResponse> userList;
}
