package jwlee.staywithme.domain.exceptions;

public class NoSuchAccommodation extends RuntimeException {
    public NoSuchAccommodation() {
        super("숙소를 찾을 수 없습니다.");
    }
}
