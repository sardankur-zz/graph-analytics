package graph_analytics.graphx.util

import org.apache.spark.{SparkConf, SparkContext}

object SparkUtil {

    def getConf() : SparkConf = {
           return new SparkConf().setAppName("Label Propagation").setMaster("local")
    }

    def getContext(sparkConf : SparkConf) : SparkContext = {
        new SparkContext(sparkConf)
    }
}
