package pl.jozwik.quillgeneric.sync

import java.time.LocalDate

import io.getquill.{ H2Dialect, H2JdbcContext, SnakeCase }
import pl.jozwik.quillgeneric.AbstractSpec
import pl.jozwik.quillgeneric.model.{ Person, PersonId }
import pl.jozwik.quillgeneric.quillmacro.sync.Queries

import scala.util.Try

class QueriesSpec extends AbstractSpec {

  private lazy val ctx = new H2JdbcContext(SnakeCase, "h2") with Queries
  private lazy val repository = new PersonRepository[H2Dialect, SnakeCase.type] {
    val context: H2JdbcContext[SnakeCase.type] with Queries = ctx

  }

  override def afterAll(): Unit = {
    ctx.close()
    super.afterAll()
  }

  "QueriesAsync " should {
    "Call all operations " in {
      val person = Person(PersonId(1), "firstName", "lastName", LocalDate.now)
      repository.all shouldBe Try(Seq())
      repository.create(person) shouldBe 'success
      repository.update(person) shouldBe 'success
      repository.all shouldBe Try(Seq(person))
      repository.delete(person.id) shouldBe 'success
      repository.all shouldBe Try(Seq())
    }
  }
}
