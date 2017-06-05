package graph_analytics.graphx.util

import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.io.Source

object JsonUtil {
      def getJson(filePath : String) : JValue = {
          val fileContents = Source.fromFile(filePath).getLines.mkString
          return parse(fileContents)
      }
}
