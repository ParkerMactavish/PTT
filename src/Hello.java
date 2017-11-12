import java.util.*;
import java.lang.*;

public class Hello {

    public static void main(String[] argv) {
        ArrayList<article> list_of_article=new ArrayList<article>();
        ArrayList<article> tmp_list_of_article=new ArrayList<article>();
        String input=new String();
        article tmp_article=new article("", "", "", 0);
        int state_code=9, num=0;
        Scanner scanner=new Scanner(System.in);
        while(state_code!=0){
            if(state_code==9) {
                System.out.println("Serial Number\t\tThumbs up\t\tClassification\t\tTopic");
                for (int i = 0; i < list_of_article.size(); i++) {
                    tmp_article = list_of_article.get(i);
                    tmp_article.print_outline();
                }
                input = scanner.next();

                if (input.equals("bye")) {
                    state_code = 0;
                } else if (input.equals("add")) {
                    state_code = 1;
                } else if (input.equals("read")) {
                    num = scanner.nextInt();
                    if (num > (list_of_article.size()) || num < 1) {
                        System.out.println("Please enter a valid serial number.");
                        state_code = 9;
                    } else {
                        state_code = 2;
                    }
                } else if (input.equals("delete")) {
                    state_code = 3;
                } else if (input.equals("search")) {
                    state_code = 4;
                } else if (input.equals("validate")) {
                    state_code = 5;
                } else {
                    state_code = 6;
                }
            }
           if(state_code==1) {
               String classification, topic, content;
               System.out.println("What kind of article is this one?");
               classification = scanner.next();
               System.out.println("What is the topic of your article?");
               while( (topic = scanner.nextLine()).equals("") );
               System.out.println("Type the content below");
               content = scanner.nextLine();
               tmp_article = new article(classification, topic, content, list_of_article.size()+1);
               list_of_article.add(tmp_article);
               state_code=9;
           }
           if(state_code==2) {
               tmp_article = list_of_article.get(num - 1);
               state_code=21;
           }
           if(state_code==21) {
               if (tmp_article.is_deleted()) {
                   System.out.println("What a pity~ This article has been deleted");
                   state_code=9;
               }
               else {

                   //System.out.println(state_code);
                   tmp_article.print_article();
                   input = scanner.next();
                   //System.out.println(input);
                   while (!input.equals("exit") &&!input.equals("reply")) {
                       System.out.println("Naughty boy/girl. Please enter exit or reply");
                       input = scanner.next();
                   }
                   //System.out.println(input);
                   if (input.equals("reply")) {
                       num=scanner.nextInt();
                       while( (input = scanner.nextLine()).equals("") );
                       switch (num) {
                           case 1:
                               tmp_article.do_reply(1, input);
                               break;
                           case 2:
                               tmp_article.do_reply(2, input);
                               break;
                           case 3:
                               tmp_article.do_reply(3, input);
                               break;
                       }
                       state_code = 21;
                       //System.out.println(state_code);
                   }
                   else {
                       state_code = 9;
                   }
               }
           }
           if(state_code==3){
                num=scanner.nextInt();
                list_of_article.get(num-1).delete();
                state_code=9;
           }
           if(state_code==4){
               input=scanner.next();
               while(!input.equals("-k")&&!input.equals("-p")){
                   System.out.println("Please enter -k or -p for argument.");
                   input=scanner.next();
               }
               if(input.equals("-k")){
                   input=scanner.next();
                   for(int i=0; i<list_of_article.size(); i++){
                       if(list_of_article.get(i).Read_topic().contains(input)||list_of_article.get(i).Read_classification().contains(input)){
                           tmp_list_of_article.add(list_of_article.get(i));
                       }
                   }
                   state_code=41;
               }
               else{
                   num=scanner.nextInt();
                   for(int i=0; i<list_of_article.size(); i++){
                       if(list_of_article.get(i).get_thumbs_ups()>=num){
                           tmp_list_of_article.add(list_of_article.get(i));
                       }
                   }
                   state_code=41;
               }
           }
           if(state_code==41){
               System.out.println("Code\t\tThumbs up\t\tClassification\t\tTopic");
               for(int i=0; i<tmp_list_of_article.size(); i++){
                   tmp_list_of_article.get(i).print_outline();
               }
               input = scanner.next();
               if(input.equals("exit")){ state_code=9;}
               else if(input.equals("add")) {state_code=42;}
               else if(input.equals("read")) {state_code=43;}
               else if(input.equals("delete")){ state_code=45;}
               else if(input.equals("search")){ state_code=4;}
           }
           if(state_code==42){
               String classification, topic, content;
               System.out.println("What kind of article is this one?");
               classification = scanner.nextLine();
               System.out.println("What is the topic of your article?");
               topic = scanner.nextLine();
               System.out.println("Type the content below\n");
               content = scanner.nextLine();
               tmp_article = new article(classification, topic, content, list_of_article.size());
               if(topic.contains(input)||classification.contains(input)){
                   tmp_list_of_article.add(tmp_article);
               }
               state_code=41;
           }
           if(state_code==43){
               num = scanner.nextInt();
               article tmp_tmp_article = new article("", "", "", 0);
               for(int i=0; i<tmp_list_of_article.size(); i++){
                   if(tmp_list_of_article.get(i).get_num()==num)
                       tmp_tmp_article=tmp_list_of_article.get(i);
               }
               while (tmp_tmp_article.get_num()==0) {
                   System.out.println("Naughty boy/girl. Please enter an integer in the range of available article.");
                   for(int i=0; i<tmp_list_of_article.size(); i++){
                       if(tmp_list_of_article.get(i).get_num()==num)
                           tmp_tmp_article=tmp_list_of_article.get(i);
                   }
               }
               tmp_article = tmp_tmp_article;
               state_code=44;
           }
           if(state_code==44) {
                if (tmp_article.is_deleted()) {
                    System.out.println("What a pity~ This article has been deleted");
                    state_code=41;
                }
                else {
                    tmp_article.print_article();
                    input = scanner.next();
                    while (!input.equals("exit") &&!input.equals("reply")) {
                        System.out.println("Naughty boy/girl. Please enter exit or reply");
                        input = scanner.next();
                    }
                    if (input.equals("reply")) {
                        num=scanner.nextInt();
                        input=scanner.nextLine();
                        switch (num) {
                            case 1:
                                tmp_article.do_reply(1, input);
                                break;
                            case 2:
                                tmp_article.do_reply(2, input);
                                break;
                            case 3:
                                tmp_article.do_reply(3, input);
                                break;
                        }
                        state_code = 43;
                    }
                    else {
                        state_code = 41;
                    }
                }
            }
            if(state_code==45){
                num=scanner.nextInt();
                article tmp_tmp_article = new article("", "", "", 0);
                for(int i=0; i<tmp_list_of_article.size(); i++){
                    if(tmp_list_of_article.get(i).get_num()==num)
                        tmp_tmp_article=tmp_list_of_article.get(i);
                }
                while (tmp_tmp_article.get_num()==0) {
                    System.out.println("Naughty boy/girl. Please enter an integer in the range of available article.");
                    for(int i=0; i<tmp_list_of_article.size(); i++){
                        if(tmp_list_of_article.get(i).get_num()==num)
                            tmp_tmp_article=tmp_list_of_article.get(i);
                    }
                }
                list_of_article.get(num-1).delete();
                state_code=41;
            }
           if(state_code==6){
                System.out.println("Input is not available, try again.");
                state_code=9;
           }
        }
    }
}