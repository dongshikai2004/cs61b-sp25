import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     void addFirstTest(){
         Deque61B<Integer>a=new ArrayDeque61B<>();
         assertThat(a.toList()).containsExactly().inOrder();
         a.addFirst(1);
         assertThat(a.toList()).containsExactly(1).inOrder();
         a.addFirst(2);
         assertThat(a.toList()).containsExactly(2,1).inOrder();
         a.addFirst(3);
         assertThat(a.toList()).containsExactly(3,2,1).inOrder();
         a.addFirst(4);
         assertThat(a.toList()).containsExactly(4,3,2,1).inOrder();
     }

    @Test
    void addLastTest(){
        Deque61B<Integer>a=new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
        a.addLast(1);
        assertThat(a.toList()).containsExactly(1).inOrder();
        a.addLast(2);
        assertThat(a.toList()).containsExactly(1,2).inOrder();
        a.addLast(3);
        assertThat(a.toList()).containsExactly(1,2,3).inOrder();
        a.addLast(4);
        assertThat(a.toList()).containsExactly(1,2,3,4).inOrder();
    }

    @Test
    void addTest(){
        Deque61B<Integer>a=new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
        a.addFirst(1);
        assertThat(a.toList()).containsExactly(1).inOrder();
        a.addLast(2);
        assertThat(a.toList()).containsExactly(1,2).inOrder();
        a.addFirst(3);
        assertThat(a.toList()).containsExactly(3,1,2).inOrder();
        a.addLast(4);
        assertThat(a.toList()).containsExactly(3,1,2,4).inOrder();
    }
    @Test
    void removeFirstTest(){
        Deque61B<Integer>a=new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        assertThat(a.removeFirst()).isEqualTo(4);
        assertThat(a.toList()).containsExactly(3,2,1).inOrder();
        assertThat(a.removeFirst()).isEqualTo(3);
        assertThat(a.toList()).containsExactly(2,1).inOrder();
        assertThat(a.removeFirst()).isEqualTo(2);
        assertThat(a.toList()).containsExactly(1).inOrder();
        assertThat(a.removeFirst()).isEqualTo(1);
        assertThat(a.toList()).containsExactly().inOrder();
    }

    @Test
    void removeLastTest(){
        Deque61B<Integer>a=new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        assertThat(a.removeLast()).isEqualTo(1);
        assertThat(a.toList()).containsExactly(4,3,2).inOrder();
        assertThat(a.removeLast()).isEqualTo(2);
        assertThat(a.toList()).containsExactly(4,3).inOrder();
        assertThat(a.removeLast()).isEqualTo(3);
        assertThat(a.toList()).containsExactly(4).inOrder();
        assertThat(a.removeLast()).isEqualTo(4);
        assertThat(a.toList()).containsExactly().inOrder();
    }

    @Test
    public void resizeTest(){
        Deque61B<Integer>a=new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
        a.addFirst(1);
        a.addLast(2);
        a.addFirst(3);
        a.addLast(4);
        a.addFirst(5);
        a.addLast(6);
        a.addFirst(7);
        a.addLast(8);
        //resizeUp()
        assertThat(a.toList()).containsExactly(7,5,3,1,2,4,6,8).inOrder();
        a.addFirst(9);
        assertThat(a.toList()).containsExactly(9,7,5,3,1,2,4,6,8).inOrder();
        a.addLast(10);
        assertThat(a.toList()).containsExactly(9,7,5,3,1,2,4,6,8,10).inOrder();
        a.addFirst(11);
        assertThat(a.toList()).containsExactly(11,9,7,5,3,1,2,4,6,8,10).inOrder();
        a.addLast(12);
        assertThat(a.toList()).containsExactly(11,9,7,5,3,1,2,4,6,8,10,12).inOrder();
        //resizeDown()
        a.removeFirst();
        assertThat(a.toList()).containsExactly(9,7,5,3,1,2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(7,5,3,1,2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(5,3,1,2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(3,1,2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(1,2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(2,4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(4,6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(6,8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(8,10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(10,12).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(12).inOrder();
    }
    @Test
    public void IterateTest(){
        Deque61B<String> a = new ArrayDeque61B<>();
        a.addLast("front");
        a.addLast("middle");
        a.addLast("back");
        assertThat(a).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void toStringTest(){
        Deque61B<String> a = new ArrayDeque61B<>();
        a.addLast("front");
        a.addLast("middle");
        a.addLast("back");
        assertThat(a.toString()).isEqualTo("[front, middle, back]");
    }
}
