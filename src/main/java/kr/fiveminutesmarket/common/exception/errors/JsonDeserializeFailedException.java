package kr.fiveminutesmarket.common.exception.errors;

public class JsonDeserializeFailedException extends RuntimeException{
    public JsonDeserializeFailedException() {
        super("JSON 형태의 문자열을 읽어오는데 실패하였습니다.");
    }
}
