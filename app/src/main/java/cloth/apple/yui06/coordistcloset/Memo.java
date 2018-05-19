package cloth.apple.yui06.coordistcloset;



import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte[] white;
    public byte[] picture=white;

    public String color = "";

    public String content = "";

    public String updateDate;

    public Memo(byte[] data) {

        picture = data;

    }

    public Memo(){
        picture=white;
        color="";
        content="";
    }



}
