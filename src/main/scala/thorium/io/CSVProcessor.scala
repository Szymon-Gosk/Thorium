package thorium.io

import java.io.{BufferedWriter, FileWriter}
import java.util

import com.opencsv.CSVWriter
import thorium.processing.Processor

object CSVProcessor extends App {

  `export`("test", "test")

  def process: Seq[String] = Seq(
    Processor.run(1)
  )

  def export(filename: String, buildname: String): Unit = {
    val outputFile = new BufferedWriter(new FileWriter("src\\main\\resources\\renders\\" + filename + ".csv"))
    val csvWriter = new CSVWriter(outputFile)
    val csvFields: Array[String] = Array("name", "latex")
    val listOfRecords: java.util.List[Array[String]] = new util.ArrayList[Array[String]]()
    listOfRecords.add(csvFields)
    val processed: Seq[String] = process
    for(i <- process.indices) {
      listOfRecords.add(Array(buildname, processed(i)))
    }
    csvWriter.writeAll(listOfRecords)
    outputFile.close()
  }

}
