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

}
