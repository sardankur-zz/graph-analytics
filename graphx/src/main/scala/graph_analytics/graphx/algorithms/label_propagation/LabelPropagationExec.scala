package graph_analytics.graphx.algorithms.label_propagation

import graph_analytics.graphx.util.{JsonUtil, SparkUtil}
import org.apache.spark.graphx._
import org.apache.spark.graphx.lib._
import org.apache.spark.rdd.RDD

object LabelPropagationExec {

    def main(args: Array[String]): Unit = {
        val sc = SparkUtil.getContext(SparkUtil.getConf())
        val json = JsonUtil.getJson("src/main/scala/graph_analytics/graphx/" +
                "algorithms/label_propagation/data/sample_data.json")
        //        val edges_j = (json \ "edges").extract[List[(Int, Int, Int)]]
        val nodes: RDD[(VertexId, (String, String))] =
            sc.parallelize(
                (json \ "nodes").values.asInstanceOf[List[Map[String, Object]]]
                        .map((node) => (node.get("id").asInstanceOf[Option[BigInt]].get.longValue(),
                                ("label", node.get("label").asInstanceOf[Option[String]].get))))
        val edges: RDD[Edge[String]] =
            sc.parallelize(
                (json \ "edges").values.asInstanceOf[List[Map[String, Object]]]
                        .map((edge) =>
                            Edge(edge.get("source").asInstanceOf[Option[BigInt]].get.longValue(),
                                edge.get("target").asInstanceOf[Option[BigInt]].get.longValue(),
                                "related")
                        )
            )

        val graph = Graph(nodes, edges)
//        val labels = LabelPropagation.run(graph, nodes.count().asInstanceOf[Int] * 4)
        val labels = LabelPropagation.run(graph, 10)
        val sortedLabels = labels.vertices.collect().sortBy(id => id._1)
        for (label <- sortedLabels) {
            println(label)
        }
    }

}
