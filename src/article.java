import java.util.*;

public class article {
    private int _num;
    private int _thumbs_ups;
    private String _classification;
    private String _topic;
    private String _content;
    private ArrayList<reply> _list_of_reply=new ArrayList<reply>();
    private boolean _is_deleted=false;
    public article(String classification, String topic, String content, int num){
        _classification=classification;
        _topic=topic;
        _content=content;
        _num=num;
    };
    public void do_reply(int state, String content){
        reply tmp_reply=new reply(state, content);
        _list_of_reply.add(tmp_reply);
    }
    public void print_outline(){
        if(!_is_deleted) {
            System.out.print("\t");
            System.out.print(_num);
            System.out.print("\t\t\t\t\t");
            get_thumbs_ups();
            System.out.print(_thumbs_ups);
            System.out.print("\t\t\t\t");
            System.out.print(_classification);
            System.out.print("\t\t\t");
            System.out.println(_topic);
        }
    }
    public void print_article(){
        System.out.println(_classification);
        System.out.println(_topic);
        System.out.println(_content);
        System.out.println("State\tContent");
        for(int i=0; i<_list_of_reply.size();i++){
            reply tmp_reply=_list_of_reply.get(i);
            System.out.print(tmp_reply.read_state());
            System.out.print("\t");
            System.out.println(tmp_reply.read_content());
        }
    }
    public boolean is_deleted(){
        return _is_deleted;
    }
    public void delete(){
        _is_deleted=true;
    }
    public String Read_topic(){
        return _topic;
    }
    public String Read_classification(){
        return _classification;
    }
    public int get_thumbs_ups(){
        int tmp=0;
        for(int i=0; i<_list_of_reply.size(); i++)
            if(_list_of_reply.get(i).read_state()==1)
                tmp++;
        _thumbs_ups=tmp;
        return _thumbs_ups;
    }
    public int get_num(){
        return _num;
    }
}
