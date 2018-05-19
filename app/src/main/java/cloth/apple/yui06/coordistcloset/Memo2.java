package cloth.apple.yui06.coordistcloset;

import io.realm.RealmObject;

public class Memo2 extends RealmObject {

    public byte[] white;
    public byte[] picture2;

    public String color2 = "";

    public String content2 = "";

    public String updateDate;

    public Memo2(byte[] data1) {

        picture2 = data1;

    }

    public Memo2(){
        picture2=white;
        color2="";
        content2="";
    }


}
