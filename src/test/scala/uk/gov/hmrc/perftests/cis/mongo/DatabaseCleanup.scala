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

package uk.gov.hmrc.perftests.cis.mongo

import java.sql.{Connection, DriverManager, Statement}
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import uk.gov.hmrc.perftests.cis.utils.Env
import uk.gov.hmrc.perftests.cis.utils.Env._

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object DatabaseCleanup {

  // MongoDB URIs for different environments
  private val localMongoUri       = "mongodb://localhost:27017"
  private val jenkinsMongoUri     =
    "mongodb://public-mongo-eu-west-2a-1:27017,public-mongo-eu-west-2b-1:27017,public-mongo-eu-west-2c-1:27017/cis-frontend?ssl=true"
  private val mongoDatabaseName   = "cis-frontend"
  private val mongoCollectionName = "user-answers"

  // Determine the MongoDB URI based on the environment
  private def getMongoUri: String =
    Env.currentEnvironment match {
      case Local | Staging =>
        println("Using local MongoDB URI.")
        localMongoUri
      case JenkinsStaging  =>
        println("Using Jenkins MongoDB URI.")
        jenkinsMongoUri
    }

  def dropMongoCollection(): Unit = {
    val mongoUri                              = getMongoUri
    val mongoClient: MongoClient              = MongoClient(mongoUri)
    val database: MongoDatabase               = mongoClient.getDatabase(mongoDatabaseName)
    val collection: MongoCollection[Document] = database.getCollection(mongoCollectionName)

    try {
      println(
        s"Attempting to drop MongoDB collection '$mongoCollectionName' in database '$mongoDatabaseName' using URI: $mongoUri..."
      )
      val dropFuture = collection.drop().toFuture()
      Await.result(dropFuture, 30.seconds)
      println(s"MongoDB collection '$mongoCollectionName' dropped successfully.")
    } catch {
      case e: Exception =>
        println(s"Failed to drop MongoDB collection '$mongoCollectionName': ${e.getMessage}")
        e.printStackTrace()
    } finally
      mongoClient.close()
  }

  // Oracle Database Configuration
  private val oracleUrl      = "jdbc:oracle:thin:@//localhost:1521/XE"
  private val oracleUsername = "sys as sysdba"
  private val oraclePassword = "oracle"

  def deleteOracleTableData(): Unit = {
    var connection: Connection = null
    var statement: Statement   = null

    try {
      connection = DriverManager.getConnection(oracleUrl, oracleUsername, oraclePassword)
      connection.setAutoCommit(false)
      statement = connection.createStatement()

      val deleteChildQuery =
        """
        DELETE FROM CIS_FILE_DATA.MONTHLY_RETURN_ITEM
        WHERE MONTHLY_RETURN_ID IN (
          SELECT MONTHLY_RETURN_ID FROM CIS_FILE_DATA.MONTHLY_RETURN
        )
      """
      val childRowsDeleted = statement.executeUpdate(deleteChildQuery)
      println(s"Deleted $childRowsDeleted rows from MONTHLY_RETURN_ITEM.")

      val deleteParentQuery = "DELETE FROM CIS_FILE_DATA.MONTHLY_RETURN"
      val parentRowsDeleted = statement.executeUpdate(deleteParentQuery)
      println(s"Deleted $parentRowsDeleted rows from MONTHLY_RETURN.")

      connection.commit()
      println("Data deletion in Formp Proxy completed successfully.")
    } catch {
      case e: java.sql.SQLRecoverableException =>
        println("A recoverable SQL error occurred: " + e.getMessage)
        e.printStackTrace()
        if (connection != null) {
          println("Rolling back transaction due to a recoverable SQL error.")
          connection.rollback()
        }
      case e: java.io.IOException              =>
        println("An IO error occurred: " + e.getMessage)
        e.printStackTrace()
        if (connection != null) {
          println("Rolling back transaction due to an IO error.")
          connection.rollback()
        }
      case e: Exception                        =>
        println("An unexpected error occurred: " + e.getMessage)
        e.printStackTrace()
        if (connection != null) {
          println("Rolling back transaction due to an unexpected error.")
          connection.rollback()
        }
    } finally
      try {
        if (statement != null) statement.close()
        if (connection != null) connection.close()
      } catch {
        case e: Exception =>
          println("An error occurred while closing resources: " + e.getMessage)
          e.printStackTrace()
      }
  }

  def cleanupDatabaseIfNotStub(): Unit =
    Env.currentEnvironment match {
      case Local                    =>
        println("Running Oracle database cleanup as this is the local environment.")
        deleteOracleTableData()
      case Staging | JenkinsStaging =>
        println("Skipping Oracle database cleanup as this is a stub environment.")
    }

}
