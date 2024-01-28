package my;

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;

class bellman {
    static class E { int s, d, w; E(int s, int d, int w) {this.s=s; this.d=d; this.w=w;}}

    void bf(E[] e, int V, int S) {
        int[] D = IntStream.range(0, V).map(i -> i == S ? 0 : Integer.MAX_VALUE).toArray();
        
        Arrays.stream(e).forEach(E -> {
            if (D[E.s] != Integer.MAX_VALUE && D[E.s] + E.w < D[E.d]) {
                D[E.d] = D[E.s] + E.w;
                if (D[E.d] < 0) System.out.println("Negative cycle");
            }
        });
        
        IntStream.range(0, V).mapToObj(i -> "Vertex " + i + ": " + (D[i] == Integer.MAX_VALUE ? "INF" : D[i]))
        .forEach(System.out::println);

    }


    public static void main(String[] a) {
    	Scanner s = new Scanner(System.in); int V, E; System.out.print("V E: "); V = s.nextInt(); E = s.nextInt();
        E[] e = new E[E];
        System.out.println("Edges (s d w):");
        for (int i = 0; i < E; ++i) e[i] = new E(s.nextInt(), s.nextInt(), s.nextInt());
        System.out.print("Source: "); new bellman().bf(e, V, s.nextInt());
        s.close();
    }
}
