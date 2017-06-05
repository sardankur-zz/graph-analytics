#!/bin/bash

export MAVEN_HOME=/usr/share/maven
export PGX_HOME=/home/ankursarda/Software/pgx-2.2.1
export PGX_VERSION="2.2.1"
export PYTHON_EXEC_VER=/usr/bin/python3.5

if [ -z "$JAVA_HOME" ]; then
	echo "Need to set JAVA_HOME"
	exit 1
fi
  
if [ -z "$MAVEN_HOME" ]; then
	echo "Need to set MAVEN_HOME"	
	exit 1
fi

if [ -z "$PGX_HOME" ]; then
	echo "Need to set PGX_HOME"
	exit 1
fi

if [ -z "$PGX_VERSION" ]; then
	echo "Need to set PGX_VERSION"
	exit 1
fi

if [ $# -eq 0 ]; then
	echo "Enter one of the following arguments :  sh run.sh [args]"
	echo "TopologicalSort data_file"
	echo "DagCheck data_file"
	#echo "AverageNeighborDegree data_file"
	#echo "AverageDegreeConnectivity data_file"
	echo "CurrentFlowCloseness data_file"
	echo "CurrentFlowBetweenness data_file"
	echo "PreFlowPush data_file source destination"
	echo "EdmondsKarp data_file source destination"
	echo "CycleBasis data_file"
	echo "FindCycle data_file"
	echo "AverageClustering data_file"
	#echo "LabelPropagation data_file"
	#echo "MaximumIndependentSet data_file"
	#echo "SSSP data_file"

	exit
fi

dfile=$2
testfile=
pyfile=
pomfile=
extraargs=

case $PGX_VERSION in
	"2.2.1")
	pomfile=pgx/pom221.xml
	;;
	"2.3.1")
	pomfile=pgx/pom231.xml
	;;
	"2.4")
	pomfile=pgx/pom24.xml
	;;
	"2.5")
	pomfile=pgx/pom25.xml
	;;
esac

case $1 in
	"TopologicalSort")
	echo "The answers may be different, because there can be multiple ways of doing topological sort."
	testfile=algorithms.topological_sort.TopologicalSortExtTest
	pyfile=algorithms/topological_sort/topological_sort.py
	;;
			
	"DagCheck")
	testfile=algorithms.dag_check.DagCheckExtTest
	pyfile=algorithms/dag_check/dag_check.py
	;;
	
	# "AverageNeighborDegree")
	# testfile=algorithms.average_neighbor_degree.AverageNeighborDegreeExtTest
	# pyfile=algorithms/average_neighbor_degree/average_neighbor_degree.py
	# ;;
	
	# "AverageDegreeConnectivity")
	# testfile=algorithms.average_degree_connectivity.AverageDegreeConnectivityExtTest
	# pyfile=algorithms/average_degree_connectivity/average_degree_connectivity.py
	# ;;
	
	"CurrentFlowBetweenness")
	testfile=algorithms.current_flow.current_flow_betweenness.CurrentFlowBetweennessExtTest
	pyfile=algorithms/current_flow_betweenness_centrality/current_flow_betweenness_centrality.py	
	;;
	
	"CurrentFlowCloseness")
	testfile=algorithms.current_flow.current_flow_closeness.CurrentFlowClosenessExtTest	
	pyfile=algorithms/current_flow_closeness_centrality/current_flow_closeness_centrality.py
	;;
	
	"PreFlowPush")
	testfile=algorithms.maximum_flow.PreFlowPushExtTest
	pyfile=algorithms/preflow_push/preflow_push.py		
	extraargs="-Dsource=$3 -Ddest=$4"	
	extrargspy="$3 $4"
	;;
	
	
	"EdmondsKarp")
	testfile=algorithms.maximum_flow.EdmondsKarpMaximumFlowExtTest
	pyfile=algorithms/edmondskarp/edmondskarp.py	
	extraargs="-Dsource=$3 -Ddest=$4"
	extrargspy="$3 $4"
	;;
	
	"CycleBasis")
	testfile=algorithms.cycles.CycleBasisExtTest
	pyfile=algorithms/cycle_basis/cycle_basis.py
	;;
	
	"FindCycle")
	testfile=algorithms.cycles.FindCycleExtTest
	pyfile=algorithms/find_cycle/find_cycle.py
	;;
	
	"AverageClustering")
	testfile=algorithms.average_clustering.AverageClusteringExtTest
	pyfile=algorithms/average_clustering/average_clustering.py
	;;
	
	# "LabelPropagation")
	# testfile=algorithms.label_propagation.LabelPropagationExtTest
	# ;;
	
	# "MaximumIndependentSet")
	# testfile=algorithms.maximal_independent_set.MaximalIndependentSetExtTest
	# ;;
	
	# "SSSP")
	# testfile=algorithms.sssp.SsspExtTest
	# ;;
	
esac

echo "Running python test"
pythonout=$($PYTHON_EXEC_VER networkx/$pyfile $dfile $extrargspy)
echo $pythonout

echo "---------------------------------------"

echo "Compiling JAVA sources"

$MAVEN_HOME/bin/mvn -f $pomfile clean compile test-compile -DskipTests

echo "---------------------------------------"

echo "Running java test"

javares=$($MAVEN_HOME/bin/mvn -q -f $pomfile surefire:test -Dpgx.location=$PGX_HOME -Dtest=$testfile -DdFile=$dfile $extraargs | grep '###.*###')
javaout=$(echo "$javares" | cut -c1-3 --complement | rev | cut -c1-3 --complement | rev)
echo $javaout

echo "---------------------------------------"
echo "performing diff"

echo $javaout > javaout.txt
echo $pythonout > pythonout.txt

diff javaout.txt pythonout.txt

