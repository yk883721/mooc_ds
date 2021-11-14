package custom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ArrayTest<E> {

//    [2222222,2222222]
//            4444444

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        ListNode p1 = l1;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;
        l1.next = new ListNode(9); l1 = l1.next;


        ListNode l2 = new ListNode();
        ListNode p2 = l2;
        l2.next = new ListNode(9); l2 = l2.next;
        l2.next = new ListNode(9); l2 = l2.next;
        l2.next = new ListNode(9); l2 = l2.next;
        l2.next = new ListNode(9); l2 = l2.next;

//        ListNode res = addTwoNumbers(p1.next,p2.next);
//        print(res);

        System.out.println(isValid("{[]}"));

    }

    public static boolean isValid(String s) {
        byte[] stack = new byte[s.length()];
        short tail = -1;
        byte c;
        byte left;

        for (short i = 0; i < s.length(); i++) {
             c = (byte) s.charAt(i);
            if ('(' == c || '[' == c || '{' == c){
                stack[++tail] = c;
            }else {
                if (tail == -1){
                    return false;
                }
                left =  stack[tail--];

                if (')' == c && '(' != left){
                    return false;
                }else if (']' == c && '[' != left){
                    return false;
                }else if ('}' == c && '{' != left){
                    return false;
                }
            }
        }

        return tail == -1;
    }


    public static void print(ListNode list){
        while (list != null){
            System.out.print(list.val + " " );
            list = list.next;
        }
    }

}
