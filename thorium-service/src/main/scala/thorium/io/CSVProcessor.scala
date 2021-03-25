package thorium.io

import java.io.{BufferedWriter, FileWriter}
import java.util

import com.opencsv.CSVWriter
import thorium.processing.Processor

object CSVProcessor extends App {

  `export`("test")

  def process: Map[String, String] = Processor.run()

  def export(filename: String): Unit = {
    val outputFile = new BufferedWriter(new FileWriter("src\\main\\resources\\renders\\" + filename + ".csv"))
    val csvWriter = new CSVWriter(outputFile)
    val csvFields: Array[String] = Array("name", "latex")
    val listOfRecords: java.util.List[Array[String]] = new util.ArrayList[Array[String]]()
    listOfRecords.add(csvFields)
    val processed: Map[String, String] = process
    processed.foreach(pair => {
      listOfRecords.add(Array(pair._1, pair._2))
    })
    csvWriter.writeAll(listOfRecords)
    outputFile.close()
  }

}
