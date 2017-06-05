The test cases can be run in a general format
mvn surefire:test -Dtest=[TestFileClass] -DdFile=[TestData] [other arguments if required]

1. Install Maven 
2. Set path of pgx software folder in pom.xml (Check for property pgx.location)
3. On the root directory of the project (same directory as pom file) run "mvn clean compile"
4. Run the test cases as mentioned below.
5. To run in different pgx environment, there are separate pom files as pom24.xml, pom221.xml, pom25.xml. Copy and paste the content to pom.xml. Change the path of pgx location to the desired version.
6. There is an API change with respect to loading graph from 2.3 onwards.  Goto graph-analytics/pgx/src/main/java/algorithms/Algorithm.java. Uncomment the version required and comment the version not required.

Note : Most of the algorithm written expect ids to start from 0 to number of nodes present in the graph with all ids present. We will write an intermediate layer to mediate this problem. 

Average Degree Connectivity 

mvn surefire:test -Dtest=algorithms.average_degree_connectivity.AverageDegreeConnectivityExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data3.adj

<br />
Average Neighbor Degree

mvn surefire:test -Dtest=algorithms.average_neighbor_degree.AverageNeighborDegreeExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data1.adj

<br />

Current Flow Betweenness
mvn surefire:test -Dtest=algorithms.current_flow.current_flow_betweenness.CurrentFlowBetweennessExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data5.adj

<br />

Current Flow Closeness

mvn surefire:test -Dtest=algorithms.current_flow.current_flow_closeness.CurrentFlowClosenessExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data5.adj

<br />

DAG check

mvn surefire:test -Dtest=algorithms.dag_check.DagCheckTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data1.adj

<br />

Label Propagation

mvn surefire:test -Dtest=algorithms.label_propagation.LabelPropagationExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data1.adj -Diters=50

<br />

Maximal Independent Set

mvn surefire:test -Dtest=algorithms.maximal_independent_set.MaximalIndependentSetExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data1.adj

<br />

Preflow Push Maximum Flow

mvn surefire:test -Dtest=algorithms.maximum_flow.PreFlowPushExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data6.adj -Dsource=0 -Ddest=3

<br />

Edmonds Karp Maximum Flow

mvn surefire:test -Dtest=algorithms.maximum_flow.EdmondsKarpMaximumFlowExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data6.adj -Dsource=0 -Ddest=3

<br />

Single Source Shortest Path with landmarks

mvn surefire:test -Dtest=algorithms.sssp.SsspExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data2.adj -Dsource=1 -Dlandmarks=2,3

<br />

Topological Sort

mvn surefire:test -Dtest=algorithms.topological_sort.TopologicalSortExtTest -DdFile=/home/ankursarda/Projects/graph-analytics/pgx/src/test/java/data/sample_data1.adj
