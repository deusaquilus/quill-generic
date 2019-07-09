package pl.jozwik.quillgeneric.sync

import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import pl.jozwik.quillgeneric.model.{ Person, PersonId }
import pl.jozwik.quillgeneric.quillmacro.Queries

import scala.util.Try

trait PersonRepository[Dialect <: SqlIdiom, Naming <: NamingStrategy]
  extends Repository[PersonId, Person] {
  val context: JdbcContext[Dialect, Naming] with Queries

  import context._

  override def all: Try[Seq[Person]] =
    context.all[Person]

  override def create(person: Person, generateId: Boolean = false): Try[PersonId] =
    if (generateId) {
      context.createAndGenerateId[PersonId, Person](person)
    } else {
      context.create[PersonId, Person](person)
    }

  override def read(id: PersonId): Try[Option[Person]] =
    context.read[PersonId, Person](id)

  override def createOrUpdate(entity: Person): Try[PersonId] =
    context.insertOrUpdate[PersonId, Person](entity)

  override def update(person: Person): Try[Long] =
    context.update[Person](person)

  override def update(id: PersonId, action: Person => (Any, Any), actions: Function[Person, (Any, Any)]*): Try[Long] =
    context.updateById[Person](_.id == lift(id), action, actions: _*)

  override def delete(id: PersonId): Try[Boolean] =
    context.deleteByFilter[Person](_.id == context.lift(id))

}
