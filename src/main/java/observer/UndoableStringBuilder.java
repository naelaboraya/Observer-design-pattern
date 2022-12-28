package observer;
import java.util.LinkedList;
import java.util.Objects;

/**
 * This class describes a mutable and modifiable String , the length of the String can be changed throughout method calls.
 * The main purpose of this class is to give the ability to take a step backwards while doing operations on strings by calling a method.
 * This additional method called <strong>undo</strong> , implemented with the helping of <strong>LinkedList</strong> data structure.
 *<p>
 *For example, if {@code s} refers to a {@code UndoableStringBuilder} object
 * whose current contents are "{@code abcd}" , and the previous contents
 * were "{@code ab}" (before appending) , then the method call {@code s.undo()}
 * would cause the {@code UndoableStringBuilder} to contain "{@code ab}" (like how the String was before).
 * @see StringBuilder
 * @author Nael Aboraya , Marwan Hresh
 * @version 2
 */
public class UndoableStringBuilder {
    private StringBuilder sb;
    private LinkedList<String> history;


    /**
     * Constructs a string builder with the content of the
     * specified String.
     * <p>plus initializing the history LinkedList and adding the current String to it.
     * @param str  the given String.
     */
    public UndoableStringBuilder(String str) {
        sb = new StringBuilder(str);
        history = new LinkedList<>();
        history.add(str);
    }


    /**
     * Constructs a string builder with no characters in it.
     * <p>plus initializing the history LinkedList.
     */
    public UndoableStringBuilder() {
        sb = new StringBuilder();
        history = new LinkedList<>();
    }


    /**
     * Adds the specified string to the end of this string.
     *<p>The method also adds the new String to the history LinkedList.
     * @param str the given String.
     * @return a reference to this object.
     */
    public UndoableStringBuilder append(String str) {
        sb.append(str);
        history.add(sb.toString());
        return this;
    }


    /**
     * Inserts the specified String into this String , at the indicated offset, moving up any
     * characters originally above that position and increasing the length
     * of this sequence by the length of the argument.
     * <p>The method also adds the new String to the history LinkedList.
     * @param offset the offset.
     * @param str the given String.
     * @return a reference of this object.
     * @throws StringIndexOutOfBoundsException if the offset is invalid.
     */
    public UndoableStringBuilder insert(int offset, String str) throws StringIndexOutOfBoundsException{
        sb.insert(offset, str);
        history.add(sb.toString());
        return this;
    }


    /**
     * Deletes the Substring starting from {@code start}
     * to {@code end-1} indices of this string, or to the end of the String if no such character exists
     * <p>The method also adds the new String to the history LinkedList.
     * @param start start index of the substring to delete (inclusive).
     * @param end end index of substring to delete (exclusive).
     * @return a reference to this object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     * is negative, greater than {@code length()}, or
     * greater than {@code end}.
     */
    public UndoableStringBuilder delete(int start, int end) throws StringIndexOutOfBoundsException{
        sb.delete(start, end);
        history.add(sb.toString());
        return this;
    }


    /**
     * Replaces the Substring starting from {@code start}
     * to {@code end-1} indices of this string with
     * the specified String.
     * <p>The method also adds the new String to the history LinkedList.
     * @param start start index of the substring to replace (inclusive).
     * @param end end index of the substring to replace (exclusive).
     * @param str the given String.
     * @return a reference to this object.
     * @throws StringIndexOutOfBoundsException if start is negative, greater than length(), or greater than end.
     * @throws NullPointerException if str is null.
     */
    public UndoableStringBuilder replace(int start, int end, String str) throws StringIndexOutOfBoundsException,NullPointerException{
        sb.replace(start, end, str);
        history.add(sb.toString());
        return this;
    }


    /**
     * Reverses the String.
     * <p>The method also adds the new String to the history LinkedList.
     * @return a reference to this object.
     */
    public UndoableStringBuilder reverse() {
        sb.reverse();
        history.add(sb.toString());
        return this;
    }


    /**
     * Makes this object's String back to its previous state.
     * <p>For example if S refers to a {@code UndoableStringBuilder} object
     * with the String currently "java" , if we append "2023" to it
     * the String will be "java2023" , by calling this method
     * (S.undo()) the String of the object S will return to "java"
     * (the last state it was before appending "2023").
     * <p>
     * Deletes the last state of the LinkedList (the previous state of the String) by calling
     * method {@code removeLst} , gives the new last state of the LinkedList to {@code sb}
     * (the {@code StringBuilder} attribute of this object).
     */
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


    /**
     * equals() is a method defined in the Object class thus the default implementation of the .
     * equals() method compares the object references or the memory location where the objects are stored in the heap.
     * Thus, by default the .equals() method checks the object by using the “==” operator.
     * <p>This method overrides the equals() method that defined in Object class , it compares the string values
     * of two objects by equals() method in class String , if they are the same , the method returns true , otherwise,
     * returns false.
     * <p>The method checks if the specified object is null , if not , it checks
     * if the specified object's class is {@code String} or
     * {@code UndoableStringBuilder} , note that it will return false
     * if the specified object is neither {@code String} nor {@code UndoableStringBuilder} , even though  its
     * string content may actually be equal to this object string content.
     * <p>
     *<strong>This methode is mainly implemented for testing (checking if two strings are equal).</strong>
     * @param o the given object to compare this object with.
     * @return boolean value.
     */
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