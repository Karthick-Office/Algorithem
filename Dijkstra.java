package my;

import java.util.*;
import java.util.stream.IntStream;

class Dijkstra {
    static class E { int s, d, w; E(int s, int d, int w) {this.s=s; this.d=d; this.w=w;}}

    void dj(E[] e, int V, int S) {
        int[] D = IntStream.range(0, V).map(i -> i == S ? 0 : Integer.MAX_VALUE).toArray();
        boolean[] visited = new boolean[V];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(D[a], D[b]));
        pq.add(S);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited[u] = true;

            Arrays.stream(e).forEach(E -> {
                if (!visited[E.d] && E.s == u && D[E.s] != Integer.MAX_VALUE && D[E.s] + E.w < D[E.d]) {
                    D[E.d] = D[E.s] + E.w;
                    pq.add(E.d);
                }
            });
        }

        IntStream.range(0, V).mapToObj(i -> "Vertex " + i + ": " + (D[i] == Integer.MAX_VALUE ? "INF" : D[i]))
        .forEach(System.out::println);
    }

    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int V, E;
        System.out.print("V E: ");
        V = s.nextInt();
        E = s.nextInt();
        E[] e = new E[E];
        System.out.println("Edges (s d w):");
        for (int i = 0; i < E; ++i) e[i] = new E(s.nextInt(), s.nextInt(), s.nextInt());
        System.out.print("Source: ");
        new Dijkstra().dj(e, V, s.nextInt());
        s.close();
    }
}
