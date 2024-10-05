package jwlee.staywithme.domain.exceptions;

public class NotfoundImageException extends RuntimeException {
    public NotfoundImageException() {
        super("이미지가 존재하지 않습니다");
    }
}
