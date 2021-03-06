package pl.jozwik.quillgeneric.quillmacro.sync

import io.getquill.context.Context
import pl.jozwik.quillgeneric.quillmacro.{ CrudMacro, DateQuotes, WithId }

import scala.language.experimental.macros

object CrudWithContext {
  type CrudWithContextDateQuotesUnit = CrudWithContextDateQuotes[Unit]
  type CrudWithContextDateQuotesLong = CrudWithContextDateQuotes[Long]
}

trait CrudWithContextDateQuotes[U] extends DateQuotes {
  this: Context[_, _] =>
  type dQuery[T] = this.DynamicEntityQuery[T]

  def all[T](implicit dSchema: dQuery[T]): Seq[T] = macro CrudMacro.all[T]

  def create[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): K = macro CrudMacro.create[K, T]

  def createAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): T = macro CrudMacro.createAndRead[K, T]

  def createAndGenerateId[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): K = macro CrudMacro.createAndGenerateId[K, T]

  def createWithGenerateIdAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): T = macro CrudMacro.createWithGenerateIdAndRead[K, T]

  def createOrUpdate[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): K = macro CrudMacro.createOrUpdate[K, T]

  def createOrUpdateAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): T = macro CrudMacro.createOrUpdateAndRead[K, T]

  def createAndGenerateIdOrUpdate[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): K = macro CrudMacro.createAndGenerateIdOrUpdate[K, T]

  def createWithGenerateIdOrUpdateAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): T =
    macro CrudMacro.createWithGenerateIdOrUpdateAndRead[K, T]

  def update[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): U = macro CrudMacro.update[K, T]

  def updateAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): T = macro CrudMacro.updateAndRead[K, T]

  def read[K, T <: WithId[K]](id: K)(implicit dSchema: dQuery[T]): Option[T] = macro CrudMacro.read[K, T]

  def readUnsafe[K, T <: WithId[K]](id: K)(implicit dSchema: dQuery[T]): T = macro CrudMacro.readUnsafe[K, T]

  def delete[K, T <: WithId[K]](id: K)(implicit dSchema: dQuery[T]): U = macro CrudMacro.delete[K]

  def deleteAll[T](implicit dSchema: dQuery[T]): U = macro CrudMacro.deleteAll[T]

  def deleteByFilter[T](filter: T => Boolean)(implicit dSchema: dQuery[T]): Long = macro CrudMacro.deleteByFilter

  def searchByFilter[T](filter: T => Boolean)(offset: Int, limit: Int)(implicit dSchema: dQuery[T]): Seq[T] = macro CrudMacro.searchByFilter[T]

  def count[T](filter: T => Boolean)(implicit dSchema: dQuery[T]): Long = macro CrudMacro.count
}
