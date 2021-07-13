package kr.fiveminutesmarket.common.exception.errors;

public class JsonSerializeFailedException extends RuntimeException{
    public JsonSerializeFailedException() {
        super("객체를 직렬화하는데 실패하였습니다");
    }
}
