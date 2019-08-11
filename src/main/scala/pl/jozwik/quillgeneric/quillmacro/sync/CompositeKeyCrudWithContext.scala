package pl.jozwik.quillgeneric.quillmacro.sync

import io.getquill.context.Context
import pl.jozwik.quillgeneric.quillmacro.WithId

import scala.language.experimental.macros
import scala.util.Try

trait CompositeKeyCrudWithContext {
  this: Context[_, _] =>
  type dQuery[T] = this.DynamicEntityQuery[T]

  def all[T](implicit dSchema: dQuery[T]): Try[Seq[T]] = macro CompositeKeyCrudMacro.all

  def create[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): Try[K] = macro CompositeKeyCrudMacro.create

  def createAndRead[K, T <: WithId[K]](entity: T)(implicit dSchema: dQuery[T]): Try[T] = macro CompositeKeyCrudMacro.createAndRead

  def createOrUpdate[K, T <: WithId[K]](
    entity: T)(implicit dSchema: dQuery[T]): Try[K] = macro CompositeKeyCrudMacro.createOrUpdate

  def createOrUpdateAndRead[K, T <: WithId[K]](
    entity: T)(implicit dSchema: dQuery[T]): Try[T] = macro CompositeKeyCrudMacro.createOrUpdateAndRead

  def update[T](entity: T)(implicit dSchema: dQuery[T]): Try[Long] = macro CompositeKeyCrudMacro.update

  def updateAndRead[T](entity: T)(implicit dSchema: dQuery[T]): Try[T] = macro CompositeKeyCrudMacro.updateAndRead

  def read[K, T <: WithId[K]](id: K)(implicit dSchema: dQuery[T]): Try[Option[T]] = macro CompositeKeyCrudMacro.read

  def delete[K, T <: WithId[K]](id: K)(implicit dSchema: dQuery[T]): Try[Boolean] = macro CompositeKeyCrudMacro.delete

  def deleteByFilter[T](filter: T => Boolean)(implicit dSchema: dQuery[T]): Try[Boolean] = macro CompositeKeyCrudMacro.deleteByFilter

  def searchByFilter[T](filter: T => Boolean)(
    offset: Int,
    limit: Int)(implicit dSchema: dQuery[T]): Try[Seq[T]] = macro CompositeKeyCrudMacro.searchByFilter

  def count[T](filter: T => Boolean)(implicit dSchema: dQuery[T]): Try[Long] = macro CompositeKeyCrudMacro.count
}
