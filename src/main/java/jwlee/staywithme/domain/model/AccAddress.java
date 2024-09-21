package jwlee.staywithme.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccAddress {

    @Builder
    public AccAddress(String postNo, String address1, String address2) {
        this.postNo = postNo;
        this.address1 = address1;
        this.address2 = address2;
    }

    private String postNo;
    private String address1;
    private String address2;
}
