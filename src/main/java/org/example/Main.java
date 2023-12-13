package org.example;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        final var data1 = new ListNode(2, new ListNode(4, new ListNode( 3)));
        final var data2 = new ListNode(5, new ListNode(6, new ListNode( 4)));

        final var expectedData = new int[]{ 7, 0, 8 };
        final var calculated = addTwoNumbers(data1, data2).getAllNodes();
        for ( int i = 0; i < expectedData.length; i ++)
            assert calculated.get(i).val == expectedData[i];
    }

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single
     * digit. Add the two numbers and return the sum as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero,
     * except the number 0 itself.
     *
     * Example 1:
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * Example 2:
     *
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     * Example 3:
     *
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */
    public static ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {

        final var innerNodes1 = getAllNodesAndReverse(l1);
        final var innerNodes2 = getAllNodesAndReverse(l2);

        final var summedNumber =
                new BigInteger((innerNodes1.stream().map(node -> node.val + "").collect(Collectors.joining("")))).add(
                        new BigInteger((innerNodes2.stream().map(node -> node.val + "").collect(Collectors.joining("")))));

        if (summedNumber.compareTo(BigInteger.ONE) < 0)
            return new ListNode(0);

        final var returnNodes = new LinkedList<>(Arrays.stream(summedNumber.toString().split("")).map(val -> new ListNode(Integer.parseInt(val))).toList());
        Collections.reverse(returnNodes);

        final var start = returnNodes.poll();
        var head = start;
        ListNode next;
        while((next = returnNodes.poll()) != null && head != null) {
            head.next = next;
            head = next;
        }

        return start;
    }

    public static List<ListNode> getAllNodesAndReverse(final ListNode node) {

        var nextNode = node.next;
        final var nodes = new ArrayList<ListNode>();
        nodes.add(node);
        while(nextNode != null) {
            nodes.add(nextNode);
            nextNode = nextNode.next;
        }

        Collections.reverse(nodes);
        return nodes;
    }

    public static class ListNode {
        int val;
        ListNode next = null;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public List<ListNode> getAllNodes() {

            var nextNode = next;
            final var nodes = new ArrayList<ListNode>();
            nodes.add(this);
            while(nextNode != null) {
                nodes.add(nextNode);
                nextNode = nextNode.next;
            }

            return nodes;
        }
    }

}