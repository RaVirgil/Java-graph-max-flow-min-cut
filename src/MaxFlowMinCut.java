import javax.net.ssl.SSLEngineResult;
import java.util.*;

public class MaxFlowMinCut {

        public ArrayList<ArrayList<Integer>>graph;
        public Set<Edge> cutSet=new HashSet<>();
        public ArrayList<Integer> reachable=new ArrayList<>();
        public ArrayList<Integer> unreachable=new ArrayList<>();

        public MaxFlowMinCut (ArrayList<ArrayList<Integer>>graph) {
            this.graph=graph;
        }

        boolean PathFromSourceToSinkExist(int source, int sink, ArrayList<Integer> parents) {
            ArrayList<Boolean> visited = new ArrayList<>();
            for(int i=0; i<graph.size(); i++)
                visited.add(false);

            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(source);
            visited.set(source,true);
            parents.set(source,-1);

            while (queue.size()!=0) {
                int current = queue.poll();
                for (int i=0; i<graph.size(); i++) {
                    if (!visited.get(i) && graph.get(current).get(i) > 0) {
                        queue.add(i);
                        parents.set(i,current);
                        visited.set(i,true);
                    }
                }
            }



            return (visited.get(sink));
        }

        public int MaxFlowAndMinCut (int source, int sink) {
            int i, j;
            int maxFlow = 0;
            int pathFlow;
            ArrayList<ArrayList<Integer>> unchangedGraph=new ArrayList<>();
            for(i=0;i<graph.size();i++) {
                unchangedGraph.add(new ArrayList<>());
                for (j = 0; j < graph.size(); j++)
                    unchangedGraph.get(i).add(graph.get(i).get(j));

            }

            ArrayList<Integer> parents=new ArrayList<>();
            for(i=0;i<graph.size();i++)
                parents.add(0);

            while (PathFromSourceToSinkExist(source, sink, parents)) {

                pathFlow = Integer.MAX_VALUE;
                for (j=sink; j!=source; j=parents.get(j)) {
                    i = parents.get(j);
                    pathFlow = Math.min(pathFlow, graph.get(i).get(j));
                }
                
                for (j=sink; j != source; j=parents.get(j)) {
                    i = parents.get(j);
                    graph.get(i).set(j,graph.get(i).get(j)-pathFlow);
                    graph.get(j).set(i,graph.get(j).get(i)+pathFlow);
                }
                
                maxFlow += pathFlow;
            }
       
            
            for ( i = 0; i < graph.size(); i++) {
                if (PathFromSourceToSinkExist(source, i,parents)) {
                    reachable.add(i);
                }
                else {
                    unreachable.add(i);
                }
            }
            for ( i = 0; i < reachable.size(); i++) {
                for ( j = 0; j < unreachable.size(); j++) {
                    if (unchangedGraph.get(reachable.get(i)).get(unreachable.get(j)) > 0) {
                        cutSet.add(new Edge(reachable.get(i), unreachable.get(j)));
                    }
                }
            }
            return maxFlow;
        }

        public void printCutSet ()
        {
           cutSet.forEach(e-> System.out.println((e.source+1)+"-"+(e.destination+1)));
        }
}
