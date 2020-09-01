import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String...arg) throws FileNotFoundException {
            ArrayList<ArrayList<Integer>>graph=new ArrayList<>();
        ArrayList<ArrayList<Integer>>residualGraph=new ArrayList<>();
            int numberOfNodes;
            int source;
            int sink;
            int maxFlow;

            Scanner f = new Scanner(new File("graph.txt"));
            Scanner console=new Scanner(System.in);
            numberOfNodes = f.nextInt();

            for (int i = 0; i < numberOfNodes; i++)
            {
                graph.add(new ArrayList<>());
                for (int j = 0; j < numberOfNodes ; j++)
                {
                    graph.get(i).add(f.nextInt());
                }
            }

            System.out.println("Enter the source of the graph 1-"+graph.size());
            source= console.nextInt()-1;

            System.out.println("Enter the sink of the graph 1-"+graph.size());
            sink = console.nextInt()-1;

            MaxFlowMinCut maxFlowMinCut = new MaxFlowMinCut(graph);
            maxFlow = maxFlowMinCut.MaxFlowAndMinCut(source, sink);

            System.out.println("The Max Flow is " + maxFlow);
            System.out.println("The Cut Set is ");
            maxFlowMinCut.printCutSet();

        }
    }


