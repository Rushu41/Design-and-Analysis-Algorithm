import java.util.*;

class Node {
    char c;
    int fq;
    Node left, right;

    Node(char c, int fq) {
        this.c = c;
        this.fq = fq;
    }
}

public class huffman {
    static void huffman(char[] c, int[] frq) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.fq - b.fq);
        int n = c.length;
        for (int i = 0; i < n; i++) {
            pq.add(new Node(c[i], frq[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('#', left.fq + right.fq);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        print(pq.peek(), "");
    }

    static void print(Node n, String s) {
        if (n == null)
            return;

        if (n.c != '#') {
            System.out.println(n.c + " " + s);
        }

        print(n.left, s + '0');
        print(n.right, s + '1');
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        Scanner scanner = new Scanner(System.in);
        int[] freq = new int[256];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        int distinctCount = 0;
        for (int f : freq) {
            if (f > 0)
                distinctCount++;
        }

        char[] chars = new char[distinctCount];
        int[] finalFreq = new int[distinctCount];

        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                chars[index] = (char) i;
                finalFreq[index] = freq[i];
                index++;
            }
        }
        huffman(chars, finalFreq);

    }
}
