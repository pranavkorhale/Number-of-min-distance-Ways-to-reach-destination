class Solution {
    public class Pair{
        int vertex;
        int weight;
        public Pair(int vertex,int weight)
        {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public int countPaths(int n, int[][] roads) {
        final int mod = 1000000007;
        ArrayList<Pair>[]adj = new ArrayList[n];
        for(int i=0;i<n;i++)
            adj[i] = new ArrayList<Pair>();
        
        for(int i=0;i<roads.length;i++){
            int u = roads[i][0];
            int v = roads[i][1];
            int wt = roads[i][2];
            adj[v].add(new Pair(u,wt));
            adj[u].add(new Pair(v,wt)); 
        }
        for(int i=0;i<adj.length;i++)
        {
            ArrayList<Pair> al = adj[i];
            for(int j=0;j<al.size();j++)
            {
                Pair p = al.get(j);
                // System.out.println(i+" --> "+p.vertex+" its weight is  "+p.weight);
            }
        }
        
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b)->{
            return a.weight-b.weight;
        });
        
        pq.add(new Pair(0,0));
        int []ways = new int[n];
        int []dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        ways[0] = 1;
        dist[0] = 0;
        
        while(pq.size()!=0)
        {
            Pair p = pq.poll();
            int vt = p.vertex;
            int wt = p.weight;
            ArrayList<Pair> al = adj[vt];
            for(int i=0;i<al.size();i++)
            {
                Pair p2 = al.get(i);
                int v = p2.vertex;
                int w = p2.weight;
                int totalDist = w+wt;
                if(totalDist<dist[v])
                {
                    System.out.println("hits here");
                    dist[v] = totalDist;
                    ways[v] = ways[vt];
                    pq.add(new Pair(v,dist[v]));
                }
                else if(totalDist==dist[v])
                {
                    ways[v] = (ways[v]+ways[vt])%mod;
                }
            }
        }
        // System.out.println(Arrays.toString(ways));
        return ways[n-1];
    }
}
