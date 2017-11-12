import java.util.ArrayList;

public class reply {
    public reply(int state, String content){
        _state=state;
        _content=content;
    }
    public String read_content(){
        return _content;
    }
    public int read_state(){
        return _state;
    }
    private int _state;
    private String _content;
}
