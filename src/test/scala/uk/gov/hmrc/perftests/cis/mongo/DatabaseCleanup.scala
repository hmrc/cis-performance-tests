/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

///*
// * Copyright 2024 HM Revenue & Customs
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package uk.gov.hmrc.perftests.cis.mongo
//
//import java.sql.{Connection, DriverManager, Statement}
//import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
////import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}
////import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
//
//import scala.concurrent.Await
//import scala.concurrent.duration.DurationInt
//
//trait DatabaseCleanup {
////  trait DatabaseCleanup extends BeforeAndAfterEach with BeforeAndAfterAll { self: Suite =>
//
//  // MongoDB Configuration
////  private val mongoUri            = "mongodb://localhost:27017"
////  private val mongoDatabaseName   = "cis-frontend"
////  private val mongoCollectionName = "user-answers"
//
////  private def dropMongoCollection(): Unit = {
////    val mongoClient: MongoClient              = MongoClient(mongoUri)
////    val database: MongoDatabase               = mongoClient.getDatabase(mongoDatabaseName)
////    val collection: MongoCollection[Document] = database.getCollection(mongoCollectionName)
////
////    try {
////      val dropFuture = collection.drop().toFuture()
////      Await.result(dropFuture, 10.seconds)
////      println(
////        s"MongoDB collection '$mongoCollectionName' in database '$mongoDatabaseName' dropped successfully."
////      )
////    } catch {
////      case e: Exception =>
////        println(s"Failed to drop MongoDB collection '$mongoCollectionName': ${e.getMessage}")
////    } finally mongoClient.close()
////  }
//
//
//  protected def dropMongoCollection(): Unit = {
//    val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
//    try {
//      val database: MongoDatabase = mongoClient.getDatabase("cis-frontend")
//      val collection: MongoCollection[Document] = database.getCollection("user-answers")
//      Await.result(collection.drop().toFuture(), 10.seconds)
//    } finally {
//      mongoClient.close()
//    }
//  }
//
//  // Oracle Database Configuration
//  private val oracleUrl      = "jdbc:oracle:thin:@//localhost:1521/XE"
//  private val oracleUsername = "sys as sysdba"
//  private val oraclePassword = "oracle"
//
//  def deleteOracleTableData(): Unit = {
//    var connection: Connection = null
//    var statement: Statement   = null
//
//    try {
//      connection = DriverManager.getConnection(oracleUrl, oracleUsername, oraclePassword)
//      connection.setAutoCommit(false)
//      statement = connection.createStatement()
//
//      val deleteChildQuery = """
//        DELETE FROM CIS_FILE_DATA.MONTHLY_RETURN_ITEM
//        WHERE MONTHLY_RETURN_ID IN (
//          SELECT MONTHLY_RETURN_ID FROM CIS_FILE_DATA.MONTHLY_RETURN
//        )
//      """
//      val childRowsDeleted = statement.executeUpdate(deleteChildQuery)
//      println(s"Deleted $childRowsDeleted rows from MONTHLY_RETURN_ITEM.")
//
//      val deleteParentQuery = "DELETE FROM CIS_FILE_DATA.MONTHLY_RETURN"
//      val parentRowsDeleted = statement.executeUpdate(deleteParentQuery)
//      println(s"Deleted $parentRowsDeleted rows from MONTHLY_RETURN.")
//
//      connection.commit()
//      println("Data deletion in Formp Proxy completed successfully.")
//    } catch {
//      case e: Exception =>
//        e.printStackTrace()
//        if (connection != null) {
//          println("Rolling back transaction due to an error.")
//          connection.rollback()
//        }
//    } finally {
//      if (statement != null) statement.close()
//      if (connection != null) connection.close()
//    }
//  }
//
////  override def beforeEach(): Unit = {
////    super.beforeEach()
////    dropMongoCollection()
////    deleteOracleTableData()
////  }
//
//}
