package com.sparkfits

import java.net.URI

// import gov.llnl.spark.hdf.ScanExecutor.{BoundedScan, UnboundedScan}
// import gov.llnl.spark.hdf.reader.HDF5Schema.{Dataset}
import org.apache.commons.io.FilenameUtils
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.sources.{BaseRelation, TableScan}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import com.sparkfits.FitsFileInputFormat._
import com.sparkfits.FitsLib.FitsBlock
import org.apache.hadoop.conf.Configuration
import com.sparkfits.FitsSchema.getEmptySchema

class FitsRelation( val paths: Array[String], val hdu: Int)(@transient val sqlContext: SQLContext)
    extends BaseRelation with TableScan {

  // val path = new Path(paths)
  // val indexHDU = conf.get("HDU").toInt
  // val fB = new FitsBlock(path, conf, indexHDU)

  // val hadoopConfiguration = sqlContext.hadoopConfiguration
  // val fileSystem = FileSystem.get(hadoopConfiguration)

  // lazy val files: Array[URI] = {
  //   val roots = paths.map{ path =>
  //     fileSystem.getFileStatus(new Path(path)) }.toSeq
  //
  //   val leaves = roots.flatMap{
  //     case status if status.isFile => Set(status)
  //     case directory if directory.isDirectory =>
  //       val it = fileSystem.listFiles(directory.getPath, true)
  //       var children: Set[FileStatus] = Set()
  //       while (it.hasNext) {
  //         children += it.next()
  //       }
  //       children
  //   }
  //
  //   leaves.filter(status => status.isFile)
  //     .map(_.getPath)
  //     .filter(path => fileExtension.contains(FilenameUtils.getExtension(path.toString)))
  //     .map(org.apache.hadoop.fs.Path.getPathWithoutSchemeAndAuthority(_).toUri)
  //     .toArray
  // }
  //
  // private lazy val datasets: Array[Dataset[_]] = files.flatMap {
  //   file => new ScanExecutor(file.toString).openReader(_.getObject(dataset))
  // }.collect { case y: Dataset[_] => y }
  //
  // private lazy val hdf5Schema: Dataset[_] = datasets match {
  //   case Array(head: Dataset[_], _*) => head
  //   case _ => throw new java.io.FileNotFoundException("No files")
  // }

  override def schema: StructType = getEmptySchema

  override def buildScan(): RDD[Row] = {
    // val scans = datasets.map{ UnboundedScan(_, chunkSize) }
    // val splitScans = scans.flatMap{
    //   case UnboundedScan(ds, size) if ds.size > size =>
    //     (0L until Math.ceil(ds.size.toFloat / size).toLong).map(x => BoundedScan(ds, size, x))
    //   case x: UnboundedScan => Seq(x)
    // }
    // sqlContext.sparkContext.parallelize(splitScans).flatMap{ item =>
    //   new ScanExecutor(item.dataset.file).execQuery(item)
    // }
    // Open the file
    // // Distribute the table data
    // val rdd = spark.sparkContext.newAPIHadoopFile(fn,
    //   classOf[FitsFileInputFormat],
    //   classOf[LongWritable],
    //   classOf[Seq[Row]],
    //   conf).flatMap(x => x._2)
    // rdd
    sqlContext.sparkContext.emptyRDD[Row]
  }

}
