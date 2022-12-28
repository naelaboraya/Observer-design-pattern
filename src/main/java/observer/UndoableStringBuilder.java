package observer;
import java.util.LinkedList;
import java.util.Objects;

public class UndoableStringBuilder {
    private StringBuilder sb;
    private LinkedList<String> history;

    public UndoableStringBuilder(String str) {
        sb = new StringBuilder(str);
        history = new LinkedList<>();
        history.add(str);
    }

    public UndoableStringBuilder() {
        sb = new StringBuilder();
        history = new LinkedList<>();
    }

    public UndoableStringBuilder append(String str) {
        sb.append(str);
        history.add(sb.toString());
        return this;
    }

    public UndoableStringBuilder insert(int index, String str) {
        sb.insert(index, str);
        history.add(sb.toString());
        return this;
    }

    public UndoableStringBuilder delete(int start, int end) {
        sb.delete(start, end);
        history.add(sb.toString());
        return this;
    }

    public UndoableStringBuilder replace(int start, int end, String str) {
        sb.replace(start, end, str);
        history.add(sb.toString());
        return this;
    }

    public UndoableStringBuilder reverse() {
        sb.reverse();
        history.add(sb.toString());
        return this;
    }

    public void undo() {
        if (history.size() > 0) {
            history.removeLast();
            if (history.size() > 0) {
                sb = new StringBuilder(history.getLast());
            } else {
                sb = new StringBuilder();
            }
        }
    }
    @Override
    public boolean equals(Object o) {//For testing
        if(o==null){
            if(this.sb.toString()==null) return true;
            return false;
        }
        else if(o.getClass()==String.class) {//checking if the specified object is of type String
            if (this.sb.toString().equals((String) o)){return true;}
            return false;}

        else if(o.getClass()== UndoableStringBuilder.class){//checking if the specified object is of type UndoableStringBuilder
            if (this.sb.toString().equals(o.toString())){return true;}
            return false;}

        return false;//else...(any other class the answer will be false)
    }

    @Override//with equals().
    public int hashCode() {
        return Objects.hash(sb);
    }


    @Override
    public String toString() {
        return sb.toString();
    }
}